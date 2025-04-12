package com.svetanis.algorithms.intervals;

import java.util.Arrays;

// 1288. Remove Covered Intervals

public final class RemoveCoveredIntervals {
	// Time Complexity: O(n log n)

	public static int rci(int[][] intervals) {
		Arrays.sort(intervals, (i1, i2) -> (i1[0] == i2[0]) ? i2[1] - i1[1] : i1[0] - i2[0]);
		int count = 1;
		int[] prev = intervals[0];
		for (int i = 1; i < intervals.length; i++) {
			int[] interval = intervals[i];
			if (prev[1] < interval[1]) {
				count++;
				prev = interval;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] intervals1 = { { 1, 4 }, { 3, 6 }, { 2, 8 } };
		System.out.println(rci(intervals1)); // 2

		int[][] intervals2 = { { 1, 4 }, { 2, 3 } };
		System.out.println(rci(intervals2)); // 1
	}
}
