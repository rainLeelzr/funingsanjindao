package com.funing.commonfn.manager.scanTask.impl;

import com.funing.commonfn.manager.operate.Operate;
import com.funing.commonfn.manager.putOutCard.scanTask.AbstractHuScanTask;
import com.funing.commonfn.model.mahjong.Mahjong;
import com.funing.commonfn.model.mahjong.PersonalCardInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 扫描是否吃硬碰碰胡
 */
public class ChiYingPengPengHu extends AbstractHuScanTask {

    @Override
    public Operate getOperate() {
        return Operate.CHI_YING_PENG_PENG_HU;
    }

    /**
     * 判断依据：
     * 按花字号分组
     * 1：除了含有眼的哪，每组元素个数为3的整数倍数
     */
    @Override
    public boolean doScan(PersonalCardInfo personalCardInfo) throws InstantiationException, IllegalAccessException {
        List<Mahjong> handCards = new ArrayList<>(personalCardInfo.getHandCards());
        handCards.add(specifiedMahjong);
        return isPengPengHu(handCards);
    }
}
