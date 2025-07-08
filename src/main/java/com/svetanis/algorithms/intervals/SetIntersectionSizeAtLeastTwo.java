package com.svetanis.algorithms.intervals;

import java.util.Arrays;

// 757. Set Intersection Size At Least Two

public final class SetIntersectionSizeAtLeastTwo {
	// Time Complexity: O(n log n)

	public static int minSize(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> 
		(a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]));
		int count = 0;
		int lower = -1;
		int upper = -1;
		for (int[] interval : intervals) {
			int start = interval[0];
			int end = interval[1];
			if (start <= lower) { // complete overlap
				continue;
			}
			if (start > upper) { // disjoined intervals
				count += 2;
				lower = end - 1;
				upper = end;
			} else { // partial overlap
				count += 1;
				lower = upper;
				upper = end;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] intervals1 = { { 1, 3 }, { 3, 7 }, { 8, 9 } };
		System.out.println(minSize(intervals1)); // 5
		int[][] intervals2 = { { 1, 3 }, { 1, 4 }, { 2, 5 }, { 3, 5 } };
		System.out.println(minSize(intervals2)); // 3
		int[][] intervals3 = { { 1, 2 }, { 2, 3 }, { 2, 4 }, { 4, 5 } };
		System.out.println(minSize(intervals3)); // 5
		int[][] intervals4 = { { 1, 3 }, { 3, 7 }, { 5, 7 }, { 7, 8 } };
		System.out.println(minSize(intervals4)); // 5
	}
}