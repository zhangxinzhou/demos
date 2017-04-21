package com.test01;

public class test1 {

	public static void main(String[] args) {
		halfFind(new int[]{1,30},29);
		halfFind2(1,30,555);
	}
	
	//折半查找
	public static void halfFind(int [] range,int target){
		if(range.length==2){
			int min=range[0]; 
			int max=range[1];
			if(target>min&&target<max){
				int mid=(min+max)/2;
				int n=0;
				while(mid!=target){
					n++;
					if(mid>target){
						max=mid;
						System.out.println("您猜的数字:"+mid+"比目标数字:"+target+"大");					
					}else{
						min=mid;
						System.out.println("您猜的数字:"+mid+"比目标数字:"+target+"小");
					}
					mid=(min+max)/2;
				}
				System.out.println("您猜的数字:"+mid+"目标数字:"+target+",一共猜了"+(n+1)+"次");
			}			
		}
	}
	
	public static void halfFind2(int min,int max,int target){
		if(target>min&&target<max){
			int mid=(min+max)/2;
			int n=0;
			while(mid!=target){
				if(mid>target){
					max=mid;
				}else{
					min=mid;
				}
				mid=(min+max)/2;
				n++;
			}
			System.out.println((n+1)+"times");
		}		
	}
}
