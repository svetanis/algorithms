package com.svetanis.algorithms.intervals.select;

import java.util.Arrays;

// 452. Minimum Number of Arrows to Burst Balloons
//
// Takes balloons as [xstart, xend], both ends included. An arrow shot at x bursts every balloon
// with xstart <= x <= xend, so an arrow at a shared endpoint bursts both balloons. Returns the
// fewest arrows that burst them all; 0 for no balloons. Sorts the input array in place.
//
// Look at the balloon that ends first. Some arrow has to hit it, and moving that arrow right,
// up to that balloon's end, never makes it miss anything it hit: every other balloon ends no
// earlier. So one arrow goes exactly at that end, and it bursts every balloon that starts at or
// before it. Sorted by end, the walk keeps the position of the last arrow: a balloon that starts
// at or before it is already burst, and a balloon that starts after it needs a new arrow at its
// own end.
//
// Same greedy as ActivitySelection, with the same strict test: the fewest arrows equals the most
// balloons no two of which share a point. NonOverlappingIntervals (LC 435) uses >= instead,
// because there intervals that only touch do not overlap.

public final class MinNumOfArrowsToBurstBalloons {
	// Time Complexity: O(n log n), the sort; the walk after it is one pass
	// Space Complexity: O(1) beyond the sort, which works on the input array

	public static int count(int[][] intervals) {
		// sort by end point
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
		int count = 0;
		long end = Long.MIN_VALUE; // below every int, so the first balloon always needs an arrow
		for (int[] interval : intervals) {
			int start = interval[0];
			// starts after the last arrow: that arrow misses it, shoot a new one at its end.
			// > not >=: a balloon starting exactly at the arrow is burst by it
			if (start > end) {
				count++;
				end = interval[1];
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] intervals1 = { { 10, 16 }, { 2, 8 }, { 1, 6 }, { 7, 12 } };
		System.out.println(count(intervals1)); // 2

		int[][] intervals2 = { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 } };
		System.out.println(count(intervals2)); // 4

		int[][] intervals3 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
		System.out.println(count(intervals3)); // 2
	}
}
