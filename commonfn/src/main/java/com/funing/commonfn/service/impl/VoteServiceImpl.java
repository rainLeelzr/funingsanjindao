package com.funing.commonfn.service.impl;

import com.funing.commonfn.dao.VoteDao;
import com.funing.commonfn.model.Vote;
import com.funing.commonfn.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteServiceImpl extends BaseServiceImpl<Integer, Vote> implements VoteService {
	
	@Autowired
	private VoteDao dao;

}