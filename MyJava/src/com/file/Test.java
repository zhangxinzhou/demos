package com.file;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

	
	public static void main(String[] args) {		
		try {
			InetAddress ia=InetAddress.getLocalHost();
			System.out.println("本机名称是:"+ia.getHostName());
			System.out.println("本机IP是:"+ia.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
