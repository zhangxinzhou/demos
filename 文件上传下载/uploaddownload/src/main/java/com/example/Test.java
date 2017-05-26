package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;


public class Test {

	private static final String FILE_PATH="C:\\Users\\Administrator\\Desktop\\";
	
	public static void main(String[] args) {
		getFileList();
	}
	
	public static void test(){
		String filePath="C:\\Users\\Administrator\\Desktop\\新建文本文档.txt";
		String targetPath="C:\\Users\\Administrator\\Desktop\\new.txt";		
		try(
				FileInputStream fis=new FileInputStream(filePath);
				FileOutputStream fos=new FileOutputStream(targetPath);
				){
			byte[] b=new byte[1024];
			while(fis.read(b)!=-1){
				fos.write(b);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test1(){
		String filePath="C:\\Users\\Administrator\\Desktop\\新建文本文档.txt";
		String targetPath="C:\\Users\\Administrator\\Desktop\\new1.txt";	
		
		try (
				FileReader fr=new FileReader(filePath);
				FileWriter fw=new FileWriter(targetPath);
				){
			char [] cbuf=new char[1024];
			while(fr.read(cbuf)!=-1){
				fw.write(cbuf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getFileList(){
		File file=new File(FILE_PATH);
		FilenameFilter fnf=(File dir, String name)->{
			return name.endsWith(".txt")||
				   name.endsWith(".jpg")||
				   name.endsWith(".png");
		};
		File[] files=file.listFiles(fnf);
		for (File f : files) {
			System.out.println(f);
			System.out.println(f.getName());
		}
	}
}
