package com.example.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

/*
 * 当使用ConfigurableApplicationContext接口的stop()方法停止ApplicationContext容器时触发该事件
 */
@Component
public class MyContextStopedEvent implements ApplicationListener<ContextStoppedEvent>{
	
	private Logger log=LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ContextStoppedEvent event) {
		log.info("[{}] : [{}]",ContextStoppedEvent.class.getSimpleName(),ContextStoppedEvent.class.getSimpleName());
	}

}
