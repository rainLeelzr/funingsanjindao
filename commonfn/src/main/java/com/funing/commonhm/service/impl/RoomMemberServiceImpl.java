package com.funing.commonfn.service.impl;

import com.funing.commonfn.dao.RoomMemberDao;
import com.funing.commonfn.model.RoomMember;
import com.funing.commonfn.service.RoomMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomMemberServiceImpl extends BaseServiceImpl<Integer, RoomMember> implements RoomMemberService {
	
	@Autowired
	private RoomMemberDao dao;

}