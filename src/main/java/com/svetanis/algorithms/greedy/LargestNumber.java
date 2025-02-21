package com.svetanis.algorithms.greedy;

import java.util.ArrayList;
import java.util.List;

// 179. Largest Number

public final class LargestNumber {
	// Time Complexity: O(n log n)

	public static String pairSum(int[] a) {
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
		System.out.println(pairSum(a1)); // 4

		int[] a2 = { 6, 2, 6, 5, 1, 2 };
		System.out.println(pairSum(a2)); // 9
	}
}
