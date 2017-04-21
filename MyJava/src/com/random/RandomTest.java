package com.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;

public class RandomTest {

	private static final AtomicLong seedUniquifier  = new AtomicLong(8682522807148012L); 
	private static long seedUniquifier() { 
		for (;;) { 
		long current = seedUniquifier.get(); 
		long next = current * 181783497276652981L; 
		if (seedUniquifier.compareAndSet(current, next)) 
		return next; 
        }
	}

	//@Test
	public void test00(){//默认的种子
		for (int i = 0; i < 100; i++) {
			System.out.println("默认的种子:"+seedUniquifier());
		}
	}
	
	//@Test// l相同那么产生的数也相同
	public  void test01(){
		for (int i = 0; i < 100; i++) {
			long l=System.currentTimeMillis();
			Random r=new Random(l);;
			System.out.println("test01:"+r.nextInt());
		}		
	}
	
	//@Test// l相同那么产生的数也相同
	public  void test02(){
		long l=System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {			
			Random r=new Random(l);
			System.out.println("test02:"+r.nextInt());
		}		
	}
	
	//@Test
	public void test03(){  
		long l=0l;
		for (int i = 0; i < 100; i++) {			
			Random r=new Random(l);
			System.out.println("test03:"+r.nextInt());
		}	
	}
	
	//@Test  //test03每次都取new Random(0)的第一个随机数,所以每次的结果都是一样,而test04是按顺序取得,其实只要知道了l和取出的顺序就知道下个是什么数
	public void test04(){
		long l=0l;
		Random r=new Random(l);
		for (int i = 0; i < 100; i++) {			
			System.out.println("test04:"+r.nextInt());
		}	
	}

	//@Test
	public void test05(){
		long l=0l;
		Random r=new Random(l);
		List<Long> ll=new ArrayList<>();//在list里面放入100个数
		boolean b=false;
		int x=0;
		while(true){
			l++;
			long num=r.nextLong();
			if(l<=1){
				ll.add(num);
			}
			if(l>100&&num==ll.get(0)){
				System.out.println("第"+l+"个数:"+num+"第一次重复");
				ll.clear();
				b=true;
			}
			if(b&&x<100){	
				x++;
			    ll.add(num);
			}
			if(x>=99){
				break;
			}
		}
		System.out.println(ll);
	}
	
	//@Test
	public void test06(){
		long l=0l;
		Random r=new Random(l);
		List<Long> ll=new ArrayList<>();
		for (int i = 0; i < 100; i++) {			
			ll.add(r.nextLong());
		}	
		System.out.println(ll);
	}
	
	@Test
	public void test07(){
		int num=0;
		List<Integer> il=new ArrayList<>();
		boolean b=false;
		int i=0;
		while(true){
			num=myRandom(num);
			il.add(num);
			if(il.size()>1&&il.get(0)==num){
				b=true;
			}
			if(b){
				i++;
				if(i>10){
					break;
				}			
			}
		}
		System.out.println(il.size()+":"+il);
	}
	
	public int myRandom(int x){
		return (x*123+59)%65536;
	}
}
