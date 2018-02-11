package com.wf.ew.system.service;

import com.wf.ew.core.PageResult;
import com.wf.ew.core.exception.BusinessException;
import com.wf.ew.core.exception.ParameterException;
import com.wf.ew.system.model.Role;

/**
 * 角色操作相关的service
 * 
 * @author wangfan
 * @date 2017-4-27 下午5:37:20
 */
public interface RoleService {
	/**
	 * 查询所有角色
	 * 
	 * @return
	 */
	public PageResult<Role> getRoles(int pageNum, int pageSize, String searchKey, String searchValue, Integer isDelete);

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	public boolean addRole(Role role);

	/**
	 * 根据id修改角色
	 * 
	 * @param role
	 * @return
	 */
	public boolean updateRole(Role role);

	/**
	 * 根据id删除角色
	 * 
	 * @param roleId
	 * @return
	 */
	public boolean updateRoleStatus(String roleId, int isDelete)
			throws ParameterException;

	/**
	 * 根据id查询角色
	 * 
	 * @param roleId
	 * @return
	 */
	public Role getRoleById(String roleId);

	/**
	 * 删除
	 * 
	 * @param roleId
	 * @return
	 */
	public boolean deleteById(String roleId) throws BusinessException ;

}
