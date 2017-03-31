package com.funing.commonfn.dao;

import com.funing.commonfn.model.Entity;
import com.funing.commonfn.model.RoomMember;

import java.util.List;

public interface RoomMemberDao extends BaseDao<Integer, RoomMember> {
    RoomMember selectByUserIdForCheck(RoomMember roomMember);
    RoomMember selectByUserIdForReady(RoomMember roomMember);
    List<RoomMember> selectForStart(RoomMember roomMember);
    List<RoomMember> selectForDismiss(RoomMember roomMember);
}