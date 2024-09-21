package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

// given a set of non-overlapping intervals,
// insert a new interval into the intervals
// (merge if necessary)

public final class InsertInterval {
	// Time Complexity: O(n)

	public static List<List<Integer>> insert(List<List<Integer>> intervals, 
			List<Integer> interval) {
		intervals.sort((i1, i2) -> Integer.compare(i1.get(0), i2.get(0)));
		List<List<Integer>> list = new ArrayList<>();
		int i = 0;
		int n = intervals.size();
		// add all intervals that come before the new interval
		while (i < n && intervals.get(i).get(1) < interval.get(0)) {
			list.add(intervals.get(i));
			i++;
		}

		// merge all intervals that overlap with new interval
		while (i < n && intervals.get(i).get(0) <= interval.get(1)) {
			int start = min(intervals.get(i).get(0), interval.get(0));
			int end = max(intervals.get(i).get(1), interval.get(1));
			interval.set(0, start);
			interval.set(1, end);
			i++;
		}
		// insert new interval
		list.add(interval);

		// add all the remaining intervals
		while (i < n) {
			list.add(intervals.get(i++));
		}
		return list;
	}

	public static void main(String[] args) {
		List<List<Integer>> list = newArrayList();
		list.add(asList(1, 3));
		list.add(asList(6, 9));
		// [1, 5], [6, 9]
		System.out.println(insert(list, asList(2, 5)));

		List<List<Integer>> list2 = newArrayList();
		list2.add(asList(1, 2));
		list2.add(asList(3, 5));
		list2.add(asList(6, 7));
		list2.add(asList(8, 10));
		list2.add(asList(12, 16));
		// [1, 2], [3, 10], [12, 16]
		System.out.println(insert(list2, asList(4, 8)));

		List<List<Integer>> list3 = newArrayList();
		System.out.println(insert(list3, asList(5, 7))); // [5, 7]

		List<List<Integer>> list4 = newArrayList();
		list4.add(asList(1, 5));
		System.out.println(insert(list4, asList(2, 3))); // [1, 5]

		List<List<Integer>> list5 = newArrayList();
		list5.add(asList(1, 5));
		System.out.println(insert(list5, asList(2, 7))); // [1, 7]
	}
}
