package com.proxylearn.test;

import java.lang.reflect.Proxy;

import com.proxylearn.proxy.CglibProxy;
import com.proxylearn.proxy.InvocationHandlerProxy;
import com.proxylearn.proxy.StaticProxy;
import com.proxylearn.service.Animal;
import com.proxylearn.service.impl.Dog;
import com.proxylearn.service.impl.Humen;

/**
 * @author Administrator
 */
public class ProxyTest {

	/*测试*/
	public static void main(String[] args) {
		
		Animal animal=null;
		animal=new Humen();
		test(animal);
		animal=new Dog();
		test(animal);

		
	}
	
	//传递接口
	public static void test(Animal animal){
		//Cglib方式
		CglibProxy cglibProxy=new CglibProxy();		
		Animal cglibTarget=(Animal) cglibProxy.getProxy(animal.getClass());
		cglibTarget.say();
		printLife(animal, CglibProxy.class, cglibTarget.life());
		//InvocationHandler方式
		InvocationHandlerProxy invocationHandlerProxy=new InvocationHandlerProxy(animal);
		Animal invocationHandlerAnimal=(Animal) Proxy.newProxyInstance(animal.getClass().getClassLoader(), animal.getClass().getInterfaces(), invocationHandlerProxy);
		invocationHandlerAnimal.say();
		printLife(animal, InvocationHandlerProxy.class, invocationHandlerAnimal.life());
		//静态代理方式
		StaticProxy staticProxy=new StaticProxy(animal);
		staticProxy.say();
		printLife(animal, StaticProxy.class, staticProxy.life());
	}
	
	/*打印寿命*/
	private static void printLife(Animal animal,Class<?> cla,int life){
		String name=animal.getClass().getSimpleName();
		System.out.println(name+"'s lefe is("+cla.getSimpleName()+")"+life+" year");
	}
}
