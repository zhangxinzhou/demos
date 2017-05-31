package com.mytest.thread;

/**
 * http://blog.csdn.net/luoweifu/article/details/46613015 synchronized关键字
 * 
 * @author Administrator
 *
 */
public class Test1_synchronized4 implements Runnable {
	private static int count;

	public Test1_synchronized4() {
		count = 0;
	}

	public synchronized static void method() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (count++));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void run() {
		method();
	}
	
	/**t1和t2是Test1_synchronized4的两个对象，但在thread1和thread2并发执行时却保持了线程同步。
	 * 这是因为run中调用了静态方法method，而静态方法是属于类的，所以t1和t2相当于用了同一把锁。**/
	public static void main(String[] args) {
		Test1_synchronized4 t1=new Test1_synchronized4();
		Test1_synchronized4 t2=new Test1_synchronized4();
		Thread thread1 = new Thread(t1, "SyncThread1");
		Thread thread2 = new Thread(t2, "SyncThread2");
		thread1.start();
		thread2.start();
	}
}
