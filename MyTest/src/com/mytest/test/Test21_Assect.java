package com.mytest.test;

public class Test21_Assect {

	
	public static void main(String[] args) {
		test1(-5);
        test2(-3);
        //在eclipse中，必须开启相关设置。具体在偏好设置，选择Installed JREs，选择相关版本，点击edit，在Default VM arguments里面输入-ea就可以了。
	}
	
	private static void test1(int a){
        assert a > 0;
        System.out.println(a);
    }
    private static void test2(int a){
        assert a > 0 : "something goes wrong here, a cannot be less than 0";
        System.out.println(a);
    }
}
