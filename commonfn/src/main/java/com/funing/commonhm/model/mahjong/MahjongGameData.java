package com.funing.commonfn.model.mahjong;


import com.funing.commonfn.model.RoomMember;
import org.apache.commons.lang.math.RandomUtils;

import java.util.*;

/**
 * 麻将游戏的数据结构，每次开始游戏，都需要创建这个对象来保存游戏过程中的数据。
 * 一份完整的数据将会被存放在redis，另外会按照玩家的数量拆分成玩家个人的数据传给客户端
 */
public class MahjongGameData {

    //正常情况下手牌的数量
    public static final int HAND_CARD_NUMBER = 13;

    // json转对象时用到的复杂对象转换字段关系映射
    public static Map<String, Class> classMap;

    static {
        classMap = new HashMap<>();
        classMap.put("personalCardInfos", PersonalCardInfo.class);
        classMap.put("outCards", OutCard.class);
        classMap.put("roomMember", RoomMember.class);
        classMap.put("roomMember", RoomMember.class);
        classMap.put("handCards", Mahjong.class);
        classMap.put("leftCards", Mahjong.class);
        classMap.put("baoMahjongs", Mahjong.class);
        classMap.put("baoMahjongMakeUpMahjongs", Mahjong.class);
        classMap.put("baoMother", Mahjong.class);
    }

    // 庄家的座位号，从1开始
    private Integer bankerSite;

    // 骰子
    private Integer[] dices;

    //n个人的手牌，玩家的座位与list下标对应，第一个玩家下标为0
    private List<PersonalCardInfo> personalCardInfos;

    /**
     * 剩下可以被摸牌的麻将，有先后顺序
     */
    private List<Mahjong> leftCards;

    /**
     * 玩家打出的牌
     */
    private List<OutCard> outCards;

    /**
     * 宝娘
     */
    private Mahjong baoMother;

    /**
     * 宝牌
     */
    private List<Mahjong> baoMahjongs;

    /**
     * 宝牌可以变成以下的牌
     */
    private List<Mahjong> baoMahjongMakeUpMahjongs;
    /**
     * 消息版本号
     */
    private Long version;

    /**
     * 初始化麻将游戏，将麻将打乱，分成n份，每份13只，还有剩余的牌
     * 庄家在最后会摸多一张牌，即庄家有14张
     * 初始数据的生成与实际的玩家无关，只需要确定玩家的人数和庄家座位即可。
     * 初始数据生成后，由调用者封装实际玩家数据，例如List<PersonalCardInfo>里的RoomMember。
     *
     * @param players    玩家人数，决定分多少副手牌
     * @param bankerSite 庄家的座位号，从1开始。庄家会多摸一张牌
     */
    public static MahjongGameData initData(int players, int bankerSite) {
        // 获取所有麻将牌
        List<Mahjong> allMahjongs = Mahjong.getAllMahjongs();

        // 参数验证
        if (bankerSite > players || bankerSite < 0 || players == 0) {
            throw new RuntimeException(String.format(
                    "初始化麻将游戏错误，" +
                            "[庄家座位号不能大于玩家人数]" +
                            "或[庄家座位号不能少于0]" +
                            "或[玩家人数不能为0]。" +
                            "玩家人数%s，庄家座位号%s。", players, bankerSite
            ));
        }
        if (players * HAND_CARD_NUMBER > allMahjongs.size()) {
            throw new RuntimeException(String.format(
                    "初始化麻将游戏错误，共有%s张麻将牌，但需要发给%s个人，每人%s张牌超出数量！",
                    allMahjongs.size(),
                    players,
                    HAND_CARD_NUMBER));
        }

        // 创建麻将游戏的数据结构
        MahjongGameData mahjongGameData = new MahjongGameData();
        mahjongGameData.bankerSite = bankerSite;
        List<PersonalCardInfo> handCards = new ArrayList<>(players);
        List<Mahjong> leftCards = new ArrayList<>(
                allMahjongs.size() - players * HAND_CARD_NUMBER);
        mahjongGameData.setPersonalCardInfos(handCards);
        mahjongGameData.setLeftCards(leftCards);

        // 掷骰
        mahjongGameData.dices = rollDice();

        // 打乱所有麻将牌顺序
        Collections.shuffle(allMahjongs);
        //System.out.println("乱序后麻将：" + allMahjongs);

        // allMahjongs的下标，一共120张牌，用于记录分到第几张牌
        int index = 0;

        // 创建每个玩家的手牌对象
        for (int i = 0; i < players; i++) {
            PersonalCardInfo handCard = new PersonalCardInfo();
            Set<Mahjong> mahjongs = new TreeSet<>();
            handCard.setHandCards(mahjongs);

            // 碰列表
            handCard.setPengs(new ArrayList<Combo>(4));

            // 杠列表
            handCard.setGangs(new ArrayList<Combo>(4));

            handCards.add(handCard);
        }

        // 把牌分给玩家
        Set<Mahjong> temp;
        for (PersonalCardInfo handCard : handCards) {
            temp = handCard.getHandCards();
            for (int i = 0; i < HAND_CARD_NUMBER; i++) {
                temp.add(allMahjongs.get(index++));
            }
        }

        // 庄家摸多一张牌
        handCards.get(bankerSite - 1).setTouchMahjong(allMahjongs.get(index++));

        // 剩下的牌放在leftCards
        for (; index < allMahjongs.size(); index++) {
            leftCards.add(allMahjongs.get(index));
        }

        // 设定宝娘
        // todome 设定宝娘
        mahjongGameData.setBaoMother(leftCards.get(1));

        // 设定宝牌
        // todome 设定宝牌
        List<Mahjong> baoMahjongs = new ArrayList<>(4);
        baoMahjongs.add(Mahjong.THREE_TIAO_1);
        baoMahjongs.add(Mahjong.THREE_TIAO_2);
        baoMahjongs.add(Mahjong.THREE_TIAO_3);
        baoMahjongs.add(Mahjong.THREE_TIAO_4);
        mahjongGameData.setBaoMahjongs(baoMahjongs);

        return mahjongGameData;
    }

