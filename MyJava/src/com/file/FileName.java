package com.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileName {

	
	public static void main(String[] args) {
		String path="C:\\Users\\user\\Desktop\\pdf";
		String[] fileNames=getFileName(path);
		System.out.println(fileNames==null);
		for (String string : fileNames) {
			System.out.println(string);
		}
		
		
		List<String> fileNamelist=getFileName2(path,".pdf");
		System.out.println(fileNames==null);
		for (String string : fileNamelist) {
			System.out.println(string);
		}
		
		
	}
	
	public static String [] getFileName(String path){
		File file=new File(path); 
		String[] fileNames=file.list();
		return fileNames;
	}
	
	public static List<String> getFileName2(String path,String type){
		File file=new File(path);
		String[] fileNames=file.list();
		List<String> result=new ArrayList<>();
		if(fileNames!=null){
			for (String str : fileNames) {
				if(str.endsWith(type)){
					result.add(str);
				}
			}
		}		
		return result;
	}
}
