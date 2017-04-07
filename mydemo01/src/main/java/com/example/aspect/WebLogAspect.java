package com.example.aspect;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * aspect 记录日志内容
 * @author ZXZ
 * @version 时间:2017-4-7
 *
 */
@Aspect
@Component
public class WebLogAspect {
	
	/*日志*/
	private Logger log=LoggerFactory.getLogger(getClass());
	/*记录开始时间*/
	ThreadLocal<Long> startTime=new ThreadLocal<>();
	/*日志分割线*/
	private static final String LINE="****************************************************************************************";

	/**
	 * 定义切面,切面为com.example.web包下所有类的所有方法(Controller层)
	 */
	@Pointcut("execution(* com.example.web.*.*(..))")
	public void WebLogPointCut(){}
	
	/**
	 * 切入前,日志输出log begin
	 * @param jp  JoinPoint
	 */
	@Before(value="WebLogPointCut()")
	public void doBefore(JoinPoint jp){
		log.info(Msg("log begin"));
		startTime.set(System.currentTimeMillis());
	}
	
	/**
	 * 返回结果后,日志输出结果              
	 * @param jp    JoinPoint
	 * @param obj   被切入方法放回的结果
	 */
	@AfterReturning(value="WebLogPointCut()",returning="obj")
	public void doAfter(JoinPoint jp,Object obj){
		String url="";
		String ip="";
		int remotePort=0;
		int serverPort=0;
		String methodType="";
		RequestAttributes ra= RequestContextHolder.getRequestAttributes();
		if(ra!=null){
			HttpServletRequest request=((ServletRequestAttributes)ra).getRequest();
			url=request.getRequestURL().toString();
			ip=request.getRemoteAddr();
			remotePort=request.getRemotePort();
			serverPort=request.getServerPort();
			methodType=request.getMethod();
		}
		log.info("TIME : [{}]",LocalDateTime.now());
		log.info("IP : [{}]",ip);
		log.info("REMOTE PORT : [{}]",remotePort);
		log.info("SERVER PORT : [{}]",serverPort);
		log.info("URL : [{}]",url);
		log.info("REQUEST METHOD : [{}]",methodType);
		
		log.info("FULL CLASS NAME : [{}]",jp.getSignature().getDeclaringType());
		log.info("FULL METHOD NAME : [{}]",jp.getSignature().toLongString());
		log.info("SHORT METHOD NAME : [{}]",jp.getSignature().toShortString());
		log.info("METHOD NAME : [{}]",jp.getSignature().getName());
		log.info("ARGS : [{}]",jp.getArgs());
		log.info("RETURN : [{}]",obj);
		
		log.info("Target : [{}]",jp.getTarget());
		log.info("SourceLocation : [{}]",jp.getSourceLocation());
		log.info("StaticPart : [{}]",jp.getStaticPart());
		log.info("SPEND TIME : [{}]",System.currentTimeMillis()-startTime.get()+"ms");
		log.info(Msg("log end"));
	}
	
	/**
	 * 日志分割线的处理方法
	 * @param msg  分割线中间的内容
	 * @return     返回处理后的分割先,如:**日志结束**
	 */
	private String Msg(String msg){
		StringBuffer sb=new StringBuffer(LINE);
		sb.insert(LINE.length()/2, msg);
		return sb.toString();
	}
}
