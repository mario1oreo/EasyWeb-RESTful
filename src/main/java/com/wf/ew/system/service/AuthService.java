package com.wf.ew.system.service;

import java.util.List;

import com.wf.ew.system.model.MenuTree;

/**
 * 权限操作相关的service
 * 
 * @author wangfan
 * @date 2017-4-27 下午5:20:43
 */
public interface AuthService {

	/**
	 * 根据角色id查询角色的选中和未选中权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<MenuTree> getPermissionTree(String roleId);

	/**
	 * 修改角色的菜单权限
	 * 
	 * @param roleId
	 * @param permissionIds
	 * @return
	 */
	public boolean updateRolePermission(String roleId, List<String> permissionIds);

}
