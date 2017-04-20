package com.mytest.test;

import java.util.stream.Stream;

public class Test07 {

	
	public static void main(String[] args) {
		Integer[] sixNums = {1, 2, 3, 4, 5, 6};
		Integer[] evens =
		Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
		Stream.of(evens).forEach(System.out::println);
	}
}
