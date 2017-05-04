package com.proxylearn.proxy;

import com.proxylearn.service.Animal;
import com.proxylearn.util.SplitLineUtil;

/**
 * 静态代理的局限性,要实现animal接口,如果这个接口的方法很多,就要写很多代码
 * @author Administrator
 *
 */
public class StaticProxy implements Animal{
	
	private Animal animal;	
	public StaticProxy(Animal animal) {
		super();
		this.animal = animal;
	}

	@Override
	public void say() {
		SplitLineUtil.printSplitLine(this.getClass().getSimpleName());
		animal.say();
		SplitLineUtil.printSplitLine(this.getClass().getSimpleName());
	}

	@Override
	public int life() {
		return animal.life();
	}	
	
	
}
