package com.funing.commonfn.model.mahjong;

import com.funing.commonfn.manager.operate.Operate;
import com.funing.commonfn.model.RoomMember;

import java.util.List;
import java.util.Set;

/**
 * 单个玩家拥有的牌信息
 */
public class PersonalCardInfo {

    /**
     * 此副手牌对应的roomMember对象
     */
    private RoomMember roomMember;

    /**
     * 手牌枚举对象
     */
    private Set<Mahjong> handCards;


    /**
     * 客户端可以选择的操作，例如胡、碰、杠，不包含过
     */
    private List<Operate> operates;

    /**
     * 在剩下的牌中，摸一张牌
     */
    private Mahjong touchMahjong;

    /**
     * 已经碰的牌
     */
    private List<Combo> pengs;

    /**
     * 已经杠的牌
     */
    private List<Combo> gangs;

    public List<Combo> getPengs() {
        return pengs;
    }

    public void setPengs(List<Combo> pengs) {
        this.pengs = pengs;
    }

    public List<Combo> getGangs() {
        return gangs;
    }

    public void setGangs(List<Combo> gangs) {
        this.gangs = gangs;
    }

    public List<Operate> getOperates() {
        return operates;
    }

    public void setOperates(List<Operate> operates) {
        this.operates = operates;
    }

    public Mahjong getTouchMahjong() {
        return touchMahjong;
    }

    public void setTouchMahjong(Mahjong touchMahjong) {
        this.touchMahjong = touchMahjong;
    }

    @Override
    public String toString() {
        return "{\"PersonalCardInfo\":{"
                + "\"roomMember\":" + roomMember
                + ", \"handCards\":" + handCards
                + ", \"touchMahjong\":" + touchMahjong + ""
                + "}}";
    }

    public RoomMember getRoomMember() {
        return roomMember;
    }

    public void setRoomMember(RoomMember roomMember) {
        this.roomMember = roomMember;
    }

    public Set<Mahjong> getHandCards() {
        return handCards;
    }

    public void setHandCards(Set<Mahjong> handCards) {
        this.handCards = handCards;
    }
}
