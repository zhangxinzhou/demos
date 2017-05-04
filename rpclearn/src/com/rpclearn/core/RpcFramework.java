package com.rpclearn.core;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * dubbo项目地址:https://github.com/wosyingjun/beauty_ssm_dubbo
 * 博客地址:http://javatar.iteye.com/blog/1123915
 * @author Administrator
 *
 */
public class RpcFramework {

	
	/**
	 *                    暴露服务
	 * @param service     服务实现
	 * @param port        服务端口
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public static void export(final Object service,int port) throws Exception{
		if(service == null){
			throw new IllegalArgumentException("service instance == null");
		}
		if(port <=0 || port > 65535 ){
			throw new IllegalArgumentException("Invalid port " + port);
		}
		System.out.println("Export service " + service.getClass().getName() + "on port " + port);
		ServerSocket server=new ServerSocket(port);
		for(;;){
			final Socket socket=server.accept();
			new Thread(new Runnable() {				
				@Override
				public void run() {
					try {
						ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
						String methodName=input.readUTF();
						Class<?>[] parameterTypes=(Class<?>[]) input.readObject();
						Object[] arguments=(Object[]) input.readObject();
						ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
						Method method=service.getClass().getMethod(methodName, parameterTypes);
						Object result=method.invoke(service, arguments);
						output.writeObject(result);
						//应该在finally里关闭连接的,这里方便自己理解,未关闭
					} catch (Exception e) {
						e.printStackTrace();
					}					
				}
			}).start();
		}		
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T refer(final Class<T> interfaceClass,final String host,final int port){
		if(interfaceClass==null){
			throw new IllegalArgumentException("Interface class == null");
		}
		if(!interfaceClass.isInterface()){
			throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");
		}
		if(host == null || host.length() == 0){
			throw new IllegalArgumentException("Host == null");
		}
		if (port <= 0 || port > 65535){
	        throw new IllegalArgumentException("Invalid port " + port); 
		}
		System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);
		return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
			
			@SuppressWarnings("resource")
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Socket socket=new Socket(host, port);
				ObjectOutputStream output=new ObjectOutputStream(socket.getOutputStream());
				output.writeUTF(method.getName());
				output.writeObject(method.getParameterTypes());
				output.writeObject(args);
				ObjectInputStream input=new ObjectInputStream(socket.getInputStream());
				Object result=input.readObject();
				if(result instanceof Throwable){
					throw (Throwable)result;
				}
				return result;
				//应该在finally里关闭连接的,这里方便自己理解,未关闭
			}
		});
	}
}
