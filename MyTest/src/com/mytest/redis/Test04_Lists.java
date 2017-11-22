package com.mytest.redis;





import redis.clients.jedis.Jedis;

public class Test04_Lists {

	private static Jedis jedis=new Jedis("localhost");
	
	public static void main(String[] args) throws Exception {
		//test();
		test1();
		test2();
	}
	
	public static void test(){
		jedis.lpush("lists", "abc");
		jedis.lpush("lists", "abcd");
		jedis.lpush("lists", "abcde");
		jedis.lpush("lists", "abcdef");
		jedis.lrange("lists", 0, -1).forEach(System.out::println);
		jedis.lset("lists", 0, "test");
		jedis.lrange("lists", 0, -1).forEach(System.out::println);
	} 
	

	public static void test1(){
		jedis.publish("lalala", "你好啊");
		
	}
	
	public static void test2(){
		MyListener listen =new MyListener();
		jedis.subscribe(listen, "lalala");	}
	
	
}
