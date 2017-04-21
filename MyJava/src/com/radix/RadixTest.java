package com.radix;

public class RadixTest {

	
	public static void main(String[] args) {
		showTest();
		Test1();
		Test2();
	}
	
	
	//进制
	public static void showTest(){
		//定义
		int b=0b1111011;   //二进制(Binary)      0b___
		int o=0173;        //八进制(Octal)       0____
		int d=123;         //十进制(decimal)     _____
		int h=0x7b;        //十六进制(hexadecimal)0x___
		System.out.println(b);
		System.out.println(o);
		System.out.println(d);
		System.out.println(h);
		
		//十进制转换成其他进制
		int temp=123;
		String bStr=Integer.toBinaryString(temp);
		String oStr=Integer.toOctalString(temp);
		String dStr=Integer.toString(temp);
		String hStr=Integer.toHexString(temp);
		System.out.println(bStr);
		System.out.println(oStr);
		System.out.println(dStr);
		System.out.println(hStr);
		
		//其他进制转换成十进制
		Integer bb=Integer.valueOf(bStr,2);
		Integer oo=Integer.valueOf(oStr,8);
		Integer dd=Integer.valueOf(dStr,10);
		Integer hh=Integer.valueOf(hStr,16);
		System.out.println(bb);
		System.out.println(oo);
		System.out.println(dd);
		System.out.println(hh);
	}
	
	//毫秒转换成16进制,压缩成字符串并没有减少多少
	public static void Test1(){
		long time=System.currentTimeMillis();
		String hex=Long.toHexString(time);
		System.out.println(time);
		System.out.println(hex);
	}
	
	//其他
	public static void Test2(){
		byte b=(byte)0;
		short s=(short)0;
		int i=0;
		long l=0l;
		float f=0f;
		double d=0d;
		char c='0';
		boolean bl=false;
		
		String str="String";
		
		System.out.println(b);
		System.out.println(s);
		System.out.println(i);
		System.out.println(l);
		System.out.println(f);
		System.out.println(d);
		System.out.println(c);
		System.out.println(bl);
		System.out.println(str);
	}
}