    /**
     * 掷骰
     */
    public static Integer[] rollDice() {
        return new Integer[]{
                RandomUtils.nextInt(6) + 1,
                RandomUtils.nextInt(6) + 1};
    }

    public static void main(String[] args) {
        MahjongGameData data = new MahjongGameData();
        List<Mahjong> baoMahjongs = new ArrayList<>(4);
        baoMahjongs.add(Mahjong.THREE_TIAO_1);
        baoMahjongs.add(Mahjong.THREE_TIAO_2);
        baoMahjongs.add(Mahjong.THREE_TIAO_3);
        baoMahjongs.add(Mahjong.THREE_TIAO_4);
        data.setBaoMahjongs(baoMahjongs);
        data.getBaoMahjongMakeUpMahjongs();
    }

    public Mahjong getBaoMother() {
        return baoMother;
    }

    public void setBaoMother(Mahjong baoMother) {
        this.baoMother = baoMother;
    }

    public List<Mahjong> getBaoMahjongMakeUpMahjongs() {
        if (baoMahjongMakeUpMahjongs == null && this.baoMahjongs != null) {
            // 初始化宝牌能变成的牌
            List<Mahjong> allMahjongs = Mahjong.getAllMahjongs();
            List<Mahjong> temps = new ArrayList<>(allMahjongs.size()
                    - this.baoMahjongs.size());
            for (Mahjong tempMahjong : allMahjongs) {
                if (!this.baoMahjongs.contains(tempMahjong)) {
                    temps.add(tempMahjong);
                }
            }
            this.baoMahjongMakeUpMahjongs =
                    new ArrayList<>(temps.size() / 4);
            for (int i = 0; i < temps.size(); i += 4) {
                this.baoMahjongMakeUpMahjongs
                        .add(temps.get(i));
            }
        }
        //System.out.println(this.baoMahjongs);
        //System.out.println(this.baoMahjongMakeUpMahjongs);
        return baoMahjongMakeUpMahjongs;
    }

    public void setBaoMahjongMakeUpMahjongs(List<Mahjong> baoMahjongMakeUpMahjongs) {
        this.baoMahjongMakeUpMahjongs = baoMahjongMakeUpMahjongs;
    }

    public List<Mahjong> getBaoMahjongs() {
        return baoMahjongs;
    }

    public void setBaoMahjongs(List<Mahjong> baoMahjongs) {
        this.baoMahjongs = baoMahjongs;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getBankerSite() {
        return bankerSite;
    }

    public void setBankerSite(Integer bankerSite) {
        this.bankerSite = bankerSite;
    }

    public Integer[] getDices() {
        return dices;
    }

    public void setDices(Integer[] dices) {
        this.dices = dices;
    }

    @Override
    public String toString() {
        return "{\"MahjongGameData\":{"
                + "\"bankerSite\":\"" + bankerSite + "\""
                + ", \"dices\":" + Arrays.toString(dices)
                + ", \"personalCardInfos\":" + personalCardInfos
                + ", \"leftCards\":" + leftCards
                + ", \"outCards\":" + outCards
                + ", \"version\":\"" + version + "\""
                + "}}";
    }

    public List<PersonalCardInfo> getPersonalCardInfos() {
        return personalCardInfos;
    }

    public void setPersonalCardInfos(List<PersonalCardInfo> personalCardInfos) {
        this.personalCardInfos = personalCardInfos;
    }

    public List<Mahjong> getLeftCards() {
        return leftCards;
    }

    public void setLeftCards(List<Mahjong> leftCards) {
        this.leftCards = leftCards;
    }

    public List<OutCard> getOutCards() {
        return outCards;
    }

    public void setOutCards(List<OutCard> outCards) {
        this.outCards = outCards;
    }

    public Integer getRoomId() {
        if (personalCardInfos == null || personalCardInfos.size() == 0 ||
                personalCardInfos.get(0).getRoomMember() == null) {
            return null;
        }
        return personalCardInfos.get(0).getRoomMember().getRoomId();
    }
}
