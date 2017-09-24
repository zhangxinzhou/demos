package com.test02;

import java.util.HashSet;

public class Test12 {

	/**
	 * StringBuilder型变量sb1和sb2分别指向了堆内的字面量"aaa"和"aaabbb"。把他们都插入一个HashSet。到这一步没问题。但如果后面我把变量sb3也指向sb1的地址，再改变sb3的值，因为StringBuilder没有不可变性的保护，sb3直接在原先"aaa"的地址上改。导致sb1的值也变了。这时候，HashSet上就出现了两个相等的键值"aaabbb"。破坏了HashSet键值的唯一性。所以千万不要用可变类型做HashMap和HashSet键值。
	 * @param args
	 */
	public static void main(String[] args) {
        HashSet<StringBuilder> hs=new HashSet<StringBuilder>();  
        StringBuilder sb1=new StringBuilder("aaa");  
        StringBuilder sb2=new StringBuilder("aaabbb");  
        hs.add(sb1);  
        hs.add(sb2);    //这时候HashSet里是{"aaa","aaabbb"}  
  
        StringBuilder sb3=sb1;  
        sb3.append("bbb");  //这时候HashSet里是{"aaabbb","aaabbb"}  
        System.out.println(hs);  
        

        //-----------------------------------------------------------------------------
        
        
        HashSet<StringBuilder> hs1=new HashSet<StringBuilder>();  
        StringBuilder sbb1=new StringBuilder("aaabbb");  
        StringBuilder sbb2=new StringBuilder("aaabbb");
        
        System.out.println(sbb1.hashCode()==sbb2.hashCode());
        hs1.add(sbb1);  
        hs1.add(sbb2);     
        System.out.println(hs1);
        //----------------------------------------------------------------------------
        
        HashSet<String> hs2=new HashSet<String>();  
        String str1="aaa";  
        String str2="aaabbb";  
        hs2.add(str1);  
        hs2.add(str2);     
  
        
        String str3=str1;  
        str3=str3+"bbb";
        sb3.append("bbb");   
        System.out.println(hs2);  
        
        //hashset元素是不允许重复的
        //hashset是通过hashcode来判断是元素值是否相等的;
	}
}
