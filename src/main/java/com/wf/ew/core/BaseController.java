package com.wf.ew.core;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller基类
 * 
 * @author wangfan
 * @date 2017-6-10 上午10:38:16
 */
public class BaseController {

	/**
	 * 获取登录的User
	 */
	public String getUserId(HttpServletRequest request) {
		return (String) request.getAttribute("userId");
	}

	/**
	 * 获取当前请求的token
	 */
	public String getToken(HttpServletRequest request) {
		String token = request.getHeader("token");
		if (token == null) {
			token = request.getParameter("token");
		}
		return token;
	}

}
