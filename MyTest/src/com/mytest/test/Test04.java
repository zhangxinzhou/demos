package com.mytest.test;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * @author ZXZ
 * @see 时间格式的转换和压缩
 *
 */
public class Test04 {

	private static final SimpleDateFormat SDF_SWIFT_NUMBER=new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static final SimpleDateFormat SDF_DATE_TIME=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	
	public static void main(String[] args){
		new Test04().test();
	}


	public void test(){		
		Long ms=System.currentTimeMillis();	
		Date date=new Date(ms);
		System.out.println("时间(原格式) : "+date);	
		System.out.println("时间                     : "+SDF_DATE_TIME.format(date));		
		System.out.println("时间(流水号) : "+SDF_SWIFT_NUMBER.format(date));
		System.out.println("流水号压缩         : "+Base62.encode(Long.valueOf(SDF_SWIFT_NUMBER.format(date))));
		System.out.println("毫秒                     : "+ms);
		System.out.println("毫秒压缩             : "+Base62.encode(ms));
	}

}
