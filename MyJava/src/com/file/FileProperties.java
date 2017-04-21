package com.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileProperties {

	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	
	public static void main(String[] args) {
		String path="C:/user/test.pdf";
		test(path);
	}
	
	
	public static List<File> getFileList(String path){
		File file=new File(path);
		List<File> result=new ArrayList<>();
		String[] names=file.list();
		for (int i = 0; i < names.length; i++) {
			result.add(new File(path+"/"+names[i]));
		}
		return result;
	}
	
	public static String getCreateTime(File file){
		try {
			Process p=Runtime.getRuntime().exec("cmd /C dir "           
			        + file.getPath()  
			        + "/tc");
			InputStream is=p.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String line;
			while((line=br.readLine())!=null){
				if(line.endsWith(".pdf")){
					return line.substring(0,17);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void test(String path){
		List<File> fileList=getFileList(path);
		for (int i = 0; i < fileList.size(); i++) {
			System.out.println("文件名称:"+fileList.get(i).getName());
			System.out.println("绝对路径:"+fileList.get(i).getAbsolutePath());
			System.out.println("路径:"+fileList.get(i).getPath());
			System.out.println("此径名的分区的大小(字节):"+fileList.get(i).getTotalSpace());
			System.out.println("此径名的分区的剩余空间(字节):"+fileList.get(i).getFreeSpace());
			System.out.println("文件夹:"+fileList.get(i).getParentFile());
			System.out.println("文件大小(字节):"+fileList.get(i).length());
			System.out.println("创建时间:"+getCreateTime(fileList.get(i)));
			System.out.println("最后一次修改时间:"+sdf.format(new Date(fileList.get(i).lastModified())));
		}
	}
}
