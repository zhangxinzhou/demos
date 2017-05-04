package com.proxylearn.service.impl;

import com.proxylearn.service.Animal;

public class Dog implements Animal{

	@Override
	public void say() {
		System.out.println("汪汪,i am a dong");
	}

	@Override
	public int life() {
		return 12;
	}

}
