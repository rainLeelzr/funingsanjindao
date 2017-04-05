package com.funing.commonfn.manager.operate;

/**
 * 基本操作类型
 */
public enum BaseOperate {
    // 优先级高的要写在前面
    HU("胡"),//胡包括自摸、吃胡

    GANG("杠"),

    PENG("碰"),

    PLAY_A_MAHJONG("打出一张牌");

    private String name;

    BaseOperate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{\"BaseOperate\":{"
                + "\"name\":\"" + name + "\""
                + "}}";
    }
}
