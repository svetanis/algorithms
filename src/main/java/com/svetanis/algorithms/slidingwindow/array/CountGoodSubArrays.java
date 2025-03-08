package com.svetanis.algorithms.slidingwindow.array;

import java.util.HashMap;
import java.util.Map;

// 2348. Number of Zero-Filled Subarrays

public final class CountGoodSubArrays {
	// Time Complexity: O(n)

	public static long countGood(int[] a, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		long total = 0;
		int left = 0;
		int pairs = 0;
		for (int right = 0; right < a.length; right++) {
			int curr = a[right];
			pairs += map.getOrDefault(curr, 0);
			map.merge(curr, 1, Integer::sum);
			while (pairs - map.get(a[left]) + 1 >= k) {
				pairs -= map.merge(a[left], -1, Integer::sum);
				left++;
			}
			if (pairs >= k) {
				total += left + 1;
			}
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 1, 1, 1 };
		System.out.println(countGood(a1, 10)); // 1
		int[] a2 = { 3, 1, 4, 3, 2, 2, 4 };
		System.out.println(countGood(a2, 2)); // 4
	}
}
