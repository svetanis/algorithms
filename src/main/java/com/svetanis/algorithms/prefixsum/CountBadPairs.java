package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 2364. Count Number of Bad Pairs

public final class CountBadPairs {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static long sum(int[] a) {
		Map<Integer, Integer> map = new HashMap<>();
		long count = 0;
		for (int i = 0; i < a.length; i++) {
			int diff = i - a[i];
			count += i - map.getOrDefault(diff, 0);
			map.merge(diff, 1, Integer::sum);
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 1, 3, 3 };
		System.out.println(sum(a1)); // 5

		int[] a2 = { 1, 2, 3, 4, 5 };
		System.out.println(sum(a2)); // 0
	}
}
