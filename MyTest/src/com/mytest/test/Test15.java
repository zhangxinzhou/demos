package com.mytest.test;


public class Test15 {

	
	public static void main(String[] args) {
		
		String groupId="c4v45ooihv481-10-1-";
		test1(groupId);
		test(groupId);
	}
	
	public static void test(String groupId){
		Long max=1L;
		int num=0;
		while(num++<120){
			String[] str =groupId.split("-");
			String zstr = str[str.length - 1];
			if(Long.valueOf(zstr)>max){
				max=Long.valueOf(zstr);
			}
			
			if(max>9){
				groupId=groupId.substring(0,groupId.length()-3)+(max+1)+"-";
			}else{
				groupId=groupId.substring(0,groupId.length()-2)+(max+1)+"-";
			}
			System.out.println(groupId);
		}
	}
	
	public static void test1(String groupId){
		Long max=1L;
		int num=0;
		while(num++<12000){
			String[] str =groupId.split("-");
			String zstr = str[str.length - 1];
			if(Long.valueOf(zstr)>max){
				max=Long.valueOf(zstr);
			}
			StringBuffer sb=new StringBuffer();
			for (int j = 0; j < str.length; j++) {				
				if(j!=str.length - 1){
					sb.append(str[j]);
				}else{
					sb.append(max+1);
				}
				sb.append("-");
			}
			groupId=sb.toString();
			System.out.println(groupId);
		}
	}
}
