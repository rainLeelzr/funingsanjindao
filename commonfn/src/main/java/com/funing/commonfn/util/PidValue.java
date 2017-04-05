package com.funing.commonfn.util;

/**
 * 接口协议号
 **/
public enum PidValue {

    /**
     * 系统/
     */
    CHECK_CONNECTION(9000, "检测连接"),
    TEST(1, "开发调试"),

    /**
     * 用户
     */
    LOGIN(1000, "用户登录"),
    LOGOUT(1001,"注销登录"),
    FREE_COINS(1002,"免费领取金币"),
    GET_USER(1006, "用户信息"),
    GET_STANDINGS(1008, "获取战绩"),

    /**
     * 房间
     */
    CREATE_ROOM(2000, "创建房间"),
    JOIN_ROOM(2001, "加入房间"),
    OUT_ROOM(2002, "退出房间"),
    DISMISS_ROOM(2003, "申请解散房间"),
    AGREE_DISMISS(2004, "是否同意解散房间"),
    READY(2005, "用户准备"),

    /**
     * 游戏过程
     */
    PLAY_A_MAHJONG(3000, "打出一张牌"),

    YING_ZI_MO(3101, "硬自摸"),
    RUAN_ZI_MO(3102, "软自摸"),

    YING_CHI_HU(3103, "硬吃胡"),
    RUAN_CHI_HU(3104, "软吃胡"),

    YING_AN_GANG(3105, "硬暗杠"),
    RUAN_AN_GANG(3106, "软暗杠"),

    YING_DA_MING_GANG(3107, "硬大明杠"),
    RUAN_DA_MING_GANG(3108, "软大明杠"),

    YING_JIA_GANG(3109, "硬加杠"),
    RUAN_JIA_GANG(3110, "软加杠"),

    YING_PENG(3111, "硬碰"),
    RUAN_PENG(3112, "软碰"),

    GUO(3200, "过"),

    /**
     * 消息推送
     */
    JOIN_ROOM_MESSAGE(4000, "加入房间消息推送"),
    FIRST_PUT_OUT_ALL_CARD(4001, "游戏开始发牌消息推送"),
    CLIENT_TOUCH_MAHJONG(4002, "发一张牌给客户端消息推送"),
    OTHER_USER_PLAY_A_MAHJONG(4003, "玩家打牌广播"),;

    private int pid;

    private String name;

    PidValue(int pid, String name) {
        this.pid = pid;
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }
}
