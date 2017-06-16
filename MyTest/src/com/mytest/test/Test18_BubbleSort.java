package com.mytest.test;

import java.util.Arrays;

/**
 * 冒泡
 * @author Administrator
 *
 */
public class Test18_BubbleSort {

	
	public static void main(String[] args) {
	
		int [] arr={1000,55,12,0,1,1,1,23,-1,-20,555};
		//test1(arr);
		//test2(arr);
		test3(arr);
	}
	

	
	/**
	 * 冒泡
	 * @param arr
	 */
	public static void test1(int[] arr){
		int len=arr.length;
		while(len-->1){
			int x=0;
			while(x++<len){
				if(arr[x-1]>arr[x]){
					int max=arr[x-1];
					arr[x-1]=arr[x];
					arr[x]=max;
				}
				printResult(arr);
			}
		}
	}
	
	/**
	 * 冒泡
	 * @param arr
	 */
	public static void test2(int[] arr){
		int len=arr.length;
		while(len-->1){
			for (int i = 0; i < len; i++) {
				if(arr[i]>arr[i+1]){
					int max=arr[i];
					arr[i]=arr[i+1];
					arr[i+1]=max;
				}
				printResult(arr);
			}
		}
	}
	
	/**
	 * 其实根本没必要自己写
	 * @param arr
	 */
	public static void test3(int[] arr){
		Arrays.sort(arr);//默认升序
		printResult(arr);
	}
	
	private static void printResult(int[] arr){
		for (int i : arr) {
			System.out.print(i+"\t");
		}
		System.out.println();
	}
}
