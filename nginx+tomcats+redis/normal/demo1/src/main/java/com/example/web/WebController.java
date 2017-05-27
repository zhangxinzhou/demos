package com.example.web;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

	
	@RequestMapping({"/","index","home"})
	@ResponseBody
	public String index(HttpServletRequest request){
		HttpSession session=request.getSession();
		String serviceInfo="server info : "+request.getLocalAddr()+":"+request.getServerPort()+"#"+request.getServerName()+"<br/>";
		String clientInfo="client info : "+request.getRemoteAddr()+":"+request.getRemotePort()+"#"+request.getRemoteUser()+"<br/>";
		String webBrowser="web browser : "+request.getHeader("User-Agent")+"<br/>";
		String sessionId="session id : "+session.getId()+"<br/>";
		String sessionCreateTime="session CreateTime : "+getTime(session.getCreationTime()) +"<br/>";
		String sessionLastAccessedTime="session LastAccessedTime : "+getTime(session.getLastAccessedTime())+"<br/>";
		String sessionMaxInactiveInterval="session MaxInactiveInterval : "+session.getMaxInactiveInterval()+"<br/>";	
		StringBuffer sb=new StringBuffer("<h1>hello,this is index.</h1><br/>");
		sb.append(serviceInfo).append(clientInfo).append(webBrowser)
		  .append(sessionId).append(sessionCreateTime).append(sessionLastAccessedTime).append(sessionMaxInactiveInterval);
		return sb.toString();
	}
	
	private String getTime(Long epochMilli){
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault()).toString();
	}
}
