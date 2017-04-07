package com.example.service.iface;

import java.util.List;
import java.util.Map;

/**
 * TestService接口
 * @author ZXZ
 * @version 时间:2017-4-7
 */
public interface TestServiceInterface {

	public List<Map<String, Object>> testSql(String sql);
	
	public int getCount(String sql);
}
