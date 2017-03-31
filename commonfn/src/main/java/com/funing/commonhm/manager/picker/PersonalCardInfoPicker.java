package com.funing.commonfn.manager.picker;

import com.funing.commonfn.model.User;
import com.funing.commonfn.model.mahjong.MahjongGameData;
import com.funing.commonfn.model.mahjong.PersonalCardInfo;

import java.util.List;

/**
 * 从mahjongGameData中提取需要进行判断的PersonalCardInfo
 */
public interface PersonalCardInfoPicker {

    List<PersonalCardInfo> pick(MahjongGameData mahjongGameData, User user);
}
