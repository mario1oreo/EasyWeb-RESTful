package com.wf.ew.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.utils.UUIDUtil;
import com.wf.ew.system.dao.LoginRecordMapper;
import com.wf.ew.system.model.LoginRecord;
import com.wf.ew.system.service.LoginRecordService;

@Service
public class LoginRecordServiceImpl implements LoginRecordService {
	@Autowired
	private LoginRecordMapper loginRecordMapper;

	@Override
	public boolean addLoginRecord(LoginRecord object) {
		object.setId(UUIDUtil.randomUUID8());
		object.setCreateTime(new Date());
		return loginRecordMapper.insert(object)>0;
	}

	@Override
	public PageResult<LoginRecord> getLoginRecords(int pageIndex, int pageSize, String startDate, String endDate, String searchAccount) {
		Page<Object> startPage = PageHelper.startPage(pageIndex, pageSize);
		List<LoginRecord> list = loginRecordMapper.selectLoginRecords(startDate,endDate,searchAccount);
		return new PageResult<LoginRecord>(startPage.getTotal(), list);
	}
}