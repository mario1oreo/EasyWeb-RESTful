package com.wf.ew.core.exception;

/**
 * IException
 * 
 * @author wangfan
 * @date 2018-1-23 下午12:00:13
 */
public abstract class IException extends Exception {
	private static final long serialVersionUID = -1582874427218948396L;
	private int code;

	public IException(int code, String message) {
		super(message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
