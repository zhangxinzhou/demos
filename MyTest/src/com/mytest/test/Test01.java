package com.mytest.test;

import java.util.Properties;

/**
 * 打印出系统参数System.Properties
 * @author ZXZ
 *
 */
public class Test01 {

	
	public static void main(String[] args) {
		Properties props= System.getProperties();
		props.forEach((k,v)->{
			System.out.println(k+"="+v);
		});
	}
}
