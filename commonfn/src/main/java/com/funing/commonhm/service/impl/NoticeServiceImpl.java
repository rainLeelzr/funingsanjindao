package com.funing.commonfn.service.impl;

import com.funing.commonfn.dao.NoticeDao;
import com.funing.commonfn.model.Notice;
import com.funing.commonfn.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl extends BaseServiceImpl<Integer, Notice> implements NoticeService {
	
	@Autowired
	private NoticeDao dao;

}