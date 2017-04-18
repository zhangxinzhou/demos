package com.example.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * <ol>
 *    <li> ContextRefreshedEvent：ApplicationContext容器初始化或者刷新时触发该事件</li>
 *    <li> ContextStartedEvent：当使用ConfigurableApplicationContext接口的start()方法启动ApplicationContext容器时触发该事件</li>
 *    <li> ContextClosedEvent：当使用ConfigurableApplicationContext接口的close()方法关闭ApplicationContext容器时触发该事件</li>
 *    <li> ContextStopedEvent: 当使用ConfigurableApplicationContext接口的stop()方法停止ApplicationContext容器时触发该事件</li>
 *    <li> 其他还有很多,可以通过打印event.getClass().getSimpleName()看到!</li>
 * <ol>
 *
 */
@Component
public class MyApplicationEvent implements ApplicationListener<ApplicationEvent> {
	
	private Logger log=LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		System.out.println("##########################################################");
		log.info(event.getClass().getSimpleName());
		
	}

}
