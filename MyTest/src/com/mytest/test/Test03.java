package com.mytest.test;



public class Test03 {

	private static final int INTEGRAL_MIN=10;
	
	
	public static void main(String[] args) {
		int integral=100;
		lottery(integral);
		System.out.println(integral);
	}
	
	public static int lottery(int integral){
		if(integral>=INTEGRAL_MIN){//积分足够
			
		}
		return integral;//积分不够
	}
	
	
	
	public class prize{//奖品类
		private int type;
		private int integral;
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		public int getIntegral() {
			return integral;
		}
		public void setIntegral(int integral) {
			this.integral = integral;
		}
		

	}
}
