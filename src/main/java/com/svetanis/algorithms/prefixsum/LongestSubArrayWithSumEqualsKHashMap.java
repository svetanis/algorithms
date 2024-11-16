package com.svetanis.algorithms.prefixsum;

import static com.google.common.collect.Maps.newHashMap;
import static java.lang.Integer.MIN_VALUE;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.Map;

import com.svetanis.algorithms.sorting.interval.Interval;

// find the max length of a subarray 
// from the given array, such that the
// sum of its elements is exactly equal
// to the given integer k. 

// a subarray is a contiguous part of an array.
// if such subarray doesn't exist, return 0. 

public final class LongestSubArrayWithSumEqualsKHashMap {
	// Time complexity: O(n)
	// Space complexity: O(n)

	public static Interval maxSubArray(int target, List<Integer> list) {
		int sum = 0;
		int max = MIN_VALUE;
		int left = -1;
		int right = -1;

		Map<Integer, Integer> map = newHashMap();
		map.put(0, -1);
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
			int diff = sum - target;
			if (map.containsKey(diff)) {
				int start = map.get(diff);
				if (max < i - start) {
					max = i - start;
					left = start + 1;
					right = i;
				}
			}
			map.putIfAbsent(sum, i);
		}
		return new Interval(left, right);
	}

	public static void main(String[] args) {
		System.out.println(maxSubArray(15, asList(10, 5, 2, 7, 1, 9))); // [1, 4]
		System.out.println(maxSubArray(3, asList(1, -1, 5, -2, 3))); // [0, 3]
	}
}