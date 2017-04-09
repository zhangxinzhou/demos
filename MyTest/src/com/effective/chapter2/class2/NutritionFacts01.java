package com.effective.chapter2.class2;

/**
 * 营养成分类--重叠构造模式
 * 当有多个参数的时候，会变得很麻烦
 * @author mylinux
 *
 */
public class NutritionFacts01 {

	private final int servingSize;  //(ml)              必要参数（每份的含量）
	private final int servings;     //(per container)   必要参数（每罐的含量）
	private final int calories;     //                  可选参数（卡路里）
	private final int fat;          //(g)               可选参数（脂肪）
	private final int sodium;       //(mg)              可选参数（钠含量）
	private final int carbohydrate; //(g)               可选参数（碳水化合物）
		
	public NutritionFacts01(int servingSize, int servings) {
		this(servingSize,servings,0);
	}
		
	public NutritionFacts01(int servingSize, int servings, int calories) {
		this(servingSize,servings,calories,0);
	}
	
	public NutritionFacts01(int servingSize, int servings, int calories, int fat) {
		this(servingSize,servings,calories,fat,0);
	}

	public NutritionFacts01(int servingSize, int servings, int calories, int fat, int sodium) {
		this(servingSize,servings,calories,fat,sodium,0);
	}

	public NutritionFacts01(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate) {
		super();
		this.servingSize = servingSize;
		this.servings = servings;
		this.calories = calories;
		this.fat = fat;
		this.sodium = sodium;
		this.carbohydrate = carbohydrate;
	}

	/*getter and setter*/
	public int getServingSize() {
		return servingSize;
	}

	public int getServings() {
		return servings;
	}

	public int getCalories() {
		return calories;
	}

	public int getFat() {
		return fat;
	}

	public int getSodium() {
		return sodium;
	}

	public int getCarbohydrate() {
		return carbohydrate;
	}
	
	
}
