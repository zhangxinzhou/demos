package com.example.schedujob;

import java.time.LocalDateTime;

public class TestJob implements Runnable{

	
	public void Test01(){
		System.out.println("Test01:"+LocalDateTime.now());
	}
	
	public void Test02(){
		System.out.println("Test02:"+LocalDateTime.now());
	}

	@Override
	public void run() {
		System.out.println("run:"+LocalDateTime.now());		
	}
}
