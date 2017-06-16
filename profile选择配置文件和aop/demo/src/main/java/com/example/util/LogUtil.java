package com.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

	private final static String LINE="#########################################################";
	private final static int INSERT_LOCATION=LINE.length()/2;
	private final static Logger log=LoggerFactory.getLogger(LogUtil.class);
	
	public static String getSplitLine(String msg){
		StringBuffer sb=new StringBuffer(LINE);
		sb.insert(INSERT_LOCATION, msg);
		return sb.toString();
	}
	
	public static void printSplitLine(String msg){
		log.info(getSplitLine(msg));
	}
	
	public static void main(String[] args) {
		printSplitLine("this");
	}
}
