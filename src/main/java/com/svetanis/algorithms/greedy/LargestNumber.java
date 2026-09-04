package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

// 179. Largest Number

public final class LargestNumber {
	// Time Complexity: O(n log n)

	public static String largestNumber(int[] a) {
		List<String> list = new ArrayList<>();
		for (int num : a) {
			list.add(String.valueOf(num));
		}
		list.sort((s1, s2) -> (s2 + s1).compareTo(s1 + s2));
		if("0".equals(list.get(0))) {
			return "0";
		}
		return String.join("", list);
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 4, 3, 2 };
		System.out.println(largestNumber(a1)); // 4321

		int[] a2 = { 6, 2, 6, 5, 1, 2 };
		System.out.println(largestNumber(a2)); // 665221
	}
}
