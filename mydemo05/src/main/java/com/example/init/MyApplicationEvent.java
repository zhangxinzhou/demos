package com.example.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
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
		// 在这里可以监听到Spring Boot的生命周期
        if (event instanceof ApplicationEnvironmentPreparedEvent) { // 初始化环境变量 
        	log.info("*****->[{}]","初始化环境变量");
        }
        else if (event instanceof ApplicationPreparedEvent) { // 初始化完成 
        	log.info("*****->[{}]"," 初始化完成 ");
        }
        else if (event instanceof ContextRefreshedEvent) { // 应用刷新 
        	log.info("*****->[{}]","应用刷新 ");
        }
        else if (event instanceof ApplicationReadyEvent) {// 应用已启动完成 
        	log.info("*****->[{}]","应用已启动完成");
        }
        else if (event instanceof ContextStartedEvent) { // 应用启动，需要在代码动态添加监听器才可捕获 
        	log.info("*****->[{}]","应用启动，需要在代码动态添加监听器才可捕获 ");
        }
        else if (event instanceof ContextStoppedEvent) { // 应用停止 
        	log.info("*****->[{}]","应用停止 ");
        }
        else if (event instanceof ContextClosedEvent) { // 应用关闭 
        	log.info("*****->[{}]","应用关闭 ");
        }
        else {
        	log.info("*****[{}]->[{}]","其他",event.getSource().getClass().getSimpleName());
        }
		
	}

}
