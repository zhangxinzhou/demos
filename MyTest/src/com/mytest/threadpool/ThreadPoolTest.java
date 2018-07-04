package com.mytest.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolTest {

	private static final int thread_pool_size = 2;
	private static final int num = 100;

	public static void main(String[] args) throws Exception {
		test1();
		// test2();
	}

	static void test1() throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(thread_pool_size);
		List<Future<Map<String, Object>>> futures = new ArrayList<>();
		long start_time = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			MyCallable task = new MyCallable();
			task.setMsg("" + i);
			Future<Map<String, Object>> future = es.submit(task);
			futures.add(future);
		}
		System.out.println("添加over");
		for (Future<Map<String, Object>> future : futures) {
			Map<String, Object> result = future.get();
			System.out.println(result);
		}
		long end_time = System.currentTimeMillis();
		System.out.println("test1:" + (end_time - start_time) + "ms");
		es.shutdown();
	}

	static void test2() throws Exception {
		ExecutorService es = Executors.newFixedThreadPool(thread_pool_size);
		long start_time = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			MyCallable task = new MyCallable();
			task.setMsg("" + i);
			Future<Map<String, Object>> future = es.submit(task);
			Map<String, Object> result = future.get();
			System.out.println(result);
		}
		long end_time = System.currentTimeMillis();
		System.out.println("test2:" + (end_time - start_time) + "ms");
		es.shutdown();
	}
}
