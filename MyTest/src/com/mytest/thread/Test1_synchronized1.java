package com.mytest.thread;

/**
 * http://blog.csdn.net/luoweifu/article/details/46613015
 * synchronized关键字
 * @author Administrator
 *
 */
public class Test1_synchronized1 implements Runnable{

	@Override
	public synchronized void run() { 
		 int i=6;
		 while(i-->0){
			 System.out.println(Thread.currentThread().getName() + " synchronized loop " + i); 
			 try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
	}

	

	public static void main(String[] args) {
		//test();
		test1();
	}
	
	/**一个线程访问一个对象中的synchronized(this)同步代码块时，其他试图访问该对象的线程将被阻塞**/
	public static void test(){
		Test1_synchronized1 t=new Test1_synchronized1();
		Thread ta=new Thread(t, "A");
		Thread tb=new Thread(t, "B");
		ta.start();
		tb.start();
	}
	
	/**synchronized只锁定对象，每个对象只有一个锁（lock）与之相关联,而下面是两不同的对象**/
	public static void test1(){
		Thread t1=new Thread(new Test1_synchronized1(), "t1");
		Thread t2=new Thread(new Test1_synchronized1(), "t2");
		t1.start();
		t2.start();
	}
	
	
}
