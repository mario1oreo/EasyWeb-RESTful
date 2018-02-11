package com.wf.ew.core.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.wf.etp.authz.IUserRealm;
import com.wf.etp.authz.SubjectUtil;
import com.wf.ew.system.model.Permission;
import com.wf.ew.system.service.PermissionService;
import com.wf.ew.system.service.UserService;

/**
 * UserRealm
 * 
 * @author wangfan
 * @date 2018-1-22 上午8:30:17
 */
public class UserRealm extends IUserRealm {
	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

	@Override
	public Set<String> getUserRoles(String userId) {
		Set<String> roles = new HashSet<String>();
		roles.add(userService.getUserById(userId).getRoleId());
		return roles;
	}

	@Override
	public Set<String> getUserPermissions(String userId) {
		Set<String> permissionValues = new HashSet<String>();
		List<String> userRoles = SubjectUtil.getInstance().getUserRoles(userId);
		if(userRoles.size()>0){
			List<Permission> permissions = permissionService.getPermissionsByRoleId(userRoles.get(0));
			for (int i = 0; i < permissions.size(); i++) {
				permissionValues.add(permissions.get(i).getPermissionValue());
			}
		}
		return permissionValues;
	}
}