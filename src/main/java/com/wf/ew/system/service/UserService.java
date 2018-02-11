package com.wf.ew.system.service;

import com.wf.ew.core.PageResult;
import com.wf.ew.core.exception.BusinessException;
import com.wf.ew.core.exception.ParameterException;
import com.wf.ew.system.model.User;

/**
 * UserService
 * 
 * @author wangfan
 * @date 2017-3-24 下午4:12:31
 */
public interface UserService {

	/**
	 * 根据账号查询用户
	 * 
	 * @param userAccount
	 * @return
	 */
	public User getUserByAccount(String userAccount);

	/**
	 * 根据userId查询User
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId);

	/**
	 * 查询所有的用户
	 * 
	 * @return
	 */
	public PageResult<User> getUsers(int pageNum, int pageSize, Integer status, String searchKey, String searchValue);

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) throws BusinessException;

	/**
	 * 根据id修改用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);

	/**
	 * 修改用户状态
	 * 
	 * @param userId
	 * @return
	 */
	public boolean updateUserStatus(String userId, int status) throws ParameterException;

	/**
	 * 根据id重置密码
	 * 
	 * @param userIds
	 * @return
	 */
	public boolean updateUserPsw(String userId, String password);
	
	//删除用户
	public boolean deleteUser(String userId);

}
