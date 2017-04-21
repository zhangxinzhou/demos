package com.test02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

/**
 * 对xx.peoperties文件进行增删改查操作
 * @author user
 *
 */
public class Test02 {

	
	private static String propertiesPath="C:\\Users\\user\\Desktop\\pdf\\gp.properties";//文件名
	private static Properties props=new Properties();
	static{
		try {
			props.load(new FileInputStream(propertiesPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//根据key读value
	public static String readValue(String key){//文件路径,key
		try {
			return props.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//更新/增加一对key-value
	public static boolean writeProperties(String key,String value){
		try {
			props.setProperty(key, value);
			OutputStream fos = new FileOutputStream(propertiesPath);
			props.store(fos, "Update '" + key + "' value");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//删除一对key-values
	public static boolean delKey(String key){
		try {
			props.remove(key);
			OutputStream fos = new FileOutputStream(propertiesPath);
			props.store(fos, "Delete '" + key + "' value");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//读取全部的keys和values
	public static List<Map<String, Object>> readAll(){
		Set<Entry<Object, Object>> all=props.entrySet();
		List<Map<String, Object>> ml=new ArrayList<>();
		for (Entry<Object, Object> entry : all) {
			Map<String, Object> map=new HashMap<>();
			map.put("group", entry.getKey());
			map.put("product", entry.getValue());
			ml.add(map);
		}
		return ml;
	}
	
	@Test
	public void test(){
		System.out.println(writeProperties("B3_N4A", "12345"));
		System.out.println(readValue("B3_N4B1111111"));
		System.out.println(readAll());
		System.out.println(delKey("B3_N4A"));
		System.out.println(readAll());
	}
}
