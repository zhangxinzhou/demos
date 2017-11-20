package com.mytest.redis;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * http://www.cnblogs.com/markhe/p/5689356.html
 * @author zxz
 *
 */
public class Test01 {

	private final static String HOST="localhost";
	
	public static void main(String[] args) {
		test2();
	}
	
	@SuppressWarnings("resource")
	public static void test(){
		Jedis jedis=new Jedis(HOST);
		String result=jedis.ping();
		if("PONG".equals(result)){
			System.out.println("连接redis成功");
			jedis.append("test", "test");
		}
		 //设置 redis 字符串数据
	      jedis.set("runoobkey", "Redis tutorial");
	     // 获取存储的数据并输出
	     System.out.println("Stored string in redis:: "+ jedis.get("runoobkey"));
	}
	
	@SuppressWarnings("resource")
	public static void test1(){
		//连接本地的 Redis 服务
	      Jedis jedis = new Jedis(HOST);
	      System.out.println("Connection to server sucessfully");
	      //存储数据到列表中
	      jedis.lpush("tutorial-list", "Redis");
	      jedis.lpush("tutorial-list", "Mongodb");
	      jedis.lpush("tutorial-list", "Mysql");
	     // 获取存储的数据并输出
	     List<String> list = jedis.lrange("tutorial-list", 0 ,5);
	     for(int i=0; i<list.size(); i++) {
	       System.out.println("Stored string in redis:: "+list.get(i));
	     }
	}
	
	@SuppressWarnings("resource")
	public static void test2(){
		//连接本地的 Redis 服务
		Jedis jedis = new Jedis("localhost");
		System.out.println("Connection to server sucessfully");

		// 获取数据并输出
		Set<String> keys = jedis.keys("*"); 
		Iterator<String> it=keys.iterator() ;   
		while(it.hasNext()){   
			String key = it.next();   
			System.out.println(key);   
		}
	}
}
