package com.mytest.redis;



import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import redis.clients.jedis.Jedis;

public class Test03_Hashs {

	private static Jedis jedis=new Jedis("localhost");
	
	public static void main(String[] args) throws Exception {
		User u=new User(1L, "zhang3", "man");
		//test(u);
		test1(u);
		test2(u);
	}
	
	public static void test(User u){
		jedis.hset("user:"+u.getId(),"id", String.valueOf(u.getId()));
		jedis.hset("user:"+u.getId(),"name",u.getName());
		jedis.hset("user:"+u.getId(),"sex",u.getSex());
		jedis.hget("user:"+u.getId(), "name");
		jedis.hgetAll("user:"+u.getId()).forEach((k,v)->{
			System.out.println("field:"+k+",value:"+v);
		});
	} 
	
	//序列化与反序列化
	public static void test1(User u){
		//将对象序列号并存入redis
		byte[] b=("test1.user:"+u.getId()).getBytes();
		jedis.set(b, SerializeUtil.serialize(u));
		//从redis取出来并反序列华
		byte[] person = jedis.get(b); 
		User temp=(User) SerializeUtil.unserialize(person);
		System.out.println(temp);
	}
	
	//使用Map传递,也是转来转去
	@SuppressWarnings("unchecked")
	public static void test2(User u) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException{
		String key="test2.user:"+u.getId();
		Map<String, String> hash=BeanUtils.describe(u);
		jedis.hmset(key, hash);
		
		Map<String, String> map=jedis.hgetAll(key);
		Object obj = User.class.newInstance(); 
		BeanUtils.populate(obj, map);
		User temp=(User)obj;
		System.out.println(temp);
	}
	
}
