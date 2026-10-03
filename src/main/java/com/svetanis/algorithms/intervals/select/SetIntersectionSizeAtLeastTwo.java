package com.svetanis.algorithms.intervals.select;


import java.util.Arrays;

// 757. Set Intersection Size At Least Two
//
// Takes intervals as [start, end] of whole numbers, both ends included, with start < end and
// start >= 0, as LC guarantees (0 <= start < end <= 10^8). Returns the size of the smallest set of
// whole numbers that has at least two numbers inside every interval. Sorts the input array in
// place.
//
// Walk the intervals in order of end. lower and upper are the two largest numbers chosen so far,
// lower < upper. Every chosen number is at most upper, and upper is an earlier end, so it is at
// most the current end. So how many chosen numbers fall inside the current interval depends only
// on where its start is: at or below lower, both lower and upper are inside and nothing is added;
// above upper, none is, and two are added; in between, only upper is, and one is added. A new
// number is always taken as far right as possible (end, then end - 1), because every later
// interval ends no earlier, so a number further right is inside at least as many of them.

public final class SetIntersectionSizeAtLeastTwo {
	// Time Complexity: O(n log n), the sort; the walk after it is one pass
	// Space Complexity: O(1) beyond the sort, which works on the input array

	public static int minSize(int[][] intervals) {
		// by end; on a tied end, the larger start first, so the narrower interval chooses its
		// numbers and the wider one with the same end finds both already inside it
		Arrays.sort(intervals, (a, b) -> a[1] != b[1]
				? Integer.compare(a[1], b[1]) // by end
				: Integer.compare(b[0], a[0])); // equal ends: later start first (b before a)
		int count = 0;
		int lower = -1; // below every allowed number: nothing chosen yet
		int upper = -1;
		for (int[] interval : intervals) {
			int start = interval[0];
			int end = interval[1];
			if (start <= lower) { // lower and upper are both inside: covered
				continue;
			}
			if (start > upper) { // no chosen number inside: take the two rightmost
				count += 2;
				lower = end - 1;
				upper = end;
			} else { // only upper is inside: add end as the second
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