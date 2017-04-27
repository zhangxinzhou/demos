package com.example.dao;

import org.springframework.stereotype.Repository;

import com.example.util.ServiceUtil;

@Repository
public class RegisterDao {

	public boolean storageData(){
		try {
			ServiceUtil.storageData();
			return true;
		} catch (Exception e) {
			System.out.println("存储用户的注册信息发生错误!");
		}
		return false;
	}
}
