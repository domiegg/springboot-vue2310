package com.film.util;

/**
 * 字符串工具类*/
public class StringUtil {
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断字符串是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if((str!=null) && !"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	public static String formatLike(String str){
		if(isNotEmpty(str)){
			return "%"+str+"%";
		}else{
			return null;
		}
	}
}
