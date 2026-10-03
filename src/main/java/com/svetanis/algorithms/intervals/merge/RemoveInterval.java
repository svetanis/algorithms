package com.svetanis.algorithms.intervals.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1272. Remove Interval
//
// intervals is sorted and no two of them overlap; tbr is the interval to be removed. All are
// half-open: [a, b) holds every point from a up to, but not including, b. Returns what is left
// of intervals once every point of tbr is taken out, still sorted.
//
// Each interval is handled on its own. If it ends at or before tbr starts, or starts at or after
// tbr ends, the two share no point, because the end point is never included, and it is kept
// whole. Otherwise at most two pieces of it survive: the part before tbr's start, when it starts
// earlier than tbr, and the part after tbr's end, when it ends later than tbr.

public final class RemoveInterval {
	// Time Complexity: O(n), one pass, at most two pieces per interval
	// Space Complexity: O(n), the output

	public static List<List<Integer>> removeInterval(int[][] intervals, int[] tbr) {
		int tbrs = tbr[0]; // start of the part to remove
		int tbre = tbr[1]; // end of the part to remove, not itself removed
		List<List<Integer>> list = new ArrayList<>();
		for (int[] interval : intervals) {
			int start = interval[0];
			int end = interval[1];
			if (start >= tbre || end <= tbrs) { // half-open: sharing an endpoint shares no point
				list.add(Arrays.asList(start, end));
			} else {
				if (start < tbrs) { // a piece survives before the removed part
					list.add(Arrays.asList(start, tbrs));
				}
				if (end > tbre) { // a piece survives after the removed part
					list.add(Arrays.asList(tbre, end));
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		int[][] intervals = { { 1, 4 }, { 6, 8 }, { 10, 13 } };
		int[] tbr = { 7, 12 };
		System.out.println(removeInterval(intervals, tbr)); // [1,4), [6,7), [12,13)
	}
}
