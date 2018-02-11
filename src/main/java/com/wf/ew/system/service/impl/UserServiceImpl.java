package com.wf.ew.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangfan.endecrypt.utils.EndecryptUtils;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.exception.BusinessException;
import com.wf.ew.core.exception.ParameterException;
import com.wf.ew.core.utils.UUIDUtil;
import com.wf.ew.system.dao.UserMapper;
import com.wf.ew.system.model.User;
import com.wf.ew.system.model.UserExample;
import com.wf.ew.system.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public PageResult<User> getUsers(int pageNum, int pageSize, Integer status, String searchKey, String searchValue) {
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		List<User> users = userMapper.selectUsers(status, searchKey, searchValue);
		PageResult<User> result = new PageResult<User>();
		result.setData(users);
		result.setCount(startPage.getTotal());
		return result;
	}

	@Override
	public boolean addUser(User user) throws BusinessException {
		if(getUserByAccount(user.getUserAccount())!=null){
			throw new BusinessException("账号已经存在");
		}
		user.setUserId(UUIDUtil.randomUUID8());
		String decryptMd5 = EndecryptUtils.encrytMd5(user.getUserPassword(), user.getUserId(), 3);
		user.setUserPassword(decryptMd5);
		user.setUserStatus(0);
		user.setCreateTime(new Date());
		return userMapper.insertSelective(user)>0;
	}

	@Override
	public boolean updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}

	@Override
	public boolean updateUserStatus(String userId, int status) throws ParameterException {
		if(status!=0&&status!=1){
			throw new ParameterException();
		}
		User user = new User();
		user.setUserId(userId);
		user.setUserStatus(status);
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}

	@Override
	public User getUserByAccount(String userAccount) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUserAccountEqualTo(userAccount);
		List<User> list = userMapper.selectByExample(userExample);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public boolean updateUserPsw(String userId, String password) {
		User user = new User();
		user.setUserId(userId);
		String decryptMd5 = EndecryptUtils.encrytMd5(password, userId, 3);
		user.setUserPassword(decryptMd5);
		return userMapper.updateByPrimaryKeySelective(user)>0;
	}

	@Override
	public User getUserById(String userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public boolean deleteUser(String userId) {
		return userMapper.deleteByPrimaryKey(userId)>0;
	}
}
