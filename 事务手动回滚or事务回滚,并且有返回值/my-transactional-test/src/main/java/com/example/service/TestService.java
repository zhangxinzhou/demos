package com.example.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.example.dao.Table1Dao;
import com.example.dao.Table2Dao;
import com.example.model.MyTab1;
import com.example.model.MyTab2;

@Service
public class TestService {

	@Autowired
	Table1Dao t1Dao;
	@Autowired
	Table2Dao t2Dao;

	@Transactional // 方法加上事务
	public String test(Long id) {
		MyTab1 t1 = t1Dao.findOne(id);
		int newNum = t1.getNum() + 1;
		t1.setNum(newNum);
		MyTab2 t2 = new MyTab2(t1.getId(), t1.getNum(), t1.getName(), t1.getMessage());
		try {
			t1Dao.save(t1);
			t2Dao.save(t2);
		} catch (Exception e) {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>发生异常!!!!!!!!!!!!!!");
			//手动回滚异常(如果发生异常就回滚事务,但是仍然保证有返回值)
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return "操作失败!表2的name不能为空";
		}
		return "操作成功!,表1num+1,同时更新表2的数据(如果没有就新增一个)";
	}

	public Map<String, Object> getT1andT2() {
		Map<String, Object> map = new HashMap<>();
		map.put("t1", t1Dao.findAll());
		map.put("t2", t2Dao.findAll());
		return map;
	}
}
