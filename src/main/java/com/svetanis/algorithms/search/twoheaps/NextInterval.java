package com.svetanis.algorithms.search.twoheaps;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;
import com.svetanis.algorithms.sorting.interval.Interval;

// given an array of intervals,
// find the next interval of each interval
// in a list of intervals, for an interval i
// its next interval j will have the smallest
// start greater than or equal to the end of i

// return an array containing indices of the 
// next interval of each input interval. if
// there is no next interval of a given interval,
// return -1. it is given that none of the intervals
// have the same start point

public final class NextInterval {

	public static ImmutableList<Integer> next(Interval[] a) {
		// Time Complexity: O(n * log n)
		// Space Complexity: O(n)

		// max heap sorted on max start time
		Queue<Integer> msh = new PriorityQueue<>((i, j) -> a[j].start - a[i].start);
		// max heap sorted on max end time
		Queue<Integer> meh = new PriorityQueue<>((i, j) -> a[j].end - a[i].end);
		// push all intervals into two heaps
		for (int i = 0; i < a.length; i++) {
			msh.add(i);
			meh.add(i);
		}
		List<Integer> list = newArrayList();
		// iterate over intervals of the maxEndHeap
		while (!meh.isEmpty()) {
			int end = meh.poll();
			int str = msh.poll();
			if (a[end].end <= a[str].start) {
				// save topStart index
				list.add(0, str);
			} else {
				// couldn't find next interval
				list.add(0, -1);
				// put topStart back to maxStartHeap
				msh.add(str);
			}
		}
		return newList(list);
	}

	public static void main(String[] args) {
		Interval i1 = new Interval(2, 3);
		Interval i2 = new Interval(3, 4);
		Interval i3 = new Interval(5, 6);
		Interval[] a1 = { i1, i2, i3 };
		System.out.println(next(a1)); // [1, 2, -1]

		Interval i4 = new Interval(1, 5);
		Interval i5 = new Interval(4, 6);
		Interval[] a2 = { i2, i4, i5 };
		System.out.println(next(a2)); // [2, -1, -1]
	}
}
