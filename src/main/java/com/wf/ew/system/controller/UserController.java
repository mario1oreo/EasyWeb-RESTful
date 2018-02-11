package com.wf.ew.system.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.etp.authz.annotation.RequiresPermissions;
import com.wf.ew.core.BaseController;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import com.wf.ew.core.exception.BusinessException;
import com.wf.ew.core.exception.ParameterException;
import com.wf.ew.system.model.User;
import com.wf.ew.system.service.UserService;

/**
 * 用户管理
 * @author wangfan
 * @date 2017-3-24 下午3:56:37
 */
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	/**
	 * 查询所有用户
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@GetMapping()
	public PageResult<User> list(Integer page, Integer limit, Integer status, String searchKey, String searchValue) throws UnsupportedEncodingException {
		if(searchValue!=null){
			searchValue = new String(searchValue.getBytes("ISO-8859-1"),"UTF-8");
		}
		if(page == null) {
			page = 0;
			limit = 0;
		}
		return userService.getUsers(page, limit, status, searchKey, searchValue);
	}
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 * @throws BusinessException 
	 */
	@RequiresPermissions("system/user")
	@PostMapping()
	public ResultMap add(User user) throws BusinessException {
		user.setUserPassword("123456");
		if(userService.addUser(user)){
			return ResultMap.ok("添加成功！");
		}else{
			return ResultMap.error("添加失败，请重试！");
		}
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@PutMapping()
	public ResultMap update(User user) {
		if(userService.updateUser(user)){
			return ResultMap.ok("修改成功！");
		}else{
			return ResultMap.error("修改失败！");
		}
	}
	
	/**
	 * 修改用户状态
	 * @param userId
	 * @return
	 * @throws ParameterException 
	 */
	@RequiresPermissions("system/user")
	@PutMapping("status")
	public ResultMap updateStatus(String userId, int status) throws ParameterException {
		if(userService.updateUserStatus(userId, status)){
			return ResultMap.ok();
		}else{
			return ResultMap.error();
		}
	}
	
	/**
	 * 修改密码
	 * @param userIds
	 * @return
	 */
	@PutMapping("psw")
	public ResultMap updatePsw(String newPsw, HttpServletRequest request) {
		if(userService.updateUserPsw(getUserId(request), newPsw)) {
			return ResultMap.ok();
		}else{
			return ResultMap.error();
		}
	}
	
	@RequiresPermissions("system/user")
	@DeleteMapping("/{userId}")
	public ResultMap delete(@PathVariable("userId") String userId) {
		if(userService.deleteUser(userId)){
			return ResultMap.ok("删除成功");
		}else{
			return ResultMap.error("删除失败");
		}
	}
}