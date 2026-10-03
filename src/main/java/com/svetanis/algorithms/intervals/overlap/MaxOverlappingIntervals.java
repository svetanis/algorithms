package com.svetanis.algorithms.intervals.overlap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Maximum number of overlapping intervals (GeeksforGeeks).
//
// The statement: "Given an array of intervals arr[] where each interval is represented by two
// integers [start, end] (inclusive). Find the maximum number of intervals that overlap at any point
// in time." [[1, 2], [2, 4], [3, 6]] gives 2, and [[1, 8], [2, 5], [5, 6], [3, 7]] gives 4 -- all
// four contain 5, so an interval ending at x and one starting at x DO overlap. An empty array gives 0.
//
// Each interval becomes two points on the number line: an 's' point at its start and an 'e' point
// at its end. The points are sorted by position and walked from left to right with active = the
// number of intervals open right now: +1 at an 's', -1 at an 'e'. The largest value active reaches
// is the answer. At the same position every 's' comes before every 'e', so an interval that starts
// at x is counted before one that ends at x is closed -- the inclusive reading.
//
// The same count other ways: MinPlatformsTwoArrays, MinPlatformsDifferenceArray,
// MinPlatformsMultimap and MinPlatformsPriorityQueue (trains, inclusive too). MinMeetingRoomsHeap
// and its siblings count the half-open [start, end), where touching intervals do not overlap.

public final class MaxOverlappingIntervals {
	// Time Complexity: O(n log n), sorting the 2n points
	// Space Complexity: O(n), the list of points

	public static int maxOverlap(int[][] intervals) {
		int max = 0;
		int active = 0; // intervals open at the current point
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
		System.out.println(maxOverlap(intervals1)); // 2

		int[][] intervals2 = { { 1, 8 }, { 2, 5 }, { 5, 6 }, { 3, 7 } };
		System.out.println(maxOverlap(intervals2)); // 4: all four contain 5

		int[][] intervals3 = { { 1, 3 }, { 2, 6 }, { 4, 8 }, { 6, 7 }, { 5, 7 } };
		System.out.println(maxOverlap(intervals3)); // 4: [2,6], [4,8], [6,7], [5,7] all contain 6
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
				return Integer.compare(other.type, this.type); // 's' > 'e', reversed: starts first, inclusive
			}
			return Integer.compare(this.value, other.value);
		}
	}
}
