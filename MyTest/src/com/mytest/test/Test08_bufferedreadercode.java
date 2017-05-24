package com.mytest.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Test08_bufferedreadercode {

	
	public static void main(String[] args) throws Exception {
		String path="C:/Users/Administrator/Desktop";
		String name="sql.txt";
		File file=new File(path, name);
		if(!file.exists()){
			System.out.println("文件不存在");
			return ;
		}
		if(file.exists()){
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
			String line;
			while((line=br.readLine())!=null){
				System.out.println(line);
			}		
			br.close();
		}
	}
}
