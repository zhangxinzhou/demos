package com.mytest.thread;

/**
 * http://blog.csdn.net/luoweifu/article/details/46613015 synchronized关键字
 * 
 * @author Administrator
 *
 */
public class Test1_synchronized2 implements Runnable {

	private static int count;

	public Test1_synchronized2() {
		count = 0;
	}

	public synchronized void countAdd(){
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (count++));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
	public void printCount() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + " count:" + count);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		String threadName = Thread.currentThread().getName();
		if (threadName.equals("A")) {
			countAdd();
		} else if (threadName.equals("B")) {
			printCount();
		}
	}

	public static void main(String[] args) {
		test();
	}

	/**countAdd是一个synchronized的，printCount是非synchronized的。
	 * 从的结果中可以看出一个线程访问一个对象的synchronized代码块时，别的线程可以访问该对象的非synchronized代码块而不受阻塞。**/
	public static void test() {
		Test1_synchronized2 t = new Test1_synchronized2();
		Thread t1 = new Thread(t, "A");
		Thread t2 = new Thread(t, "B");
		t1.start();
		t2.start();
	}

}
