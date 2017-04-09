package com.effective.chapter2.class2;

/**
 * 营养成分类--Builder模式
 * @author mylinux
 *
 */
public class NutritionFacts03 {

	private final int servingSize;  //(ml)              必要参数（每份的含量）
	private final int servings;     //(per container)   必要参数（每罐的含量）
	private final int calories;     //                  可选参数（卡路里）
	private final int fat;          //(g)               可选参数（脂肪）
	private final int sodium;       //(mg)              可选参数（钠含量）
	private final int carbohydrate; //(g)               可选参数（碳水化合物）
		
	public static class Builder{
		//必要参数
		private final int servingsSize;
		private final int servings;
		//可选参数 - 赋初值
		private int calories=0;     
		private int fat=0;          
		private int sodium=0;       
		private int carbohydrate=0;
		
		public Builder(int servingsSize, int servings) {
			this.servingsSize = servingsSize;
			this.servings = servings;
		}
		
		public Builder calories(int val){
			calories=val;
			return this;
		}
		public Builder fat(int val) {
			fat = val;
			return this;
		} 
		public Builder sodium(int val) {
			sodium = val;
			return this;
		} 
		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		} 
	}
	
	private NutritionFacts03(Builder builder){
		servingSize=builder.servingsSize;
		servings=builder.servings;
		calories=builder.calories;
		fat=builder.fat;
		sodium=builder.sodium;
		carbohydrate=builder.carbohydrate;
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
