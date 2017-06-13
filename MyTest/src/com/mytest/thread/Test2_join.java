package com.mytest.thread;

public class Test2_join {

	
	public static void main(String[] args) throws InterruptedException {
		test2();
	}
	
	public static void test1(){
		for (int i = 0; i < 10; i++) {
			Thread t1=new Thread(()->{System.out.println("this is t1");});
			Thread t2=new Thread(()->{System.out.println("this is t2");});
			Thread t3=new Thread(()->{System.out.println("this is t3");});
			t1.start();
			t2.start();
			t3.start();
		}
	}
	
	public static void test2() throws InterruptedException{//这样就会按照顺序t1,t2,t3执行
		for (int i = 0; i < 10; i++) {
			Thread t1=new Thread(()->{System.out.println("this is t1");});
			Thread t2=new Thread(()->{System.out.println("this is t2");});
			Thread t3=new Thread(()->{System.out.println("this is t3");});
			t1.start();
			t1.join();//等待t1执行完毕之后(t.join()方法阻塞调用此方法的线程(calling thread)，直到线程t完成)
			
			t2.start();
			t2.join();//等待t2执行完毕之后
			
			t3.start();
			t3.join();//等待t3执行完毕之后
		}
	}
}
