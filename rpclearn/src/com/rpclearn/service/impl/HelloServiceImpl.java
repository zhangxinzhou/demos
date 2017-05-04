package com.rpclearn.service.impl;

import com.rpclearn.service.HelloService;

/**
 * 服务实现
 * @author Administrator
 *
 */
public class HelloServiceImpl implements HelloService{

	@Override
	public String hello(String name) {
		return "hello,"+name;
	}

}
