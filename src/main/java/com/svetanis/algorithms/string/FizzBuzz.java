package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.List;

// 412. Fizz Buzz
//
// Input: n. Returns a list of n strings; entry i (counting from 1) is "FizzBuzz" if i divides by
// 3 and by 5, "Fizz" if only by 3, "Buzz" if only by 5, otherwise i written out. On LeetCode:
// 1 <= n <= 10^4.
//
// The one idea: one entry is the text for one number i, and it depends only on i's remainders
// when divided by 3 and by 5. The order of the tests is the only trap: the "both" test must come
// before the single ones, or 15 would stop at "Fizz".
//
// Time: O(n) -- n numbers, a constant number of remainder tests each.
// Space: O(n) -- the returned list.
//
// Siblings: fizzBuzz tests "both" first and then each one alone; fizzBuzz2 builds the text by
// adding "Fizz" and "Buzz" to an empty string and writes the number only if the string is still
// empty, so there is no "both" test at all.

public final class FizzBuzz {

	public static List<String> fizzBuzz(int n) {
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) { // WALK: 1 .. n
			if (i % 5 == 0 && i % 3 == 0) { // BOTH first, or 15 would stop at the next test
				list.add("FizzBuzz");
			} else if (i % 5 == 0) {
				list.add("Buzz");
			} else if (i % 3 == 0) {
				list.add("Fizz");
			} else {
				list.add(i + ""); // NUMBER: neither divides
			}
		}
		return list;
	}

	public static List<String> fizzBuzz2(int n) {
		List<String> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) { // WALK: 1 .. n
			String s = "";
			if (i % 3 == 0) {
				s += "Fizz"; // ADD: no "both" test needed, the two words just join
			}
			if (i % 5 == 0) {
				s += "Buzz";
			}
			if (s.isEmpty()) {
				s += i + ""; // NUMBER: neither word was added
			}
			list.add(s);
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(fizzBuzz(3)); // [1, 2, Fizz]
		System.out.println(fizzBuzz(5)); // [1, 2, Fizz, 4, Buzz]
		System.out.println(fizzBuzz(15)); // [1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz]
	}
}
