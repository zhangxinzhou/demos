package com.proxylearn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.proxylearn.util.SplitLineUtil;


/**
 * 动态代理使用了反射技术(java.lang.reflect)
 * 局限性:使用JDK动态代理有一个很大的限制，就是它要求目标类必须实现了对应方法的接口，它只能为接口创建代理实例。
 * @author Administrator
 *
 */
public class InvocationHandlerProxy implements InvocationHandler{
	
	private Object obj;
	public InvocationHandlerProxy(Object obj) {
		super();
		this.obj = obj;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		SplitLineUtil.printSplitLine(this.getClass().getSimpleName());
		Object result=method.invoke(obj, args); 
		SplitLineUtil.printSplitLine(this.getClass().getSimpleName());
		return result;
	}

}
