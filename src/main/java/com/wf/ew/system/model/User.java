package com.wf.ew.system.model;

import java.io.Serializable;
import java.util.Date;

/**
 * User
 * @author wangfan
 * @date 2017-7-4 下午2:49:38
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1972489869581873269L;

	private String userId;

    private String userAccount;

    private String userPassword;

    private String userNickname;

    private String mobilePhone;

    private String sex;

    private Integer userStatus;  //0正常，1冻结

    private Date createTime;

    private Date updateTime;

    private String roleId;

    private String token;
    
    private String roleName;  //角色名

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userAccount=" + userAccount
				+ ", userPassword=" + userPassword + ", userNickname="
				+ userNickname + ", mobilePhone=" + mobilePhone + ", sex="
				+ sex + ", userStatus=" + userStatus + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", roleId="
				+ roleId + ", token=" + token + ", roleName=" + roleName + "]";
	}
    
}