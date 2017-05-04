package com.proxylearn.proxy;

import java.lang.reflect.Method;

import com.proxylearn.util.SplitLineUtil;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/**
 * cglib包可以在这里下载http://maven.aliyun.com(建议下载cglib-full版本)
 * @author Administrator
 *
 */
public class CglibProxy implements MethodInterceptor{
	
	 Enhancer enhancer = new Enhancer();
	 public Object getProxy(Class<?> clazz){
		 enhancer.setSuperclass(clazz);
		 enhancer.setCallback(this);
		 return enhancer.create();
	 }

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		SplitLineUtil.printSplitLine(this.getClass().getSimpleName());
		Object result=proxy.invokeSuper(obj, args);
		SplitLineUtil.printSplitLine(this.getClass().getSimpleName());
		return result;
	}

}
