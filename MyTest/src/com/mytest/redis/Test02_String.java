package com.mytest.redis;


import java.util.List;

import redis.clients.jedis.Jedis;

public class Test02_String {

	private static Jedis jedis=new Jedis("localhost");
	
	
	public static void main(String[] args) throws InterruptedException {
		test6();
	}
	
	public static void test(){
		jedis.set("key", "value");
		String result=jedis.get("key");
		System.out.println(result);
	}
	
	/**有不覆盖,没有就增加**/
	public static void test1(){
		Long result=jedis.setnx("key", "new value");
		if(result==0){
			System.out.println("不覆盖,因为已经存在了");
		}else{
			System.out.println("已经设定key的新值");
		}
		System.out.println(jedis.get("key"));
	}
	/**key多长时间后过期**/
	public static void test2() throws InterruptedException{
		jedis.setex("time", 5, "5秒后过期");
		System.out.println("time值是:"+jedis.get("time"));
		Thread.sleep(6000);
		System.out.println("6秒之后.......");
		System.out.println("time值是:"+jedis.get("time"));
	}
	
	public static void test3(){
		jedis.set("add", "100");
		jedis.set("deduct", "100");
		int i=10;
		while(i-->0){
			Long result1=jedis.incr("add");
			Long result2=jedis.decr("deduct");
			System.out.println("result1:"+result1+",result2:"+result2);
		}
	}
	
	public static void test4(){
		System.out.println("原值:"+jedis.get("add"));
		System.out.println("加50:"+jedis.incrBy("add", 50));
		System.out.println("减25:"+jedis.decrBy("add", 25));
	}
	
	public static void test5(){
		jedis.set("test", "abcdefghig");
		System.out.println(jedis.setrange("test", 0, "test"));
		System.out.println(jedis.getrange("test", 2, 6));
		jedis.append("test", "append something");
		System.out.println("test append something");
		System.out.println(jedis.get("test"));
		System.out.println("test value的长度"+jedis.strlen("test"));
	}
	
	public static void test6(){
		jedis.flushAll();
		//一次设置多个key的值，成功返回ok，失败返回0，要成功都成功，要不成功全部失败。
		jedis.mset("key1","value1","key2","value2","key3","value3");
		List<String> result=jedis.mget("key1","key2");
		//一次设置多个key的值，成功返回ok，失败返回0，不覆盖已经存在的值，要成功都成功，要失败都失败。
		jedis.msetnx("key4","value4","key5","value5");//会成功,返回1
		jedis.msetnx("key6","value6","key1","value1");//会失败,返回0
		result.forEach(System.out::println);
		jedis.keys("*").forEach(System.out::println);
	}
}
