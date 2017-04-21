package com.test02;

/**
 * 自写Base62进制方法
 * @author user
 *
 */
public class MyBase62 {

	private static final String baseDigits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final char[] digitsChar = baseDigits.toCharArray();
	
	//加密(转换成62进制)
	//计算方法,比如6200除以62=100..0,100大于62再除62=1..38,1小于62所以循环结束.
	//那么就是最后一位是0,倒数第二位是38,倒数第三位是1,所以就是1,38,0就是1C0
	public static String encode(Long number){
		StringBuffer sb=new StringBuffer();
		boolean flag=number>62;
		do {
			sb.insert(0, getStr((int) (number%62)));
			number=number/62;
		} while (number>62);
		if(flag){
			sb.insert(0, getStr(number.intValue()));
		}		
		return sb.toString();
	}
	
	//解密(还原)
	//计算方法,比如6200=1c0=1*62^2+c*62^1+0*62^0=1*62^2+38*62^1+0*62^0=6200
	public static Long decode(String str){
		Long result=0l;
		char[] c=str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			result+=getIndex(c[i])*(long)Math.pow(62, c.length-i-1);			
		}
		return result;
	}
	
	//比如38将得到C
	public static char getStr(int index){
		return digitsChar[index];
	}
	
	//比如C将得到38
	public static int getIndex(char ch){
		for (int i = 0; i < digitsChar.length; i++) {
			if(ch==digitsChar[i]){
				return i;
			}
		}
		return 0;
	}
	
	
	public static void main(String[] args) {
		//test
		//System.out.println(getStr(38));
		//System.out.println(getIndex('C'));
		long num=6200l;
		String str="1C0";
		System.out.println(Base62.encode(num));
		System.out.println(MyBase62.encode(num));
		System.out.println(Base62.decode(str));
		System.out.println(MyBase62.decode(str));
	}
}
