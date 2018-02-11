package com.wf.ew.system.model;

import java.io.Serializable;

/**
 * 角色权限
 * @author wangfan
 * @date 2017-7-4 下午2:49:24
 */
public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1848074014446805333L;

	private String id;

    private String roleId;

    private String permissionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }
}