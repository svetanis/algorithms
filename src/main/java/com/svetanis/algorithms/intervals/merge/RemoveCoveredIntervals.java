package com.svetanis.algorithms.intervals.merge;


import java.util.Arrays;

// 1288. Remove Covered Intervals
//
// intervals holds [start, end] rows, no two the same, in any order; the array is sorted in place.
// [c, d] covers [a, b] when c <= a and b <= d, so sharing an endpoint still counts. Returns how
// many intervals are left after removing every one that another interval covers. At least one
// interval is assumed; an empty array throws. LC guarantees 1 <= n <= 1000, unique intervals and
// 0 <= start < end <= 10^5; it writes them half-open, [start, end), which gives the same covering
// test.
//
// Sort by start, and among equal starts put the longer interval first. Then an interval can only
// be covered by one that comes before it: a later one starts no earlier, and with the same start
// it is shorter. Every earlier one starts no later, so the interval is covered exactly when some
// earlier interval ends at or after its end, and it is enough to know the largest end so far.
// prev is the interval holding that largest end. An interval that ends further than prev is not
// covered: it is counted and becomes prev.
//
// CompletelyOverlappingIntervals sorts the same way to ask only whether any interval lies inside
// another.

public final class RemoveCoveredIntervals {
	// Time Complexity: O(n log n), the sort; the scan after it is one pass
	// Space Complexity: O(n), the sort's temporary buffer

	public static int removeCoveredIntervals(int[][] intervals) {
		// equal starts: longer first, so the longer one is prev when the shorter one is checked
		Arrays.sort(intervals, (a, b) -> a[0] != b[0]
				? Integer.compare(a[0], b[0]) // by start
				: Integer.compare(b[1], a[1])); // equal starts: longer first (b before a)
		int count = 1;
		int[] prev = intervals[0]; // throws on an empty array
		for (int i = 1; i < intervals.length; i++) {
			int[] interval = intervals[i];
			if (prev[1] < interval[1]) { // <: an equal end is covered
				count++;
				prev = interval;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] intervals1 = { { 1, 4 }, { 3, 6 }, { 2, 8 } };
		System.out.println(removeCoveredIntervals(intervals1)); // 2

		int[][] intervals2 = { { 1, 4 }, { 2, 3 } };
		System.out.println(removeCoveredIntervals(intervals2)); // 1
	}
}
