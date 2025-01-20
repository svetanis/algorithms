package com.svetanis.algorithms.prefixsum;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.svetanis.algorithms.intervals.Interval;

// locate subarrays that sum to given target

public final class AllGivenSumSubarrays {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static ImmutableList<Interval> givenSumSubarrays(int target, List<Integer> list) {
		int sum = 0;
		List<Interval> intervals = newArrayList();
		Multimap<Integer, Integer> multi = ArrayListMultimap.create();
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
			if (sum == target) {
				intervals.add(new Interval(0, i));
			}
			int diff = sum - target;
			if (multi.containsKey(diff)) {
				for (int start : multi.get(diff)) {
					intervals.add(new Interval(start + 1, i));
				}
			}
			multi.put(sum, i);
		}
		return newList(intervals);
	}

	public static void main(String[] args) {
		List<Integer> list = asList(1, -20, -3, 30, 5, 4);
		System.out.println(givenSumSubarrays(7, list)); // [1, 3]

		List<Integer> list1 = asList(1, 3, -3, 8, 5, 7);
		System.out.println(givenSumSubarrays(5, list1)); // [2, 3], [4, 4]

		List<Integer> list2 = asList(1, 1, 1);
		System.out.println(givenSumSubarrays(3, list2)); // [0, 2]

		List<Integer> list3 = asList(6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7);
		System.out.println(givenSumSubarrays(0, list3)); // [2, 4], [2, 6], [5, 6], [6, 9], [0, 10]

		List<Integer> list4 = asList(1, 2, -3, 3, -1, -1);
		System.out.println(givenSumSubarrays(0, list4)); // [0, 2], [2, 3], [1, 5]
	}
}
