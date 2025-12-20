package com.svetanis.algorithms.dp.fibvariants;

import java.util.HashMap;
import java.util.Map;

// 377. Combination Sum IV

// given an array of distinct integers and
// a target integer, return the number of 
// possible combinations that add up to target

public final class CombinationSumTopDown {

	public static int count(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		return combinations(nums, target, map);
	}

	private static int combinations(int[] nums, int remain, Map<Integer, Integer> map) {
		if (remain == 0) {
			return 1;
		}
		if (map.containsKey(remain)) {
			return map.get(remain);
		}
		int count = 0;
		for (int num : nums) {
			if (remain - num >= 0) {
				count += combinations(nums, remain - num, map);
			}
		}
		map.put(remain, count);
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		int[] a2 = { 9 };
		int[] a3 = { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
		System.out.println(count(a1, 4)); // 7
		System.out.println(count(a2, 3)); // 0
		System.out.println(count(a3, 10)); // 9
	}
}
