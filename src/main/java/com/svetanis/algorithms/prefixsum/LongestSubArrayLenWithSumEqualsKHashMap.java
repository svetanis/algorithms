package com.svetanis.algorithms.prefixsum;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.Map;

// find the max length of a subarray 
// from the given array, such that the
// sum of its elements is exactly equal
// to the given integer k. 

// a subarray is a contiguous part of an array.
// if such subarray doesn't exist, return 0. 

public final class LongestSubArrayLenWithSumEqualsKHashMap {
	// Time complexity: O(n)
	// Space complexity: O(n)

	public static int maxSubArrLen(int target, List<Integer> list) {
		int sum = 0;
		int max = MIN_VALUE;
		Map<Integer, Integer> map = newHashMap();
		map.put(0, -1);
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
			// first entry (0,-1) takes care about
			// edge case when subarray starts at 0
			// otherwise add this check
			// if (sum == target) {
			// max = i + 1;
			// }
			int diff = sum - target;
			if (map.containsKey(diff)) {
				int start = map.get(diff);
				max = max(max, i - start);
			}
			map.putIfAbsent(sum, i);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxSubArrLen(15, asList(10, 5, 2, 7, 1, 9))); // 4
		System.out.println(maxSubArrLen(3, asList(1, -1, 5, -2, 3))); // 4
	}
}