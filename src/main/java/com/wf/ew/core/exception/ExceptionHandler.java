package com.wf.ew.core.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.wf.etp.authz.exception.EtpException;

/**
 * 统一异常处理器
 * 
 * @author wangfan
 * @date 2017-7-14 下午3:27:35
 */
public class ExceptionHandler implements HandlerExceptionResolver {
	//日志输出对象
	private Logger logger = Logger.getLogger(ExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj, Exception ex) {
		// 根据不同错误获取错误信息
		if (ex instanceof IException) {
			writerJson(response, ((IException) ex).getCode(), ex.getMessage());
		} else if(ex instanceof EtpException){
			writerJson(response, ((EtpException) ex).getCode(), ex.getMessage());
		} else {
			writerJson(response, 500, "未知错误，请稍后再试！");
			logger.error(ex.getMessage(), ex.getCause());
			ex.printStackTrace();
		}
		return new ModelAndView();
	}

	/**
	 * 写入json数据
	 * 
	 * @param response
	 * @throws Exception 
	 */
	private void writerJson(HttpServletResponse response, int code, String msg) {
		response.setContentType("application/json;charset=UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.write("{\"code\":"+code+",\"msg\":\""+msg+"\"}");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
