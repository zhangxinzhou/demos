package com.test02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class Test07 {

	public static void main(String[] args) {

		getStudents().forEach(stu->{
			System.out.printf("姓名:%s,学号:%s,性别:%s",stu.getName(),stu.getNumber(),stu.getSex()+System.getProperty("line.separator"));
		});
		System.out.printf("$%,f\n", 123456.78);//金钱表示法
		System.out.printf("%x\n", 16); //输出16进制数
		System.out.printf("%#x\n", 16);//输出带0x标识的16进制数
		showProperties();
	}
	
	public static List<Student> getStudents(){
		List<Student> sl=new ArrayList<>();
		while(sl.size()<10){
			int size=sl.size()+1;
			Student stu=new Student("小白"+size,String.valueOf(size), size%2==0?Sex.男:Sex.女);
			sl.add(stu);
		}
		return sl;
	}
	
	public static void showProperties(){
		Properties priperties= System.getProperties();
		Set<Entry<Object, Object>> ps= priperties.entrySet();
		Map<String, Integer> map=new HashMap<>();
		ps.forEach(p->{
			if(p.getKey().toString().length()>(map.get("maxkey")!=null?map.get("maxkey"):0)){
				map.put("maxkey", p.getKey().toString().length());
			}
			if(p.getValue().toString().length()>(map.get("maxvalue")!=null?map.get("maxvalue"):0)){
				map.put("maxvalue", p.getValue().toString().length());
			}
		});
		int maxkey=map.get("maxkey");
		int maxvalue=map.get("maxvalue");
		StringBuffer hf=new StringBuffer();
		hf.append(" ");
		StringBuffer body=new StringBuffer();
		for (int i = 0; i < maxkey+maxvalue+2; i++) {
			hf.append("_");
			body.append(" ");
		}
		System.out.println(hf);
		ps.forEach(p->{
			StringBuffer temp=new StringBuffer(body.toString());
			String key="|"+p.getKey();
			String value="|"+p.getValue();
			temp.replace(0, key.length(), key);
			temp.replace(maxkey+1, maxkey+1+value.length(), value);
			temp.append(" |");
			if(!p.getKey().equals("line.separator")){
				System.out.println(temp.toString());
			}
			
		});
		System.out.println(hf);
	}
}
