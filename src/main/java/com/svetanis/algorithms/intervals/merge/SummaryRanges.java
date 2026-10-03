package com.svetanis.algorithms.intervals.merge;

import java.util.ArrayList;
import java.util.List;

// 228. Summary Ranges
//
// a is sorted in increasing order with no repeated numbers. Returns, in order, one string per
// run of consecutive integers: "4->7" for a run from 4 to 7, or just "4" for a run of one number.
// An empty array gives an empty list.
//
// start is the index where the current run begins. end moves forward while the next number is
// exactly one more than the number at end; when it stops, a[start..end] is a whole run, and the
// next run begins at end + 1.

public final class SummaryRanges {
	// Time Complexity: O(n); end only moves forward and each new run starts where the last one
	// stopped, so every index is visited once across both loops
	// Space Complexity: O(n), the output

	public static List<String> summaryRanges(int[] a) {
		int n = a.length;
		List<String> intervals = new ArrayList<>();
		int end = 0;
		for (int start = 0; start < n;) {
			end = start;
			// end + 1 < n is checked first; since a is increasing, a[end] + 1 can only wrap past
			// the largest int at the last index, which that check already stops
			while (end + 1 < n && a[end + 1] == a[end] + 1) {
				end++;
			}
			intervals.add(interval(a, start, end));
			start = end + 1;
		}
		return intervals;
	}

	private static String interval(int[] a, int start, int end) {
		String single = String.valueOf(a[start]);
		return start == end ? single : String.format("%d->%d", a[start], a[end]);
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 1, 2, 4, 5, 7 };
		System.out.println(summaryRanges(a1)); // 0->2,4->5,7

		int[] a2 = { 0, 2, 3, 4, 6, 8, 9 };
		System.out.println(summaryRanges(a2)); // 0,2->4,6,8->9

	}
}
