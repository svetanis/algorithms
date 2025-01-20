package com.svetanis.algorithms.intervals;

import java.util.Arrays;
import java.util.Comparator;

// 452. Minimum Number of Arrows to Burst Balloons

public final class MinNumOfArrowsToBurstBalloons {
	// Time Complexity: O(n * log n)
	// Space Complexity: O(1)

	public static int count(int[][] intervals) {
		// sort by end point
		Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));
		int count = 0;
		long end = Long.MIN_VALUE;
		for (int[] interval : intervals) {
			int start = interval[0];
			// current doesn't overlap with previous,
			// increment arrow count and update end
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
