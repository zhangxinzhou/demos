package com.test;

//画菱形
public class TestPrint {

	
	public static void main(String[] args) {
		//RhombusPrint(11," ","*");
		//RhombusPrint2(11," ","*");
		//RhombusPrint3(11," ","*");
		RhombusPrint4(11,"*"," ");
		//SquarePrint(10,"*");
	}
	
	public static void RhombusPrint(int n,String spaceStr,String starsStr){
		int mid=n/2;
		for (int i = 0; i < n; i++) {
			int spaces=Math.abs(mid-i);
			int stars=n-2*spaces;
			for (int j = 0; j < spaces; j++) {
				System.out.print(spaceStr);
			}
			for (int j = 0; j < stars; j++) {
				System.out.print(starsStr);
			}
			for (int j = 0; j < spaces; j++) {
				System.out.print(spaceStr);
			}
			System.out.println();
		}
	}
	
	public static void RhombusPrint2(int n,String spaceStr,String starsStr){
		int mid=n/2;
		for (int i = 0; i < n; i++) {
			int spaces=Math.abs(mid-i);
			for (int j = 0; j <n; j++) {
				if(j>=spaces&&j<(n-spaces)){
					System.out.print(starsStr);
				}else{
					System.out.print(spaceStr);
				}
			}
			System.out.println();
		}
	}
	
	public static void RhombusPrint3(int n,String spaceStr,String starsStr){
		int mid=(n-1)/2;
		for (int i = 0; i <n; i++) {
			for (int j = 0; j < n; j++) {
				int min=Math.abs(i-mid);
				int max=n-1-min;
				if(j>=min&&j<=max){
					System.out.print(starsStr);
				}else{
					System.out.print(spaceStr);
				}
			}
			System.out.println();
		}
	}
	
	public static void RhombusPrint4(int n,String spaceStr,String starsStr){
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < n*n; i++) {	
			int j=i/n;
			
			int min=Math.abs(j-(n-1)/2);
			int max=n-1-min;
			
			min=min+j*n;
			max=max+j*n;

			if(i>=min&&i<=max){
				sb.append(starsStr);
			}else{
				sb.append(spaceStr);
			}
			if((i+1)%n==0){
				sb.append("\r\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static void SquarePrint(int size,String str){
	    for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
