package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// given an array of integers and
// an integer target, find a subarray
// that sums to target and find 
// the total number of subarrays
// that sums up to target

public final class CountSubArraysGivenSum {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int subarraySum(int target, List<Integer> nums) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int sum = 0;
		int count = 0;
		for (int i = 0; i < nums.size(); i++) {
			sum += nums.get(i);
			int diff = sum - target;
			if (map.containsKey(diff)) {
				count += map.get(diff);
			}
			int freq = map.getOrDefault(sum, 0);
			map.put(sum, freq + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		List<Integer> list = asList(1, 1, 1);
		System.out.println(subarraySum(2, list)); // 2

		List<Integer> list2 = asList(10, 5, -5, -20, 10);
		System.out.println(subarraySum(-10, list2)); // 3
	}
}
