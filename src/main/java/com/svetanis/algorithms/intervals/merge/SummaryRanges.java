package com.svetanis.algorithms.intervals.merge;

import java.util.ArrayList;
import java.util.List;

// 228. Summary Ranges
//
// a is sorted in increasing order with no repeated numbers. Returns, in order, one string per
// run of consecutive integers: "4->7" for a run from 4 to 7, or just "4" for a run of one number.
// An empty array gives an empty list. On LeetCode: 0 <= nums.length <= 20,
// -2^31 <= nums[i] <= 2^31 - 1, all values unique, sorted in ascending order.
//
// The one idea: one output string is one run of consecutive integers, found by one index range.
// start is the index where the current run begins. end moves forward while the next number is
// exactly one more than the number at end; when it stops, a[start..end] is a whole run, and the
// next run begins at end + 1.
//
// Time: O(n) -- end only moves forward and each new run starts where the last one stopped, so
// every index is visited once across both loops.
// Space: O(n) -- the output.
//
// Siblings: none for 228. DisjointIntervals (352) builds the same runs one number at a time, from
// a stream in any order.

public final class SummaryRanges {

	public static List<String> summaryRanges(int[] a) {
		int n = a.length;
		List<String> intervals = new ArrayList<>();
		int end = 0;
		for (int start = 0; start < n;) {
			end = start; // START: a run of one number
			// end + 1 < n is checked first; since a is increasing, a[end] + 1 can only wrap past
			// the largest int at the last index, which that check already stops
			while (end + 1 < n && a[end + 1] == a[end] + 1) { // GROW: the next number is one more
				end++;
			}
			intervals.add(interval(a, start, end)); // RECORD: a[start..end] is a whole run
			start = end + 1; // NEXT: the next run begins after this one
		}
		return intervals; // RETURN
	}

	private static String interval(int[] a, int start, int end) {
		String single = String.valueOf(a[start]); // ONE number: "4"
		return start == end ? single : String.format("%d->%d", a[start], a[end]); // RUN: "4->7"
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 1, 2, 4, 5, 7 };
		System.out.println(summaryRanges(a1)); // [0->2, 4->5, 7]

		int[] a2 = { 0, 2, 3, 4, 6, 8, 9 };
		System.out.println(summaryRanges(a2)); // [0, 2->4, 6, 8->9]

	}
}
