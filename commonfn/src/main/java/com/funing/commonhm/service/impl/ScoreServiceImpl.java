package com.funing.commonfn.service.impl;

import com.funing.commonfn.dao.ScoreDao;
import com.funing.commonfn.model.Score;
import com.funing.commonfn.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl extends BaseServiceImpl<Integer, Score> implements ScoreService {
	
	@Autowired
	private ScoreDao dao;

}