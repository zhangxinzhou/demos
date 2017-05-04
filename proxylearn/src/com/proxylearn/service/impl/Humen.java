package com.proxylearn.service.impl;

import com.proxylearn.service.Animal;

public class Humen implements Animal{

	@Override
	public void say() {
		System.out.println("hello,i am a person");		
	}

	@Override
	public int life() {
		return 70;
	}

}
