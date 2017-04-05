package com.funing.commonfn.service.impl;

import com.funing.commonfn.dao.RecordDao;
import com.funing.commonfn.model.Record;
import com.funing.commonfn.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends BaseServiceImpl<Integer, Record> implements RecordService {
	
	@Autowired
	private RecordDao dao;

}