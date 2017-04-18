package com.example.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/*
 * 当使用ConfigurableApplicationContext接口的start()方法启动ApplicationContext容器时触发该事件
 */
@Component
public class MyContextStartedEvent implements ApplicationListener<ContextStartedEvent>{
	
	private Logger log=LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ContextStartedEvent event) {
		log.info("[{}] : [{}]",ContextStartedEvent.class.getSimpleName(),ContextStartedEvent.class.getSimpleName());
	}

}
