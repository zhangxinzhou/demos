package com.filetest;

import java.io.File;
import java.util.ArrayList;
//获取全部包名
public class FileTest {

	private static ArrayList<String> filelist=new ArrayList<>();
	private static ArrayList<String> javalist=new ArrayList<>();
	
	
	public static void main(String[] args) {
		String path=System.getProperty("user.dir") + "\\src";
		getFiles(path);
		System.out.println("----------------分割线-------------------");
		filelist.forEach(filename->{
			System.out.println(filename.substring(path.length()+1, filename.length()).replace("\\", "."));
		});
		System.out.println("----------------分割线-------------------");
		javalist.forEach(javaname->{
			System.out.println(javaname.substring(path.length()+1, javaname.length()).replace("\\", "."));
		});
		
	}
	
	static void getFiles(String path){
		File root=new File(path);
		File[] files=root.listFiles();
		for (File file : files) {
			if(file.isDirectory()){//是目录
				getFiles(file.getAbsolutePath());
				filelist.add(file.getAbsolutePath());
			}else{                 //不是目录,理论上就是java文件
				javalist.add(file.getAbsolutePath());
			}
		}
	}
	
	
}
