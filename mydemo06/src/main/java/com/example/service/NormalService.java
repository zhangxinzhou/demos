package com.example.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.RegisterDao;
import com.example.util.ServiceUtil;


/**
 * 业务流程:
 * 1,用户注册服务(逻辑判断部分)
 * 2,注册信息持久化到数据库
 * 3,发送注册成功短信
 * 4,发送注册成功邮件
 * @author ZXZ
 *
 */
@Service
public class NormalService {

	@Autowired
	RegisterDao registerDao;
	
	//串行运行
	public boolean doNormalService(){
		//存储是核心业务,并且存储数据也有失败的可能,所以这一步不放在队列中
		boolean result=registerDao.storageData();
		if(result){
			System.out.println("使用普通串行方式:"+LocalDateTime.now());
			ServiceUtil.sendSMS();
			System.out.println("使用普通串行方式:"+LocalDateTime.now());
			ServiceUtil.sendEmail();
		}	
		return result;
	}
}
