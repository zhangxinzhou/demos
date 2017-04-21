package com.test02;

/**
 * 目的:希望将毫秒转换成较短的字符串方便处理
 * @author user
 *
 */
public class Test04 {

	private static final long mesc=System.currentTimeMillis();
	
	public static void main(String[] args) {
		msecToHex();
		msecShorten();
		msecShorten2();
	}
	
	//转换成16进制并没减少缩短多少
	public static void msecToHex(){
		long start=System.nanoTime();
		String result=Long.toHexString(mesc);
		Long origin=Long.valueOf(result, 16);
		long spend=System.nanoTime()-start;//println比较消耗时间,故写在这里排除影响
		System.out.println("16进制--------------------");
		System.out.println("当前时间毫秒数:"+mesc);
		System.out.println("当前时间转换后:"+result);
		System.out.println("还原成当前时间:"+origin);
		System.out.println("花费:"+spend+"ns");
	}
	
	//用Base62,将13位数字缩短为7位字符串效果明显(与上方法比较,消耗的时间也是巨大,20~30倍左右,但是也就1ms)
	public static void msecShorten(){
		long start=System.nanoTime();
		String result=Base62.encode(mesc);
		Long origin=Base62.decode(result);
		long spend=System.nanoTime()-start;
		System.out.println("62进制--------------------");
		System.out.println("当前时间毫秒数:"+mesc);
		System.out.println("当前时间转换后:"+result);
		System.out.println("还原成当前时间:"+origin);
		System.out.println("花费:"+spend+"ns");
	}
	
	//自己写的Base62方法
	public static void msecShorten2(){
		long start=System.nanoTime();
		String result=MyBase62.encode(mesc);
		Long origin=MyBase62.decode(result);
		long spend=System.nanoTime()-start;
		System.out.println("62进制(自写)---------------");
		System.out.println("当前时间毫秒数:"+mesc);
		System.out.println("当前时间转换后:"+result);
		System.out.println("还原成当前时间:"+origin);
		System.out.println("花费:"+spend+"ns");
	}
	
}
