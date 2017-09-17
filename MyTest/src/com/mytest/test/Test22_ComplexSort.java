package com.mytest.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test22_ComplexSort {

	
	public static void main(String[] args) {
	
		List<SeqObj> list=new ArrayList<>();
		Random random=new Random();
		int bound=3;
		for (int i = 0; i < 10; i++) {
			list.add(new SeqObj("name"+i, random.nextInt(bound), random.nextInt(bound), random.nextInt(bound), random.nextInt(bound)));
		}
		list.forEach(System.out::println);
		System.out.println("--------------------排序后--------------------");
		list.sort((a,b)->{//模拟数据库order by column1,column2,column3之类的效果
			//这一段代码有些臃肿,待优化
			int result=0;
			
			int seq1=a.seq1-b.seq1;
			int seq2=a.seq2-b.seq2;
			int seq3=a.seq3-b.seq3;
			int seq4=a.seq4-b.seq4;			
			if(seq1==0){
				if(seq2==0){
					if(seq3==0){
						result = seq4;
					}else{
						result = seq3;
					}
				}else{
					result = seq2;
				}
			}else{
				result = seq1;
			}
			return +result;//正负号可以控制升序还是降序
		});
		list.forEach(System.out::println);
	}
	
	
	
	
	public static class SeqObj{
		private String name;
		private int seq1;
		private int seq2;
		private int seq3;
		private int seq4;
		
		
		public SeqObj() {
			super();
		}
		
		public SeqObj(String name, int seq1, int seq2, int seq3, int seq4) {
			super();
			this.name = name;
			this.seq1 = seq1;
			this.seq2 = seq2;
			this.seq3 = seq3;
			this.seq4 = seq4;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getSeq1() {
			return seq1;
		}
		public void setSeq1(int seq1) {
			this.seq1 = seq1;
		}
		public int getSeq2() {
			return seq2;
		}
		public void setSeq2(int seq2) {
			this.seq2 = seq2;
		}
		public int getSeq3() {
			return seq3;
		}
		public void setSeq3(int seq3) {
			this.seq3 = seq3;
		}
		public int getSeq4() {
			return seq4;
		}
		public void setSeq4(int seq4) {
			this.seq4 = seq4;
		}
		@Override
		public String toString() {
			return "SeqObj [name=" + name + ", seq1=" + seq1 + ", seq2=" + seq2 + ", seq3=" + seq3 + ", seq4=" + seq4
					+ "]";
		}		
		
	}
}
