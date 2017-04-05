package com.funing.commonfn.redis;

import com.funing.commonfn.manager.operate.CanDoOperate;
import com.funing.commonfn.model.mahjong.MahjongGameData;
import com.funing.commonfn.model.mahjong.PersonalCardInfo;
import com.funing.commonfn.redis.base.Redis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

@Component
public class GameRedis {

    private static String MAHJONG_GAME_DATA_FIELD_KEY = "mahjongGameData";

    private static String WAITING_CLIENT_OPERATE_FIELD_KEY = "waitingClientOperate";

    @Autowired
    private Redis redis;

    public void saveWaitingClientOperate(CanDoOperate canDoOperate) {
        redis.hash.put(
                String.format(RoomRedis.ROOM_KEY, canDoOperate.getRoomMember().getRoomId()),
                WAITING_CLIENT_OPERATE_FIELD_KEY,
                canDoOperate);
    }

    public void saveMahjongGameData(MahjongGameData mahjongGameData) {
        redis.hash.put(
                String.format(RoomRedis.ROOM_KEY, mahjongGameData.getRoomId()),
                MAHJONG_GAME_DATA_FIELD_KEY,
                mahjongGameData);
    }

    public MahjongGameData getMahjongGameData(Integer roomId) {
        MahjongGameData temp = (MahjongGameData) redis.hash.get(
                String.format(RoomRedis.ROOM_KEY, roomId),
                MAHJONG_GAME_DATA_FIELD_KEY,
                MahjongGameData.class,
                MahjongGameData.classMap);
        if (temp != null && temp.getPersonalCardInfos() != null && temp
                .getPersonalCardInfos().size() != 0) {
            for (PersonalCardInfo personalCardInfo : temp
                    .getPersonalCardInfos()) {
                personalCardInfo.setHandCards(
                        new TreeSet<>(personalCardInfo.getHandCards())
                );
            }
        }
        return temp;
    }

    public CanDoOperate getWaitingClientOperate(Integer roomId) {
        CanDoOperate temp = (CanDoOperate) redis.hash.get(
                String.format(RoomRedis.ROOM_KEY, roomId),
                WAITING_CLIENT_OPERATE_FIELD_KEY,
                CanDoOperate.class,
                CanDoOperate.classMap);
        return temp;
    }

    public void deleteWaitingClientOperate(Integer roomId) {
        redis.hash.delete(String.format(RoomRedis.ROOM_KEY, roomId),
                WAITING_CLIENT_OPERATE_FIELD_KEY);
    }
}
