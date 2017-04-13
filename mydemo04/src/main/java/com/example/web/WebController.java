package com.example.web;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.listener.MySessionListener;

@Controller
public class WebController {
	
	
	final String COOKIE_NAME="ctime";
	final String SESSION_TIME="stime";
	private Logger log=LoggerFactory.getLogger(getClass());

	@RequestMapping("/getsession")
	public void getSession(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		Object obj= session.getAttribute(SESSION_TIME);
		log.info("[{}]=[{}]",SESSION_TIME,obj);
	}
	
	@RequestMapping("/setsession")
	public void setSession(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setAttribute(SESSION_TIME, LocalDateTime.now());
	}
	
	@RequestMapping("/allsession")
	public void allSession(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		for (Enumeration<String> es= session.getAttributeNames();es.hasMoreElements();) {
			String sname=es.nextElement();
			log.info("[{}]=[{}]",sname,session.getAttribute(sname));
		}
	}
	
	
	@RequestMapping("/getcookie")
	public void getCookie(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies=request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if(Objects.equals(COOKIE_NAME, cookies[i].getName())){
				log.info("获取cookie : [{}]",cookies[i].getValue());
				break;
			}
		}
	}
	
	@RequestMapping("/addcookie")
	public void addCookie(HttpServletRequest request,HttpServletResponse response){
		Cookie cookie=new Cookie(COOKIE_NAME, LocalDateTime.now().toString());
		response.addCookie(cookie);
	}
	
	@RequestMapping("/allcookie")
	public void allCookie(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies=request.getCookies();
		Arrays.asList(cookies).forEach(c->{
			log.info("cookie : [{}]=[{}]",c.getName(),c.getValue());
		});
	}
	
	@RequestMapping("/count")
	@ResponseBody
	public Integer doApplication(HttpServletRequest request,HttpServletResponse response){
		ServletContext application = request.getServletContext(); 
		return (Integer)application.getAttribute(MySessionListener.ONLINE_COUNT);
	}
}
