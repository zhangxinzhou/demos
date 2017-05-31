package com.mytest.thread;

/**
 * http://blog.csdn.net/luoweifu/article/details/46613015 synchronized关键字
 * 
 * @author Administrator
 *
 */
public class Test1_synchronized3 implements Runnable {
	private Account account;

	public Test1_synchronized3(Account account) {
		this.account = account;
	}

	public void run() {
		/**synchronized 给account对象加了锁。这时，当一个线程访问account对象时，
		 * 其他试图访问account对象的线程将会阻塞，直到该线程访问account对象结束。也就是说谁拿到那个锁谁就可以运行它所控制的那段代码。 **/
		synchronized (account) {
			account.deposit(500);
			account.withdraw(500);
			System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
		}

	}
	
	public static void main(String[] args) {
		Account account = new Account("zhang san", 10000.0f);
		Test1_synchronized3 accountOperator = new Test1_synchronized3(account);

		final int THREAD_NUM = 5;
		Thread threads[] = new Thread[THREAD_NUM];
		for (int i = 0; i < THREAD_NUM; i ++) {
		   threads[i] = new Thread(accountOperator, "Thread" + i);
		   threads[i].start();
		}
	}
}
