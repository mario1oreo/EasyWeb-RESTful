package com.wf.ew.system.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.exception.BusinessException;
import com.wf.ew.core.exception.ParameterException;
import com.wf.ew.core.utils.UUIDUtil;
import com.wf.ew.system.dao.RoleMapper;
import com.wf.ew.system.model.Role;
import com.wf.ew.system.model.RoleExample;
import com.wf.ew.system.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleBaseMapper;

	@Override
	public PageResult<Role> getRoles(int pageNum, int pageSize, String searchKey, String searchValue, Integer isDelete) {
		PageResult<Role> result = new PageResult<Role>();
		RoleExample example = new RoleExample();
		example.setOrderByClause("create_time asc");
		RoleExample.Criteria criteria = example.createCriteria();
		if(isDelete!=null){
			criteria.andIsDeleteEqualTo(isDelete);
		}
		if(searchKey!=null){
			if(searchKey.equals("role_id")){
				criteria.andRoleIdLike("%"+searchValue+"%");
			}else if(searchKey.equals("role_name")){
				criteria.andRoleNameLike("%"+searchValue+"%");
			}else if(searchKey.equals("comments")){
				criteria.andCommentsLike("%"+searchValue+"%");
			}
		}
		Page<Object> startPage = PageHelper.startPage(pageNum, pageSize);
		result.setData(roleBaseMapper.selectByExample(example));
		result.setCount(startPage.getTotal());
		return result;
	}
	
	@Override
	public boolean addRole(Role role) {
		role.setRoleId(UUIDUtil.randomUUID8());
		role.setCreateTime(new Date());
		return roleBaseMapper.insertSelective(role)>0;
	}

	@Override
	public boolean updateRole(Role role) {
		return roleBaseMapper.updateByPrimaryKeySelective(role)>0;
	}

	@Override
	public boolean updateRoleStatus(String roleId, int isDelete) throws ParameterException {
		if(isDelete!=0&&isDelete!=1){
			throw new ParameterException();
		}
		Role role = new Role();
		role.setRoleId(roleId);
		role.setIsDelete(isDelete);
		return roleBaseMapper.updateByPrimaryKeySelective(role)>0;
	}
	
	@Override
	public Role getRoleById(String roleId) {
		return roleBaseMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public boolean deleteById(String roleId) throws BusinessException {
		try {
			return roleBaseMapper.deleteByPrimaryKey(roleId)>0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("角色已经被使用,无法删除");
		}
	}
}
