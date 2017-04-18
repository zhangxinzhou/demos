package com.example.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/*
 * 当使用ConfigurableApplicationContext接口的close()方法关闭ApplicationContext容器时触发该事件
 */
@Component
public class MyContextClosedEvent implements ApplicationListener<ContextClosedEvent> {
	
	private Logger log=LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		log.info("[{}] : [{}]",ContextClosedEvent.class.getSimpleName(),ContextClosedEvent.class.getSimpleName());
	}

}
