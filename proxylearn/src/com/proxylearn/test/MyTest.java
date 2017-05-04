package com.proxylearn.test;

import com.proxylearn.service.Animal;
import com.proxylearn.service.impl.Dog;
import com.proxylearn.service.impl.Humen;

public class MyTest {

	
	
	public static void main(String[] args) {
		Animal dog=new Dog();
		Animal humen=new Humen();
		System.out.println(dog.getClass().isInterface());
		System.out.println(humen.getClass().isInterface());
	}
}
