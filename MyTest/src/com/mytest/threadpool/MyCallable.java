package com.mytest.threadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Map<String, Object>> {

	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public Map<String, Object> call() throws Exception {
		Random random = new Random();
		int sleep = random.nextInt(1000);
		Thread.sleep(sleep);
		Map<String, Object> result = new LinkedHashMap<>();
		result.put("index", msg);
		result.put("sleep", sleep);
		result.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss:SSS")));
		return result;
	}

}
