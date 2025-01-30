package com.svetanis.algorithms.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Largest Overlap of Intervals

// given an array of intervals, determine
// the max number of intervals that overlap
// at any point. Each interval is half-open,
// meaning it includes the start point but
// excludes the end point

public final class LargestOverlapOfIntervals {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int insert(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		int max = 0;
		int active = 0;
		List<Point> points = points(intervals);
		for (Point point : points) {
			if (point.type == 's') {
				active++;
			} else {
				active--;
			}
			max = Math.max(max, active);
		}
		return max;
	}

	private static List<Point> points(int[][] intervals) {
		List<Point> list = new ArrayList<>();
		for (int[] interval : intervals) {
			list.add(new Point(interval[0], 's'));
			list.add(new Point(interval[1], 'e'));
		}
		Collections.sort(list);
		return list;
	}

	public static void main(String[] args) {
		int[][] intervals1 = { { 1, 2 }, { 2, 4 }, { 3, 6 } };
		System.out.println(insert(intervals1)); // 2

		int[][] intervals2 = { { 1, 3 }, { 2, 6 }, { 4, 8 }, { 6, 7 }, { 5, 7 } };
		System.out.println(insert(intervals2)); // 3
	}

	private static class Point implements Comparable<Point> {
		private int value;
		private char type;

		public Point(int value, char type) {
			this.value = value;
			this.type = type;
		}

		@Override
		public int compareTo(Point other) {
			if (this.value == other.value) {
				return this.type - other.type;
			}
			return this.value - other.value;
		}
	}
}
