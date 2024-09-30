package com.svetanis.algorithms.prefixsum;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;

import java.util.List;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multimap;
import com.svetanis.algorithms.sorting.mergesort.interval.Interval;

// locate subarrays that sum to zero

public final class AllZeroSumSubarrays {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static ImmutableList<Interval> zeroSumSubarrays(List<Integer> list) {
		int sum = 0;
		List<Interval> intervals = newArrayList();
		Multimap<Integer, Integer> multi = ArrayListMultimap.create();
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i);
			if (sum == 0) {
				intervals.add(new Interval(0, i));
			}
			if (multi.containsKey(sum)) {
				for (int start : multi.get(sum)) {
					intervals.add(new Interval(start + 1, i));
				}
			}
			multi.put(sum, i);
		}
		return newList(intervals);
	}

	public static void main(String[] args) {
		List<Integer> list = asList(6, -4, 4, 2, -2);
		System.out.println(zeroSumSubarrays(list)); // [1, 2], [1, 4], [3, 4]

		List<Integer> list1 = asList(1, 3, -3, 8, 5, 7);
		System.out.println(zeroSumSubarrays(list1)); // [1, 2]

		List<Integer> list2 = asList(1, 1, 1);
		System.out.println(zeroSumSubarrays(list2)); // []

		List<Integer> list3 = asList(6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7);
		System.out.println(zeroSumSubarrays(list3)); // [2, 4], [2, 6], [5, 6], [6, 9], [0, 10]

		List<Integer> list4 = asList(1, 2, -3, 3, -1, -1);
		System.out.println(zeroSumSubarrays(list4)); // [0, 2], [2, 3], [1, 5]
	}
}
