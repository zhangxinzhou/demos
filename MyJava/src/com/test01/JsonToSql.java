package com.test01;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 如果前端发送的查询类似filter=%7B%22age%22%3A%2212%22%2C%22name%22%3A%22zx1%22%7D
 *                filter:{"age":"12","name":"zx1"}
 * 可以尝试自己拼接sql:select * from table where age='12' and name='zx1'              
 * @author user
 *
 */
public class JsonToSql {

	private String str;
	private long startTime;
	
	@Before
	public void before(){
		String strArray[]={"{\"name\":\"张三\"}",
				           "{\"name\":\"张三\",\"sex\":\"男\"}",
				           "{\"name\":\"张三\",\"sex\":\"男\",\"age\":\"20\"}",
				           "",
				           null,
				           "{}"
		                  };
		str=strArray[2];
		System.out.println("jsonStr:"+str);
		System.out.println("开始处理...");
		startTime=System.nanoTime();
	}
	
	@After
	public void after(){
		System.out.println("处理完毕...");
		System.err.println("耗时:"+(System.nanoTime()-startTime)+"ns");
	}
	
	@Test
	public void doString1(){//字符串分组截取
		StringBuffer sb=new StringBuffer("select * from table");
		String tempStr=str;
		if(tempStr!=null&&tempStr.contains("\"")){
			sb.append(" where 1=1");
			tempStr=tempStr.replace("{", "").replace("}", "").replace("\"", "").replace(":", "=");
			String temp[]=tempStr.split(",");
			for (String string : temp) {
				string=string.replace("=", "='")+"'";
				sb.append(" and "+string);
			}			
		}	
		System.err.println("doString1处理后:"+sb.toString());		
	}
	
	@Test
	public void doString2(){//替换大法,似乎这个速度更快
		StringBuffer sb=new StringBuffer("select * from table");
		String tempStr=str;
		if(tempStr!=null&&tempStr.contains("\"")){
			tempStr=" where 1=1 and "+str.replace("{", "").replace("}", "").replace("\"", "").replace(":", "='")
					.replace(",", "' and ")+"'";
			sb.append(tempStr);			
		}	
		System.err.println("doString2处理后:"+sb.toString());		
	}
	
}
