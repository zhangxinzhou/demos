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

	//先把所有的任务放到线程池队列中
	//预期是:任何一个任务返回结果（执行结束）,就打印出来，并移除futures
	//实际：会安装futures的顺序执行，如果futures[2]比futures[1]先完成，但是因为安装顺序来遍历，futures[2]仍然要等待futures[1]打印完毕之后才会轮到自己
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
		while(!futures.isEmpty()) {
			Map<String, Object> result = futures.get(0).get();
			futures.remove(0);
			System.out.println(result);
		}
		long end_time = System.currentTimeMillis();
		System.out.println("test1:" + (end_time - start_time) + "ms");
		es.shutdown();
	}

	//1.添加一个任务到线程池
	//2.该任务返回结果（执行完毕）之后才会添加下一个任务到线程池
	//意义不是很大
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
