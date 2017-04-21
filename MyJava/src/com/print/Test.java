package com.print;

public class Test {

	
	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 0; i < 5; i++) {
			Thread.sleep(1000);
			int result=PrintUtil.UtilPrint("0","");
			System.out.println(result);
		}
		
		/*int result=PrintUtil.UtilPrint("0","SATO CL4NX 305dpi","192.168.14.250");
		System.out.println(result);*/
	}
}
