package com.effective.chapter2.class1;

/**
 * 静态工厂方法替代构造器
 * <p>优点：</p>
 * <ol>
 *   <li>静态工厂方法有自己的名称</li>
 *   <li>不必每次调用他们的时候都创建一个新对象</li>
 *   <li>他们可以返回类型的任何子类型的对象</li>
 *   <li>在创建参数化类型实例的时候，它们使代码变得更加简洁</li>
 * </ol>
 * <p>优点：</p>
 * <ol>
 *   <li>如果不含有公有的或者受保护的构造器，就不能被子类化</li>
 *   <li>它们于其他静态方法实际上没有任何区别</li>
 * </ol>
 * @author mylinux
 *
 */
public class StaticFactoryMethod {

	public static void main(String[] args) {
		boolean b=true;
		/*构造器*/
		Boolean b1=new Boolean(b);
		Boolean b2=new Boolean(b);
		/*静态工厂方法*/
		Boolean b3=Boolean.valueOf(b);
		Boolean b4=Boolean.valueOf(b);
		/*之间的比较*/
		System.out.println(b1==b2);//采用构造器方法new的两个并不想等
		System.out.println(b3==b4);//采用静态工厂方法创建的两个相等（当然参数要一样）
	}
	


}
