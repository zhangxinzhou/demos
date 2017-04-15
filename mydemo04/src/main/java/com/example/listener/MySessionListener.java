package com.example.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class MySessionListener implements HttpSessionListener{

	private Logger log=LoggerFactory.getLogger(getClass());
	public static final String ONLINE_COUNT="count";
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
		Integer count=(Integer)application.getAttribute(ONLINE_COUNT);
		if(count==null){
			count=0;
		}
		application.setAttribute(ONLINE_COUNT, count+1);
		log.info("sessionCreated");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
        ServletContext application = session.getServletContext();
        Integer count=(Integer)application.getAttribute(ONLINE_COUNT);
        if(count==null){
			count=0;
		}
		application.setAttribute(ONLINE_COUNT, count-1);
		log.info("sessionDestroyed");
	}

}
