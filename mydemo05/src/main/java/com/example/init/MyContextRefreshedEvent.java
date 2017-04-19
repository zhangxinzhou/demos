package com.example.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/*
 * ApplicationContext容器初始化或者刷新时触发该事件
 */
@Component
public class MyContextRefreshedEvent implements ApplicationListener<ContextRefreshedEvent>{
	
	private Logger log=LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("[{}] : [{}]",ContextRefreshedEvent.class.getSimpleName(),ContextRefreshedEvent.class.getSimpleName());
		//root application context 没有parent，他就是老大.
		if(event.getApplicationContext().getParent()==null){
			System.out.println("防止运行多次!");
		}
	}

}
