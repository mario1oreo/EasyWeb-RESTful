package com.wf.ew.system.service;

import java.util.List;

import com.wf.ew.core.PageResult;
import com.wf.ew.core.exception.BusinessException;
import com.wf.ew.core.exception.ParameterException;
import com.wf.ew.system.model.Permission;

/**
 * 菜单Menu操作相关的service
 * 
 * @author wangfan
 * @date 2017-4-27 下午5:27:11
 */
public interface PermissionService {

	/**
	 * 获取用户的菜单导航
	 * 
	 * @param userId
	 * @return
	 */
	public List<Permission> getUserMenus(String userId);

	/**
	 * 根据角色id查询权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Permission> getPermissionsByRoleId(String roleId);

	/**
	 * 根据角色类型查询权限
	 * 
	 * @param permissionType
	 * @return
	 */
	public PageResult<Permission> getPermissions(Integer page, Integer limit, String searchKey, String searchValue);

	/**
	 * 得到所有的一级权限
	 * 
	 * @return
	 */
	public List<Permission> getParentPermissions();

	/**
	 * 得到所有的非一级权限
	 * 
	 * @return
	 */
	public List<Permission> getSubPermissions();

	/**
	 * 添加权限
	 * 
	 * @param permission
	 * @return
	 */
	public boolean addPermission(Permission permission);

	/**
	 * 根据id修改权限
	 * 
	 * @param permission
	 * @return
	 */
	public boolean updatePermission(Permission permission);

	/**
	 * 修改状态
	 * 
	 * @param isDelete
	 *            0正常,1删除
	 * @return
	 */
	public boolean updatePermissionStatus(String permissionId, int isDelete)
			throws ParameterException;
	
	//删除
	public boolean delete(String permissionId) throws BusinessException;

}
