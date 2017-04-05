package com.funing.commonfn.manager.scanTask.impl;

import com.funing.commonfn.manager.operate.Operate;
import com.funing.commonfn.manager.putOutCard.scanTask.AbstractGangScanTask;
import com.funing.commonfn.model.mahjong.Mahjong;
import com.funing.commonfn.model.mahjong.PersonalCardInfo;
import com.funing.commonfn.model.mahjong.Combo;

import java.util.List;

/**
 * 扫描是否可以软加杠
 */
public class RuanJiaGang extends AbstractGangScanTask {

    @Override
    public Operate getOperate() {
        return Operate.RUAN_JIA_GANG;
    }

    @Override
    public boolean doScan(PersonalCardInfo personalCardInfo)
            throws InstantiationException, IllegalAccessException {

        // 判断玩家有没有已经碰了的牌并且碰牌中有宝牌
        List<Combo> pengs = personalCardInfo.getPengs();
        if (pengs.size() == 0) {
            return false;
        }

        Integer baoMahjongNumber = this.mahjongGameData.getBaoMahjongs().get(0).getNumber();
        for (Combo peng : pengs) {
            // 考虑宝牌归位的情况
            int baoMahjong = 0;
            for (Mahjong mahjong : peng.getMahjongs()) {
                if (mahjong.getNumber().equals(baoMahjongNumber)) {
                    baoMahjong++;
                }
            }

            if (baoMahjong == 3) {
                //宝牌归位,不算软加杠
                return false;
            } else if (baoMahjong == 0) {
                // 没有宝牌
                return false;
            }

            // 如果有一只麻将与specifiedMahjong相同，则是软加杠
            for (Mahjong mahjong : peng.getMahjongs()) {
                if (mahjong.getNumber().equals(specifiedMahjong.getNumber())) {
                    return true;
                }
            }
        }

        return false;
    }

}
