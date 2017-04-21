package com.file;

import java.io.File;

public class FileDelete {

	public static void main(String[] args) {
		
		String path="C:\\Users\\user\\Desktop\\新建文件夹\\a.txt";
		System.out.println(isExists(path));
		System.out.println(isFile(path));
		//System.out.println(del(path));
	}
	
	public static boolean isExists(String path){
		File file=new File(path);
		return file.exists();
	}
	//判断是不是文件
	public static boolean isFile(String path){
		File file=new File(path);
		return file.isFile();
	}
		
	public static boolean del(String path){
		File file=new File(path);
		return file.delete();
	}
	
	public static boolean delFile(String path){
		File file=new File(path);
		if(file.exists()&&file.isFile()){
			return file.delete();
		}
		return false;
	}

}
