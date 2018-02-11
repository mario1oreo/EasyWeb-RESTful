package com.wf.ew.core.utils;

/**
 * 字符串工具类
 * @author 王帆
 * @date 2017-11-10 上午11:03:50
 */
public class StringUtil {
	
	/**
	 * 是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		if(str==null||str.isEmpty()||str.replaceAll(" ", "").isEmpty()){
			return true;
		}
		return false;
	}
	
	public static boolean isBlank(String... strs){
		for(int i=0; i<strs.length; i++){
			if(isBlank(strs[i])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 首字母大写
	 * @param in
	 * @return
	 */
	public static String upperHeadChar(String in){  
	    String head=in.substring(0,1);  
	    String out=head.toUpperCase()+in.substring(1,in.length());  
	    return out;  
	}
	
}
