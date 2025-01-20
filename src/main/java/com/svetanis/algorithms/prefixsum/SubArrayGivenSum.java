package com.svetanis.algorithms.prefixsum;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static java.util.Arrays.asList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.svetanis.algorithms.intervals.Interval;

// given an array of integers and
// an integer target, find a subarray
// that sums to target and return the
// start and end indices of the subarray

public final class SubArrayGivenSum {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static Optional<Interval> subarraySum(int target, List<Integer> nums) {
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 0);
		int sum = 0;
		for (int i = 0; i < nums.size(); i++) {
			sum += nums.get(i);
			int diff = sum - target;
			if (map.containsKey(diff)) {
				return of(new Interval(map.get(diff), i));
			}
			map.put(sum, i + 1);
		}
		return absent();
	}

	public static void main(String[] args) {
		List<Integer> list = asList(1, -20, -3, 30, 5, 4);
		System.out.println(subarraySum(7, list)); // 1, 3: -20, -3, 30

		List<Integer> list1 = asList(1, 3, -3, 8, 5, 7);
		System.out.println(subarraySum(5, list1)); // 2, 3

		List<Integer> list2 = asList(1, 1, 1);
		System.out.println(subarraySum(3, list2)); // 0, 2
	}
}
