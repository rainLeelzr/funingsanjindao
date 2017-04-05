package com.funing.commonfn.util.mock;

import com.funing.commonfn.model.mahjong.Mahjong;
import org.apache.commons.lang.math.RandomUtils;

import java.util.Arrays;
import java.util.List;

public class MockComboMahjongList {

    /**
     * 硬平胡的麻将组合
     */
    public static List<Mahjong> getYingPingHuMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_4)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                all.remove(all.indexOf(Mahjong.ONE_WANG_2)),
                null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_3)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_2)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_3)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_1)),
                all.remove(all.indexOf(Mahjong.TWO_TIAO_2)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_3)),
                all.remove(all.indexOf(Mahjong.FOUR_TIAO_1)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_2)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_3)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_1)),
                all.remove(all.indexOf(Mahjong.SEVEN_TONG_2)),
                all.remove(all.indexOf(Mahjong.EIGHT_TONG_2)),
                all.remove(all.indexOf(Mahjong.SIX_TONG_1)),
                /////////////////////////////////////////////////
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }

    /**
     * 硬对对胡的麻将组合
     */
    public static List<Mahjong> getYingPengPengHuMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_4)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.EIGHT_WANG_1)),
                all.remove(all.indexOf(Mahjong.EIGHT_WANG_2)),
                all.remove(all.indexOf(Mahjong.EIGHT_WANG_3)),
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_2)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_3)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_1)),
                all.remove(all.indexOf(Mahjong.FOUR_WANG_2)),
                all.remove(all.indexOf(Mahjong.FOUR_WANG_3)),
                all.remove(all.indexOf(Mahjong.FOUR_WANG_1)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_2)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_3)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_1)),
                all.remove(all.indexOf(Mahjong.TWO_WANG_2)),
                all.remove(all.indexOf(Mahjong.TWO_WANG_3)),
                all.remove(all.indexOf(Mahjong.TWO_WANG_1)),
                /////////////////////////////////////////////////
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }

    /**
     * 软碰碰胡的麻将组合
     */
    public static List<Mahjong> getRuanPengPengHuMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_4)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.EIGHT_WANG_1)),
                all.remove(all.indexOf(Mahjong.EIGHT_WANG_2)),
                all.remove(all.indexOf(Mahjong.EIGHT_WANG_3)),
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_2)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_3)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_1)),
                all.remove(all.indexOf(Mahjong.FOUR_WANG_2)),
                all.remove(all.indexOf(Mahjong.FOUR_WANG_3)),
                all.remove(all.indexOf(Mahjong.FOUR_WANG_1)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_2)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_3)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_1)),
                all.remove(all.indexOf(Mahjong.TWO_WANG_2)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_3)),
                all.remove(all.indexOf(Mahjong.TWO_WANG_1)),
                /////////////////////////////////////////////////
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }

    /**
     * 硬大明杠的麻将组合
     */
    public static List<Mahjong> getYingMingGangMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_4)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                all.remove(all.indexOf(Mahjong.ONE_WANG_2)),
                all.remove(all.indexOf(Mahjong.ONE_WANG_3)),
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }

    /**
     * 软大明杠的麻将组合
     */
    public static List<Mahjong> getRuanDaMingGangMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_4)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                all.remove(all.indexOf(Mahjong.ONE_WANG_2)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_3)),
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.THREE_TIAO_1)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_2)),
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }

    /**
     * 硬加杠的麻将组合
     */
    public static List<Mahjong> getYingJiaGangMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_4)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                all.remove(all.indexOf(Mahjong.ONE_WANG_2)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_3)),
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.THREE_TIAO_1)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_2)),
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }

    /**
     * 软加杠的麻将组合
     */
    public static List<Mahjong> getRuanJiaGangMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_4)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                all.remove(all.indexOf(Mahjong.ONE_WANG_2)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_3)),
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.THREE_TIAO_1)),
                null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }

    /**
     * 软碰的麻将组合
     */
    public static List<Mahjong> getRuanPengMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_4)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                all.remove(all.indexOf(Mahjong.ONE_TIAO_2)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_3)),
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.THREE_TIAO_1)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_2)),
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }

    /**
     * 硬七对的麻将组合
     */
    public static List<Mahjong> getYingQiDuiMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_4)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_3)),
                all.remove(all.indexOf(Mahjong.ONE_WANG_2)),
                null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                all.remove(all.indexOf(Mahjong.FIVE_TONG_2)),
                all.remove(all.indexOf(Mahjong.FIVE_TONG_3)),
                all.remove(all.indexOf(Mahjong.FOUR_WANG_1)),
                all.remove(all.indexOf(Mahjong.FOUR_WANG_2)),
                all.remove(all.indexOf(Mahjong.FOUR_TIAO_3)),
                all.remove(all.indexOf(Mahjong.FOUR_TIAO_1)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_2)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_3)),
                all.remove(all.indexOf(Mahjong.TWO_TIAO_1)),
                all.remove(all.indexOf(Mahjong.TWO_TIAO_2)),
                all.remove(all.indexOf(Mahjong.TWO_WANG_3)),
                all.remove(all.indexOf(Mahjong.TWO_WANG_1)),
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }


    /**
     * 从mahjongs中抽一个麻将出来，并在mahjongs中移除此麻将
     */
    private static Mahjong getOneForm(List<Mahjong> mahjongs) {
        int i = RandomUtils.nextInt(mahjongs.size());
        return mahjongs.remove(i);
    }

    /**
     * 软平胡的麻将组合
     */
    public static List<Mahjong> getRuanPingHuMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_3)),
                all.remove(all.indexOf(Mahjong.ONE_WANG_2)),
                null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.TWO_WANG_4)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_2)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_3)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_1)),
                all.remove(all.indexOf(Mahjong.TWO_TIAO_2)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_3)),
                all.remove(all.indexOf(Mahjong.FOUR_TIAO_1)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_2)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_3)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_1)),
                all.remove(all.indexOf(Mahjong.SEVEN_TONG_2)),
                all.remove(all.indexOf(Mahjong.EIGHT_TONG_2)),
                all.remove(all.indexOf(Mahjong.SIX_TONG_1)),
                /////////////////////////////////////////////////
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }

    /**
     * 软七对的麻将组合
     */
    public static List<Mahjong> getRuanQiDuiMahjongs() {
        List<Mahjong> all = Mahjong.getAllMahjongs();

        // 自定义所有4个玩家的初始麻将牌
        Mahjong[] m = new Mahjong[]{
                all.remove(all.indexOf(Mahjong.ONE_WANG_4)),
                null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_1)),
                all.remove(all.indexOf(Mahjong.ONE_WANG_2)),
                null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                null, null, null,
                null, null, null,
                null, null, null,
                null, null, null,
                null,
                /////////////////////////////////////////////////
                all.remove(all.indexOf(Mahjong.ONE_WANG_3)),
                all.remove(all.indexOf(Mahjong.THREE_TIAO_2)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_3)),
                all.remove(all.indexOf(Mahjong.FIVE_WANG_1)),
                all.remove(all.indexOf(Mahjong.FIVE_TIAO_2)),
                all.remove(all.indexOf(Mahjong.FIVE_TIAO_3)),
                all.remove(all.indexOf(Mahjong.FOUR_WANG_1)),
                all.remove(all.indexOf(Mahjong.FOUR_WANG_2)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_3)),
                all.remove(all.indexOf(Mahjong.THREE_WANG_1)),
                all.remove(all.indexOf(Mahjong.SEVEN_TONG_2)),
                all.remove(all.indexOf(Mahjong.SEVEN_TONG_1)),
                all.remove(all.indexOf(Mahjong.SIX_TONG_1)),
                /////////////////////////////////////////////////
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null
        };
        for (int i = 0; i < m.length; i++) {
            if (m[i] == null) {
                m[i] = getOneForm(all);
            }
        }

        return Arrays.asList(m);
    }
}
