package com.mytest.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * try-catch-finally与try-with-resource比较
 * @author Administrator
 *
 */
public class Test12 {

	public static void main(String[] args) {

	
		String path="C:\\windows-version.txt";
		oldMethod(path);
		newMethod(path);
	}


	public static void oldMethod(String path){
		InputStreamReader isr=null;
		BufferedReader br=null;
		try {
			isr = new InputStreamReader(new FileInputStream(path), "gbk");
			br=new BufferedReader(isr);
			System.out.println(br.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(isr!=null){
				try {
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}		
		}
	}
	
	public static void newMethod(String path){
		try(
				InputStreamReader isr = new InputStreamReader(new FileInputStream(path), "gbk");
				BufferedReader br = new BufferedReader(isr);
				) 
		{
			System.out.println(br.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
