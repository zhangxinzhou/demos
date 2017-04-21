package com.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		getFruitInfo(Apple.class);
	}
	
	public static void getFruitInfo(Class<?> cla){
		Field[] fields=cla.getDeclaredFields();
		Map<String, Object> map=new HashMap<>();
		for (Field field : fields) {
			if(field.isAnnotationPresent(FruitName.class)){
				map.put("FruitName", field.getAnnotation(FruitName.class).value());
			}
			if(field.isAnnotationPresent(FruitColor.class)){
				map.put("FruitColor", field.getAnnotation(FruitColor.class).fruitColor());
			}
			if(field.isAnnotationPresent(FruitProvider.class)){
				map.put("FruitProvider.id", field.getAnnotation(FruitProvider.class).id());
				map.put("FruitProvider.user", field.getAnnotation(FruitProvider.class).user());
				map.put("FruitProvider.address", field.getAnnotation(FruitProvider.class).address());				
			}
		}
		System.out.println(map);
	}
}
