package com.svetanis.algorithms.intervals;

import java.util.ArrayList;
import java.util.List;

import com.svetanis.java.base.utils.Print;

// 986. Interval List Intersections

// Given two lists of intervals, find the intersection of these two lists. 
// Each list consists of disjoint intervals sorted on their start time.

// whenever the two intervals overlap, one of the interval’s start time lies within the other interval
// the overlapping interval will be equal to:
//  start = max(a.start, b.start)
//  end = min(a.end, b.end) 
// That is, the highest start time and the lowest end time will be the overlapping interval.

public final class IntervalIntersectionSubmit {
	// Time Complexity: O(n + m)

	public static int[][] intersection(int[][] a1, int[][] a2) {
		int n = a1.length;
		int m = a2.length;
		int first = 0;
		int second = 0;
		List<int[]> list = new ArrayList<>();
		while (first < n && second < m) {
			int start = Math.max(a1[first][0], a2[second][0]);
			int end = Math.min(a1[first][1], a2[second][1]);
			if (start <= end) {
				list.add(new int[] { start, end });
			}
			if (a1[first][1] < a2[second][1]) {
				first++;
			} else {
				second++;
			}
		}
		return list.toArray(new int[list.size()][]);
	}

	public static void main(String[] args) {
		int[][] a1 = { { 0, 2 }, { 5, 10 }, { 13, 23 }, { 24, 25 } };
		int[][] a2 = { { 1, 5 }, { 8, 12 }, { 15, 24 }, { 25, 26 } };
		Print.print(intersection(a1, a2));
	}
}
