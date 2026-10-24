package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

// 179. Largest Number
//
// Input: a -- non-negative integers. Arrange them so that their digits, written one after another,
// form the largest number. Returns that number as a string, since it can be far too long for any
// integer type. On LeetCode: 1 <= a.length <= 100, 0 <= a[i] <= 10^9.
//
// The one idea: decide the order of any two numbers x and y by trying both ways round: x goes
// first when x followed by y reads larger than y followed by x. Both strings have the same length,
// so comparing them as text compares them as numbers. The rule is consistent -- if x goes before
// y and y before z, then x goes before z -- so sorting by it gives a single order, and in that
// order no swap of two neighbours makes the result larger, which means no other order does. If
// the first string is "0", every number is 0, and the answer is "0", not "000".
//
// Time: O(n log n) -- the sort makes O(n log n) comparisons, and each builds two strings of at
// most 20 characters, a constant here.
// Space: O(n) -- the n strings, at most 10 characters each.
//
// An empty array throws, and a negative number gives a string that is not a number; LeetCode rules
// both out.

public final class LargestNumber {

	public static String largestNumber(int[] a) {
		List<String> list = new ArrayList<>();
		for (int num : a) {
			list.add(String.valueOf(num)); // TEXT: the order is decided by digits
		}
		list.sort((s1, s2) -> (s2 + s1).compareTo(s1 + s2)); // SORT: s1 first when s1 + s2 reads larger
		if("0".equals(list.get(0))) { // ALL ZEROS: the largest one is 0
			return "0";
		}
		return String.join("", list); // RETURN: the digits in order
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 4, 3, 2 };
		System.out.println(largestNumber(a1)); // 4321

		int[] a2 = { 6, 2, 6, 5, 1, 2 };
		System.out.println(largestNumber(a2)); // 665221
	}
}
