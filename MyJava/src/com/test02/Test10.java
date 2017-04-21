package com.test02;

import java.util.Map.Entry;
import java.util.Set;

public class Test10 {

	
	public static void main(String[] args) {
		Set<Entry<Object, Object>> ps=System.getProperties().entrySet();
		String line="                             ";
		ps.forEach(p->{
			StringBuffer sb=new StringBuffer(line);
			String key=p.getKey().toString();
			sb.replace(0, key.length(), key);
			sb.append(": "+p.getValue());
			System.out.println(sb.toString());
		});
		
	}
}
