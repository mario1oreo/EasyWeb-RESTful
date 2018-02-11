package com.wf.ew.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.etp.authz.annotation.RequiresPermissions;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.utils.StringUtil;
import com.wf.ew.system.model.LoginRecord;
import com.wf.ew.system.service.LoginRecordService;

/**
 * 登录日志
 * @author wangfan
 * @date 2017-7-24 下午1:22:26
 */
@RestController
@RequestMapping("/api/loginRecord")
public class LoginRecordController {
	@Autowired
	private LoginRecordService loginRecordService;
	
	/**
	 * 查询所有登录日志
	 * @return
	 */
	@RequiresPermissions("system/loginRecord")
	@GetMapping()
	public PageResult<LoginRecord> list(Integer page, Integer limit,String startDate,String endDate,String account){
		if(StringUtil.isBlank(startDate)){
			startDate = null;
		}else{
			startDate+=" 00:00:00";
		}
		if(StringUtil.isBlank(endDate)){
			endDate = null;
		}else{
			endDate+=" 23:59:59";
		}
		if(StringUtil.isBlank(account)){
			account = null;
		}
		return loginRecordService.getLoginRecords(page, limit, startDate, endDate, account);
	}
	
}
