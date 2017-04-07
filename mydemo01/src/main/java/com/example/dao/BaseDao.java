package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 持久基类,注入JdbcTemplate
 * @author ZXZ
 * @version 时间:2017-4-7
 */
@Repository
public class BaseDao {

	/*注入JdbcTemplate*/
	@Autowired
	protected JdbcTemplate jdbc;
}
