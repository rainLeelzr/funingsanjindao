package com.funing.commonfn.model.mahjong;


import java.util.List;

/**
 * 游戏开始第一次发牌广播
 */
public class FirstPutOutCard {

    // 需要接收本对象的玩家uId,先设置为userId，在api层转换为uId
    private Integer uId;

    // 庄家的uId，先设置为userId，在api层转换为uId
    private Integer bankerUId;

    // 骰子
    private Integer[] dices;

    //个人的手牌id
    private List<Integer> handCardIds;

    /**
     * 剩下可以被摸牌的麻将的数量
     */
    private Integer leftCardCount;

    /**
     * 宝娘
     */
    private Integer baoMotherId;

    /**
     * 宝牌
     */
    private List<Integer> baoMahjongs;

    /**
     * 消息版本号
     */
    private Long version;

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getBankerUId() {
        return bankerUId;
    }

    public void setBankerUId(Integer bankerUId) {
        this.bankerUId = bankerUId;
    }

    public Integer[] getDices() {
        return dices;
    }

    public void setDices(Integer[] dices) {
        this.dices = dices;
    }

    public List<Integer> getHandCardIds() {
        return handCardIds;
    }

    public void setHandCardIds(List<Integer> handCardIds) {
        this.handCardIds = handCardIds;
    }

    public Integer getLeftCardCount() {
        return leftCardCount;
    }

    public void setLeftCardCount(Integer leftCardCount) {
        this.leftCardCount = leftCardCount;
    }

    public Integer getBaoMotherId() {
        return baoMotherId;
    }

    public void setBaoMotherId(Integer baoMotherId) {
        this.baoMotherId = baoMotherId;
    }

    public List<Integer> getBaoMahjongs() {
        return baoMahjongs;
    }

    public void setBaoMahjongs(List<Integer> baoMahjongs) {
        this.baoMahjongs = baoMahjongs;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
