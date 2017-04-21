package com.annotation;

import com.annotation.FruitColor.Color;

public class Apple {

	@FruitName(value="FuShi Apple")
	private String fruitName;
	
	@FruitColor(fruitColor=Color.RED)
	private String fruitColor;
	
	@FruitProvider(id=1,user="Tom",address="China")
	private FruitProvider provider;
	
}
