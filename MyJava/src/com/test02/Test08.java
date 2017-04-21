package com.test02;


import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.function.Predicate;


public class Test08 {

	public static void main(String[] args) {
		LocalDate date=LocalDate.of(2016, Month.JANUARY, 20);
		System.out.println(date);

		DateTimeFormatter dtf=DateTimeFormatter.ISO_LOCAL_DATE;
		String result=dtf.format(LocalDate.now());
		System.out.println(result);
		System.out.println(Instant.now().getNano());
		
		new Thread(()->{
			System.out.println("this is a thread");
		}).start();
		
		
		List<Integer> intList=new ArrayList<>();
		intList.add(1);
		intList.add(2);

		OptionalInt max=intList.stream().mapToInt(i->i).max();
		int sum=intList.stream().mapToInt(i->i).reduce(0, (x,y)->x+y);
		System.out.println(max.getAsInt());
		System.out.println(sum);
		
		Map<String, Integer> map=new HashMap<>();
		map.put("a", 10);
		map.put("b", 10);
		
		int x=map.entrySet().stream().mapToInt(m->m.getValue()).sum();
		System.out.println(x);
		
		List<Student> student=new ArrayList<>();
		student.add(new Student("c", "20", Sex.女));
		student.add(new Student("d", "20", Sex.女));
		student.add(new Student("a", "30", Sex.男));
		System.out.println(student);
		student.sort(Comparator.comparing(Student::getName));
		System.out.println(student);
		int girlage=student.stream()
				          .mapToInt(stu-> {
				        	  if(Sex.女.equals(stu.getSex())){
				        		  return Integer.parseInt(stu.getNumber());
				        	  }else{
				        		  return 0;
				        	  }})
				          .sum();
		System.out.println(girlage);
		
		List<String> strs=Arrays.asList("fjk","dfjh","a","123");
		strs.sort(Comparator.comparing(s->s));
		System.out.println(strs);
		
		Predicate<String> predicate = (s) -> "a".equals(s);
		
		System.out.println(predicate.test("a1"));
		
		System.out.println(predicate.negate().test("a1"));
	}
	


}
