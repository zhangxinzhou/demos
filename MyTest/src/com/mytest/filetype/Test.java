package com.mytest.filetype;

import java.io.IOException;

public class Test {

	
	public static void main(String[] args) throws IOException {
		String path="C:\\Users\\Administrator\\Desktop\\QQ截图201.gif";
		FileType type=FileTypeJudge.getType(path);
		System.out.println(type);
	}
}
