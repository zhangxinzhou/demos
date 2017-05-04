package com.rpclearn.rpc;

import com.rpclearn.core.RpcFramework;
import com.rpclearn.service.HelloService;

/**
 * 引用服务
 * @author Administrator
 *
 */
public class RpcProvider {

	public static void main(String[] args) throws Exception {
		HelloService service=RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			String hello=service.hello("world "+i);
			System.out.println(hello);
			Thread.sleep(1000);
		}
	}
}
