package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 525. Contiguous Array

public final class ContiguousArray {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int maxLen(int[] a) {
		int n = a.length;
		int max = 0;
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		for (int i = 0; i < n; i++) {
			sum += a[i] == 1 ? 1 : -1;
			if (map.containsKey(sum)) {
				max = Math.max(max, i - map.get(sum));
			} else {
				map.put(sum, i);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 1 };
		System.out.println(maxLen(a1)); // 2

		int[] a2 = { 0, 1, 0 };
		System.out.println(maxLen(a2)); // 2

		int[] a3 = { 1, 1, 1, 1, 1, 1, 1, 1 };
		System.out.println(maxLen(a3)); // 2
	}
}
