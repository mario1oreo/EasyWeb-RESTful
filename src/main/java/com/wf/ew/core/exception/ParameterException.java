package com.wf.ew.core.exception;

/**
 * 参数异常
 * 
 * @author wangfan
 * @date 2017-6-29 上午10:17:44
 */
public class ParameterException extends IException {
	private static final long serialVersionUID = 7993671808524980055L;

	public ParameterException(String message) {
		super(500, message);
	}

	public ParameterException() {
		super(500, "参数错误");
	}
}
