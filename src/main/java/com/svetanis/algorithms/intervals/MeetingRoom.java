package com.svetanis.algorithms.intervals;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

import java.util.List;

// 252. Meeting Rooms

// Given an array of meeting time intervals 
// consisting of start and end times
// find if a person could attend all meetings.

public final class MeetingRoom {
	// Time Complexity: O(n * log n)
	// Space Complexity: O(1)

	public static boolean merge(List<List<Integer>> intervals) {
		intervals.sort((i1, i2) -> Integer.compare(i1.get(0), i2.get(0)));
		for (int i = 1; i < intervals.size(); i++) {
			List<Integer> prev = intervals.get(i - 1);
			List<Integer> curr = intervals.get(i);
			if (prev.get(1) > curr.get(0)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		List<List<Integer>> list = newArrayList();
		list.add(asList(0, 30));
		list.add(asList(5, 10));
		list.add(asList(15, 20));
		System.out.println(merge(list)); // false

		List<List<Integer>> list2 = newArrayList();
		list2.add(asList(7, 10));
		list2.add(asList(2, 4));
		System.out.println(merge(list2)); // true
	}
}
