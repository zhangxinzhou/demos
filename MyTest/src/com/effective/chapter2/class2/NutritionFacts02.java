package com.effective.chapter2.class2;

/**
 * 营养成分类--JavaBeans模式
 * @author mylinux
 *
 */
public class NutritionFacts02 {

	private int servingSize;  //(ml)              必要参数（每份的含量）
	private int servings;     //(per container)   必要参数（每罐的含量）
	private int calories;     //                  可选参数（卡路里）
	private int fat;          //(g)               可选参数（脂肪）
	private int sodium;       //(mg)              可选参数（钠含量）
	private int carbohydrate; //(g)               可选参数（碳水化合物）
	
	public NutritionFacts02() {}

	/*getter and setter*/
	public int getServingSize() {
		return servingSize;
	}
	public void setServingSize(int servingSize) {
		this.servingSize = servingSize;
	}
	public int getServings() {
		return servings;
	}
	public void setServings(int servings) {
		this.servings = servings;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public int getFat() {
		return fat;
	}
	public void setFat(int fat) {
		this.fat = fat;
	}
	public int getSodium() {
		return sodium;
	}
	public void setSodium(int sodium) {
		this.sodium = sodium;
	}
	public int getCarbohydrate() {
		return carbohydrate;
	}
    public void setCarbohydrate(int carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	
}
