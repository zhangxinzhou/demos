package com.example.applicationevent;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MyContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent>{

	private static boolean flag=true;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if(flag){
			flag=false;
			//do something
		}
	}

}
