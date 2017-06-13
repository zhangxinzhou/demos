package com.mytest.thread;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的学习
 * @author Administrator
 *
 */
public class Test3_ThreadPool {

	
	public static void main(String[] args) {
		//cachedThreadPoolTest();
		//singleThreadExecutorTest();
		//fixedThreadPoolTest();
		scheduledThreadPoolTest();
	}
	
	/**
	 * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
	 * 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
	 */
	public static void cachedThreadPoolTest(){
		ExecutorService cacheThreadPool=Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int temp=i+1;
			cacheThreadPool.execute(()->{printSomething(temp);});
		}	
	}
	
	/**
	 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
	 * 结果依次输出，相当于顺序执行各个任务。
	 */
	public static void singleThreadExecutorTest(){
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(); 
		for (int i = 0; i < 10; i++) {
				final int temp=i+1;
				singleThreadExecutor.execute(()->{printSomething(temp);});
		}	
	}
	
	/**
	 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
	 * 定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
	 */
	public static void fixedThreadPoolTest(){
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3); 
		for (int i = 0; i < 10; i++) {
			final int temp=i+1;
			fixedThreadPool.execute(()->{printSomething(temp);});
		}	
	}
	
	/**
	 * 创建一个定长线程池，支持定时及周期性任务执行
	 */
	public static void scheduledThreadPoolTest(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		for (int i = 0; i < 10; i++) {
			final int temp=i+1;
			//scheduledThreadPool.execute(()->{printSomething(temp);});
			//scheduledThreadPool.schedule(()->{printSomething(temp);},3, TimeUnit.SECONDS);//延迟3秒执行
			scheduledThreadPool.scheduleAtFixedRate(()->{printSomething(temp);},1L,3, TimeUnit.SECONDS);//延迟1秒后每3秒执行一次
		}	
		//scheduledThreadPool.scheduleAtFixedRate(()->{printSomething(0);},1L,3, TimeUnit.SECONDS);//延迟1秒后每3秒执行一次(定时任务啊..)
	}

	
	private static void printSomething(int temp){
		System.out.println("TIME:"+LocalDateTime.now()+
				           ",NUM:"+temp+				           
				           ",THREAD NAME:"+Thread.currentThread().getName());
	}
}
