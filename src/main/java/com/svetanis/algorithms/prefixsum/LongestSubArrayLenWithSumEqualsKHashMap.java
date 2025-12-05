package com.svetanis.algorithms.prefixsum;

import java.util.HashMap;
import java.util.Map;

// 325. Max Size Subarray Sum Equals k

// find the max length of a subarray 
// from the given array, such that the
// sum of its elements is exactly equal
// to the given integer k. 

// a subarray is a contiguous part of an array.
// if such subarray doesn't exist, return 0. 

public final class LongestSubArrayLenWithSumEqualsKHashMap {
	// Time complexity: O(n)
	// Space complexity: O(n)

	public static int maxSubArrLen(int target, int[] a) {
		int max = 0;
		long sum = 0;
		Map<Long, Integer> map = new HashMap<>();
		map.put(0L, -1);
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			// first entry (0,-1) takes care about
			// edge case when subarray starts at 0
			// otherwise add this check
			// if (sum == target) {
			// max = i + 1;
			// }
			long diff = sum - target;
			if (map.containsKey(diff)) {
				int start = map.get(diff);
				max = Math.max(max, i - start);
			}
			map.putIfAbsent(sum, i);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxSubArrLen(15, new int[] {10, 5, 2, 7, 1, 9})); // 4
		System.out.println(maxSubArrLen(3, new int[] {1, -1, 5, -2, 3})); // 4
	}
}