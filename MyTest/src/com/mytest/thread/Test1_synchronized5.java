package com.mytest.thread;

/**
 * http://blog.csdn.net/luoweifu/article/details/46613015 synchronized关键字
 * 
 * @author Administrator
 *
 */
public class Test1_synchronized5 implements Runnable {
	private static int count;

	public Test1_synchronized5() {
		count = 0;
	}

	public static void method() {
		synchronized (Test1_synchronized5.class) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public synchronized void run() {
		method();
	}

	/** 效果和Test1_synchronized4是一样的,synchronized作用于一个类T时，是给这个类T加锁，T的所有对象用的是同一把锁。 **/
	public static void main(String[] args) {
		Test1_synchronized5 t1 = new Test1_synchronized5();
		Test1_synchronized5 t2 = new Test1_synchronized5();
		Thread thread1 = new Thread(t1, "SyncThread1");
		Thread thread2 = new Thread(t2, "SyncThread2");
		thread1.start();
		thread2.start();
	}

	/**
	 * 总结：
	 * A.无论synchronized关键字加在方法上还是对象上，如果它作用的对象是非静态的，则它取得的锁是对象；如果synchronized作用的对象是一个静态方法或一个类，则它取得的锁是对类，该类所有的对象同一把锁。
	 * B.每个对象只有一个锁（lock）与之相关联，谁拿到这个锁谁就可以运行它所控制的那段代码。
	 * C.实现同步是要很大的系统开销作为代价的，甚至可能造成死锁，所以尽量避免无谓的同步控制。
	 */
}
