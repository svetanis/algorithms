package com.svetanis.algorithms.intervals.select;

import java.util.Arrays;

// 452. Minimum Number of Arrows to Burst Balloons
//
// Input: balloons as [xstart, xend], both ends included. An arrow shot at x bursts every balloon
// with xstart <= x <= xend, so an arrow at a shared endpoint bursts both balloons. Returns the
// fewest arrows that burst them all; 0 for no balloons. Sorts the input array in place. On
// LeetCode: 1 <= points.length <= 10^5, -2^31 <= xstart < xend <= 2^31 - 1 -- the whole int
// range, which is why the last arrow's position starts as a long below every int.
//
// The one idea: one row is one balloon, and the walk carries one number, where the last arrow was
// shot. Look at the balloon that ends first. Some arrow has to hit it, and moving that arrow right,
// up to that balloon's end, never makes it miss anything it hit: every other balloon ends no
// earlier. So one arrow goes exactly at that end, and it bursts every balloon that starts at or
// before it. Sorted by end, the walk keeps the position of the last arrow: a balloon that starts
// at or before it is already burst, and a balloon that starts after it needs a new arrow at its
// own end.
//
// Same greedy as ActivitySelection, with the same strict test: the fewest arrows equals the most
// balloons no two of which share a point. NonOverlappingIntervals (LC 435) uses >= instead,
// because there intervals that only touch do not overlap.
//
// Time: O(n log n) -- the sort; the walk after it is one pass.
// Space: O(1) -- beyond the sort, which works on the input array.
//
// Siblings: none for 452; ActivitySelection and NonOverlappingIntervals above are the same greedy
// on other problems.

public final class MinNumOfArrowsToBurstBalloons {

	public static int count(int[][] intervals) {
		// sort by end point
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1])); // SORT by end
		int count = 0;
		long end = Long.MIN_VALUE; // below every int, so the first balloon always needs an arrow
		for (int[] interval : intervals) {
			int start = interval[0];
			// starts after the last arrow: that arrow misses it, shoot a new one at its end.
			// > not >=: a balloon starting exactly at the arrow is burst by it
			if (start > end) { // TEST: the last arrow misses it
				count++; // SHOOT a new arrow
				end = interval[1]; // AT its end: as far right as still bursts it
			} // SKIP: already burst by the last arrow
		}
		return count; // RETURN
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
