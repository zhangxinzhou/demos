package com.mytest.test;

/**
 * question:数组之存在0和1,如何只遍历一次就完成排序
 * 思路:
 * 1.从数组首开始
 * 2.遇到1就将1变为0(只做一次),然后从数组尾部开始遍历遇到1就变为0(只做一次),完成一个循环
 * 3.从数组首的下个位置和数组尾的下一个位置,循环第二部
 * @author Administrator
 *
 */
public class Test17_0606Interview {

	
	public static void main(String[] args) {
		
		short arr[]={0,1,1,0,0,1,1,1,1,0,0};
		//short arr[]={1,1,1,1,1,1,1,1,1,1,1}; 校验方法
		//short arr[]={0,0,0,0,0,0,0,0,0,0,0}; 校验方法
		test(arr);
	}
	
	public static void test(short arr[]){
		int len=arr.length;
		int location=len-1;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]==1&&i<location){
				arr[i]=0;
				for (int j = location; j >= 0; j--) {
					if(arr[j]==0){
						arr[j]=1;
						location=j;
						break;
					}
				}
			}
		}
		
		for (short s : arr) {
			System.out.print(s+"\t");
		}
	}
}
