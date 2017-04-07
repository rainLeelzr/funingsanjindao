package com.funing.commonfn.service.impl;

import com.funing.commonfn.dao.RoomMemberDao;
import com.funing.commonfn.manager.getACard.GetACardManager;
import com.funing.commonfn.manager.operate.CanDoOperate;
import com.funing.commonfn.manager.putOutCard.AfterPutOutCardManager;
import com.funing.commonfn.model.Room;
import com.funing.commonfn.model.RoomMember;
import com.funing.commonfn.model.User;
import com.funing.commonfn.model.mahjong.*;
import com.funing.commonfn.redis.GameRedis;
import com.funing.commonfn.redis.RoomRedis;
import com.funing.commonfn.redis.VersionRedis;
import com.funing.commonfn.util.CommonError;
import com.funing.commonfn.util.JsonUtil;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameService {

    public static final String FIRST_PUT_OUT_CARD_KEY = "firstPutOutCard";
    private static final Logger log = LoggerFactory.getLogger(GameService.class);
    @Autowired
    private GameRedis gameRedis;

    @Autowired
    private VersionRedis versionRedis;

    @Autowired
    private AfterPutOutCardManager afterPutOutCardManager;

    @Autowired
    private RoomMemberDao roomMemberDao;

    @Autowired
    private RoomRedis roomRedis;

    /***
     * 初始化数据包括：骰子的点数、每个人的手牌、剩余的牌等信息。
     * 一局游戏开始时，生成麻将的初始数据。
     */
    public Map<String, Object> firstPutOutCard(
            Room room, List<RoomMember> roomMembers)
            throws InstantiationException, IllegalAccessException {

        // 对roomMembers按座位号升序
        Collections.sort(roomMembers, new Comparator<RoomMember>() {
            @Override
            public int compare(RoomMember o1, RoomMember o2) {
                return o1.getSeat() - o2.getSeat();
            }
        });

        Map<String, Object> result = new HashMap<>(6);

        int players = room.getPlayers();
        int bankerSite = 1;

        //先设置为userId，在api层转换为uId
        //获取第一个 房间参与者 id值
        Integer bankerUId = roomMembers.get(bankerSite - 1).getUserId();

        // 初始化一局麻将的数据
        MahjongGameData mahjongGameData = MahjongGameData.initData(players, bankerSite);
        log.debug("初始化一局麻将的数据:{}", JsonUtil.toJson(mahjongGameData));

        // 获取新版本号
        Long version = versionRedis.nextVersion(room.getId());
        mahjongGameData.setVersion(version);

        // roomMember改为游戏中
        for (RoomMember roomMember : roomMembers) {
            roomMember.setState(RoomMember.state.PLAYING.getCode());
            roomMemberDao.update(roomMember);
            roomRedis.editRoom(roomMember);
            roomRedis.joinRoom(roomMember);
        }

        // 添加roomMeMber，拆分成n份手牌数据，传给客户端
        List<FirstPutOutCard> firstPutOutCards = new ArrayList<>(players);
        ClientTouchMahjong clientTouchMahjong = new ClientTouchMahjong();
        for (int i = 0; i < players; i++) {
            PersonalCardInfo personalCardInfo = mahjongGameData.getPersonalCardInfos().get(i);
            // 添加玩家信息RoomMember
            personalCardInfo.setRoomMember(roomMembers.get(i));

            FirstPutOutCard fpc = new FirstPutOutCard();
            fpc.setBankerUId(bankerUId);//先设置为userId，在api层转换为uId
            fpc.setuId(personalCardInfo.getRoomMember().getUserId());//先设置为userId，在api层转换为uId
            fpc.setDices(mahjongGameData.getDices());

            List<Integer> handCardIds = new ArrayList<>(personalCardInfo.getHandCards().size());
            for (Mahjong mahjong : personalCardInfo.getHandCards()) {
                handCardIds.add(mahjong.getId());
            }
            fpc.setHandCardIds(handCardIds);

            fpc.setLeftCardCount(mahjongGameData.getLeftCards().size());
            fpc.setBaoMotherId(mahjongGameData.getBaoMother().getId());

            List<Integer> baoMahjongIds = new ArrayList<>(mahjongGameData.getBaoMahjongs().size());
            for (Mahjong mahjong : mahjongGameData.getBaoMahjongs()) {
                baoMahjongIds.add(mahjong.getId());
            }
            fpc.setBaoMahjongs(baoMahjongIds);

            fpc.setVersion(version);

            firstPutOutCards.add(fpc);
        }

        result.put(FIRST_PUT_OUT_CARD_KEY, firstPutOutCards);

        log.debug("初始化一局麻将添加玩家信息RoomMember的数据:{}", JsonUtil.toJson(mahjongGameData));

        // 麻将数据存redis
        gameRedis.saveMahjongGameData(mahjongGameData);
        result.put(MahjongGameData.class.getSimpleName(), mahjongGameData);
        return result;
    }

    /**
     * 打出一张麻将
     */
    public Map<String, Object> playAMahjong(Room room, User user, Mahjong playedMahjong, long version) {
        // 验证版本号
        validateVersion(room, version);

        // 删除redis的等待客户端操作对象waitingClientOperate
        CanDoOperate waitingClientOperate = gameRedis.getWaitingClientOperate(room.getId());
        if (!waitingClientOperate.getRoomMember().getUserId().equals(user.getId())) {
            throw CommonError.NOT_YOUR_TURN.newException();
        }

        // 取出麻将数据对象
        MahjongGameData mahjongGameData = gameRedis.getMahjongGameData(room.getId());

        // 出牌验证
        if (!putOutCardValidate(playedMahjong, mahjongGameData, user)) {
            throw CommonError.USER_NOT_HAVE_SPECIFIED_CARD.newException();
        }

        gameRedis.deleteWaitingClientOperate(room.getId());

        // 广播打出的牌
        List<PlayedMahjong> playedMahjongs = playedMahjongBroadcast(mahjongGameData, user, playedMahjong);

        Map<String, Object> result = new HashedMap(2);
        result.put(PlayedMahjong.class.getSimpleName(), playedMahjong);
        return result;
    }


    private List<PlayedMahjong> playedMahjongBroadcast(MahjongGameData mahjongGameData, User user, Mahjong playedMahjong) {
        List<PlayedMahjong> playedMahjongs = new ArrayList<>(mahjongGameData.getPersonalCardInfos().size());
        for (PersonalCardInfo personalCardInfo : mahjongGameData.getPersonalCardInfos()) {
            PlayedMahjong temp = new PlayedMahjong();
            temp.setuId(personalCardInfo.getRoomMember().getUserId());//先设置为userid，在api转uid
            temp.setLeftCardCount(mahjongGameData.getLeftCards().size());
            temp.setPlayedMahjongId(playedMahjong.getId());
            temp.setPlayedUId(user.getUId());
            playedMahjongs.add(temp);
        }
        return playedMahjongs;
    }

    /**
     * 玩家出牌时，验证其版本号
     */
    private void validateVersion(Room room, long version) {
        Long nowVersion = versionRedis.nowVersion(room.getId());
        if (!nowVersion.equals(version)) {
            throw CommonError.SYS_VERSION_TIMEOUT.newException();
        }
    }


    /**
     * 客户端打出一张牌的处理逻辑。
     * 遍历其他3个玩家，判断是否有碰，明杠，吃胡。
     * 如果有，则按优先级降序，存进redis的
     * 选择优先级最高的操作，返回给对应的客户端，等待其选择执行操作或着选择过
     * 如果选择过，则读redis待操作集合，循环上一步操作。
     * 如果全部人都选择过（待操作集合为空），则进入下家摸牌方法。
     * 如果待操作集合中有人选择了执行操作，则清空待操作集合，执行相应操作。
     *
     * @param putOutMahjong 打出的牌对象
     * @param room          用户所在的房间
     * @param version       消息版本号
     */
    public void putOutCard(Mahjong putOutMahjong, Room room, User user,
                           Long version)
            throws InstantiationException, IllegalAccessException {
        // 验证版本号
        validateVersion(room, version);

        // 取出麻将数据
        MahjongGameData mahjongGameData = gameRedis.getMahjongGameData(
                room.getId());

        // 出牌验证
        if (!putOutCardValidate(putOutMahjong, mahjongGameData, user)) {
            throw CommonError.USER_NOT_HAVE_SPECIFIED_CARD.newException();
        }

        for (PersonalCardInfo personalCardInfo : mahjongGameData.getPersonalCardInfos()) {
            log.debug("验证后座位{}的手牌：{}{}",
                    personalCardInfo.getRoomMember().getSeat(),
                    personalCardInfo.getHandCards().size(),
                    personalCardInfo.getHandCards());
        }

        // 扫描其他用户是否有吃胡、大明杠、碰的操作
        ArrayList<CanDoOperate> canOperates =
                afterPutOutCardManager.scan(mahjongGameData, putOutMahjong, user);
        log.debug("扫描出来可以的操作：{}", canOperates);


    }

    /**
     * 验证客户端出的牌是否合法，合法则在玩家的PersonalCardInfo手牌集合中移除该麻将牌
     */
    private boolean putOutCardValidate(Mahjong putOutCard, MahjongGameData
            mahjongGameData, User user) {
        // 获取玩家手牌信息
        PersonalCardInfo personalCardInfo = null;
        for (PersonalCardInfo gameData : mahjongGameData.getPersonalCardInfos()) {
            if (gameData.getRoomMember().getUserId().equals(user.getId())) {
                personalCardInfo = gameData;
                break;
            }
        }
        if (personalCardInfo == null) {
            throw CommonError.REDIS_GAME_DATA_ERROR.newException();
        }

        // 判断客户端打出的牌是不是刚摸上手的
        if (putOutCard == personalCardInfo.getTouchMahjong()) {
            personalCardInfo.setTouchMahjong(null);
            return true;
        }

        // 判断客户端打出的牌是不是其拥有的手牌
        boolean isHandCard = false;
        Iterator<Mahjong> iterator = personalCardInfo.getHandCards().iterator();
        while (iterator.hasNext()) {
            Mahjong mahjong = iterator.next();
            if (mahjong == putOutCard) {
                iterator.remove();
                isHandCard = true;
            }
        }
        if (isHandCard) {
            personalCardInfo.getHandCards().add(putOutCard);
        }
        return isHandCard;

    }


}
