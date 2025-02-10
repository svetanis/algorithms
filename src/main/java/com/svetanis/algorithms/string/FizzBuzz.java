package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.List;

// 412. Fizz Buzz

public final class FizzBuzz {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static List<String> fizzBuzz(int n) {
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			if (i % 5 == 0 && i % 3 == 0) {
				list.add("FizzBuzz");
			} else if (i % 5 == 0) {
				list.add("Buzz");
			} else if (i % 3 == 0) {
				list.add("Fizz");
			} else {
				list.add(i + "");
			}
		}
		return list;
	}

	public static List<String> fizzBuzz2(int n) {
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			String s = "";
			if (i % 3 == 0) {
				s += "Fizz";
			}
			if (i % 5 == 0) {
				s += "Buzz";
			}
			if (s.isEmpty()) {
				s += i + "";
			}
			list.add(s);
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(fizzBuzz(3));
		System.out.println(fizzBuzz(5));
		System.out.println(fizzBuzz(15));
	}
}
