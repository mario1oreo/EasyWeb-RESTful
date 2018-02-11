package com.wf.ew.system.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.etp.authz.annotation.RequiresPermissions;
import com.wf.ew.core.PageResult;
import com.wf.ew.core.ResultMap;
import com.wf.ew.core.exception.BusinessException;
import com.wf.ew.core.exception.ParameterException;
import com.wf.ew.core.utils.JSONUtil;
import com.wf.ew.system.model.MenuTree;
import com.wf.ew.system.model.Permission;
import com.wf.ew.system.service.AuthService;
import com.wf.ew.system.service.PermissionService;

/**
 * 菜单管理
 * @author wangfan
 * @date 2017-3-24 下午3:56:21
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionController {
	@Autowired
	private PermissionService menuService;
	@Autowired
	private AuthService authService;
	
	/**
	 * 查询所有菜单
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@GetMapping()
	public PageResult<Permission> list(Integer page, Integer limit, String searchKey, String searchValue) throws UnsupportedEncodingException{
		if(searchValue!=null){
			searchValue = new String(searchValue.getBytes("ISO-8859-1"),"UTF-8");
		}
		if(page == null) {
			page = 0;
			limit = 0;
		}
		return menuService.getPermissions(page, limit, searchKey, searchValue);
	}
	
	/**
	 * 根据roleId查询所有菜单
	 */
	@GetMapping("/role{roleId}")
	public List<Permission> list(@PathVariable("roleId") String roleId){
		return menuService.getPermissionsByRoleId(roleId);
	}
	
	/**
	 * 角色权限菜单树
	 */
	@GetMapping("/tree/{roleId}")
	public List<MenuTree> listPermTree(@PathVariable("roleId") String roleId){
		return authService.getPermissionTree(roleId);
	}
	
	/**
	 * 修改角色权限
	 */
	@PutMapping("/tree")
	public ResultMap updatePermTree(String roleId, String permIds){
		List<String> permissionIds = JSONUtil.parseArray(permIds);
		if(authService.updateRolePermission(roleId, permissionIds)){
			return ResultMap.ok("修改成功");
		}else{
			return ResultMap.error("修改失败");
		}
	}
	
	/**
	 * 查询所有的父菜单
	 */
	@GetMapping("/parent")
	public List<Permission> listParent(){
		return menuService.getParentPermissions();
	}
	
	/**
	 * 添加菜单
	 * @param user
	 * @return
	 */
	@RequiresPermissions("system/permission")
	@PostMapping()
	public ResultMap add(Permission permission){
		if(menuService.addPermission(permission)){
			return ResultMap.ok("添加成功！");
		}else{
			return ResultMap.error("添加失败！");
		}
	}
	
	/**
	 * 修改菜单
	 * @param user
	 * @return
	 */
	@RequiresPermissions("system/permission")
	@PutMapping()
	public ResultMap update(Permission permission){
		if(menuService.updatePermission(permission)){
			return ResultMap.ok("修改成功！");
		}else{
			return ResultMap.error("修改失败！");
		}
	}
	
	/**
	 * 修改状态
	 */
	@RequiresPermissions("system/permission")
	@PutMapping("status")
	public ResultMap updateStatus(String permissionId, int status) throws ParameterException {
		if(menuService.updatePermissionStatus(permissionId, status)){
			return ResultMap.ok();
		}
		return ResultMap.error();
	}
	
	/**
	 * 删除
	 */
	@RequiresPermissions("system/permission")
	@DeleteMapping("/{permissionId}")
	public ResultMap delete(@PathVariable("permissionId")String permissionId) throws BusinessException {
		if(menuService.delete(permissionId)){
			return ResultMap.ok("删除成功");
		}
		return ResultMap.error("删除失败");
	}
	
}
