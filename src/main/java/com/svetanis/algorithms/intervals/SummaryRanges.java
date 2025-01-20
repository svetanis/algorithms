package com.svetanis.algorithms.intervals;

import java.util.ArrayList;
import java.util.List;

// 228. SummaryRanges

public final class SummaryRanges {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static List<String> summaryRanges(int[] a) {
		int n = a.length;
		List<String> intervals = new ArrayList<>();
		int end = 0;
		for (int start = 0; start < n;) {
			end = start;
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
