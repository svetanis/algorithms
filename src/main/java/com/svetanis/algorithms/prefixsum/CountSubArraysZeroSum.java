package com.svetanis.algorithms.prefixsum;

import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// given an array, return the number of
// subarrays whose sum is equal to zero

public final class CountSubArraysZeroSum {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int subarraySum(List<Integer> nums) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int sum = 0;
		int count = 0;
		for (int i = 0; i < nums.size(); i++) {
			sum += nums.get(i);
			if (map.containsKey(sum)) {
				count += map.get(sum);
			}
			int freq = map.getOrDefault(sum, 0);
			map.put(sum, freq + 1);
		}
		return count;
	}

	public static void main(String[] args) {
		List<Integer> list = asList(6, -4, 4, 2, -2);
		System.out.println(subarraySum(list)); // 3

		List<Integer> list1 = asList(1, 3, -3, 8, 5, 7);
		System.out.println(subarraySum(list1)); // 1

		List<Integer> list2 = asList(1, 1, 1);
		System.out.println(subarraySum(list2)); // 0

		List<Integer> list3 = asList(6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7);
		System.out.println(subarraySum(list3)); // 5

		List<Integer> list4 = asList(1, 2, -3, 3, -1, -1);
		System.out.println(subarraySum(list4)); // 3
	}
}
