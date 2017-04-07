package com.example.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.dao.BaseDao;
import com.example.service.iface.TestServiceInterface;

/**
 * TestService类
 * @author ZXZ
 * @version 时间:2017-4-7
 */
@Service
public class TestService extends BaseDao implements TestServiceInterface{

	@Override
	public List<Map<String, Object>> testSql(String sql) {
		return jdbc.queryForList(sql);
	}

	@Override
	public int getCount(String sql) {
		List<Map<String, Object>> map=jdbc.queryForList(sql);
		return map!=null?map.size():0;
	}

}
