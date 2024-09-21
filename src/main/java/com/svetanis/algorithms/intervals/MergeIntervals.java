package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

// given a collection of intervals
// merge all overlapping intervals

public final class MergeIntervals {
	// Time Complexity: O(n * log n)
	// Space Complexity: O(1)

	public static List<List<Integer>> merge(List<List<Integer>> intervals) {
		intervals.sort((i1, i2) -> Integer.compare(i1.get(0), i2.get(0)));
		List<List<Integer>> list = new ArrayList<>();
		list.add(intervals.get(0));
		for (int i = 1; i < intervals.size(); i++) {
			List<Integer> prev = list.get(list.size() - 1);
			List<Integer> curr = intervals.get(i);
			// overlapping: extend the last interval
			if (curr.get(0) <= prev.get(1)) {
				int end = max(prev.get(1), curr.get(1));
				prev.set(1, end);
				list.set(list.size() - 1, prev);
			} else {
				list.add(curr);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		List<List<Integer>> list = newArrayList();
		list.add(asList(1, 3));
		list.add(asList(8, 10));
		list.add(asList(15, 18));
		list.add(asList(2, 6));
		System.out.println(merge(list));

		List<List<Integer>> list2 = newArrayList();
		list2.add(asList(1, 4));
		list2.add(asList(4, 5));
		System.out.println(merge(list2));

		List<List<Integer>> list3 = newArrayList();
		list3.add(asList(6, 8));
		list3.add(asList(1, 9));
		list3.add(asList(2, 4));
		list3.add(asList(4, 7));
		System.out.println(merge(list3)); // [1, 9]

		List<List<Integer>> list4 = newArrayList();
		list4.add(asList(6, 8));
		list4.add(asList(1, 3));
		list4.add(asList(2, 4));
		list4.add(asList(4, 7));
		System.out.println(merge(list4)); // [1, 8]

		List<List<Integer>> list5 = newArrayList();
		list5.add(asList(1, 3));
		list5.add(asList(7, 9));
		list5.add(asList(4, 6));
		list5.add(asList(10, 13));
		System.out.println(merge(list5)); // [1, 3], [4, 6], [7, 9], [10, 13]

	}
}
