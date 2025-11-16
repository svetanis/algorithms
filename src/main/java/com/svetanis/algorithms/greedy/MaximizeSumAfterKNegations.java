package com.svetanis.algorithms.greedy;

import java.util.HashMap;
import java.util.Map;

// 1005. Maximize Sum Of Array After K Negations

public final class MaximizeSumAfterKNegations {
	// Time Complexity: O(n + ь)

	public static int largestSumAfterKNegations(int[] nums, int k) {
		Map<Integer, Integer> map = frequency(nums);
		for (int num = -100; num < 0 && k > 0; num++) {
			if (map.getOrDefault(num, 0) > 0) {
				int flips = Math.min(map.get(num), k);
				map.merge(num, -flips, Integer::sum);
				map.merge(-num, flips, Integer::sum);
				k -= flips;
			}
		}
		if (k % 2 != 0 && map.getOrDefault(0, 0) == 0) {
			for (int num = 1; num <= 100; num++) {
				if (map.getOrDefault(num, 0) > 0) {
					map.merge(num, -1, Integer::sum);
					map.merge(-num, 1, Integer::sum);
					break;
				}
			}
		}
		int totalSum = 0;
		for (int num : map.keySet()) {
			totalSum += num * map.get(num);
		}
		return totalSum;
	}

	private static Map<Integer, Integer> frequency(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			map.merge(num, 1, Integer::sum);
		}
		return map;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 2, 3 };
		System.out.println(largestSumAfterKNegations(a1, 1)); // 5

		int[] a2 = { 3, -1, 0, 2 };
		System.out.println(largestSumAfterKNegations(a2, 3)); // 6

		int[] a3 = { 2, -3, -1, 5, -4 };
		System.out.println(largestSumAfterKNegations(a3, 2)); // 13
	}
}
