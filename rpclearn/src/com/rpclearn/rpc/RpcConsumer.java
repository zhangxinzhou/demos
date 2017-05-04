package com.rpclearn.rpc;

import com.rpclearn.core.RpcFramework;
import com.rpclearn.service.HelloService;
import com.rpclearn.service.impl.HelloServiceImpl;

/**
 * 暴露服务
 * @author Administrator
 *
 */
public class RpcConsumer {

	public static void main(String[] args) throws Exception {
		HelloService service=new HelloServiceImpl();
		RpcFramework.export(service, 1234);
	}
}
