package com.test02;

import java.lang.reflect.Field;
//虽然编译错误,但是可以运行
import sun.misc.Unsafe;



public class Addresser {
	private static Unsafe unsafe;

	static {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe = (Unsafe) field.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//对String的final(不可改变)理解
	public static void main(String... args) throws Exception {
		Object mine = "Hi there".toCharArray();
		long address = addressOf(mine);
		System.out.println("Addess: " + address);

		// Verify address works - should see the characters in the array in the
		// output
		printBytes(address, 27);

		
		//我的测试
		String str="aaa";
		long strAddress1=addressOf(str);
		str="bbb";
		long strAddress2=addressOf(str);
		boolean result=strAddress1==strAddress2;
		System.out.println("str的第一次内存地址是"+strAddress1);
		System.out.println("str的第二次内存地址是"+strAddress2);		
		System.out.println("结论str内存地址"+(!result?"确实":"并没有")+"改变了");
	}
	

	public static long addressOf(Object o) throws Exception {
		Object[] array = new Object[] { o };

		long baseOffset = unsafe.arrayBaseOffset(Object[].class);
		int addressSize = unsafe.addressSize();
		long objectAddress;
		switch (addressSize) {
		case 4:
			objectAddress = unsafe.getInt(array, baseOffset);
			break;
		case 8:
			objectAddress = unsafe.getLong(array, baseOffset);
			break;
		default:
			throw new Error("unsupported address size: " + addressSize);
		}

		return (objectAddress);
	}



	public static void printBytes(long objectAddress, int num) {
		for (long i = 0; i < num; i++) {
			int cur = unsafe.getByte(objectAddress + i);
			System.out.print((char) cur);
		}
		System.out.println();
	}
}