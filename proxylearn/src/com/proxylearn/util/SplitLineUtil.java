package com.proxylearn.util;

public class SplitLineUtil {

	/*分割线*/
	private static final String SPLIT_LINE="*********************************";
	

	/**
	 *            获取分割线
	 * @param msg 分割线中间文字
	 * @return
	 */
	public static String getSplitLine(String msg){
		StringBuffer sb=new StringBuffer(SPLIT_LINE);
		sb.insert(SPLIT_LINE.length()/2, msg);
		return sb.toString();
	}
	
	/**
	 *               打印分割线
	 * @param msg    分割线中间文字
	 */
	public static void printSplitLine(String msg){
		System.out.println(getSplitLine(msg));
	}
}
