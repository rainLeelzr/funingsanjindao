package com.funing.commonfn.service.impl;

import com.funing.commonfn.dao.TranRecordDao;
import com.funing.commonfn.model.TranRecord;
import com.funing.commonfn.service.TranRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranRecordServiceImpl extends BaseServiceImpl<Integer, TranRecord> implements TranRecordService {
	
	@Autowired
	private TranRecordDao dao;

}