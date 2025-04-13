package com.svetanis.algorithms.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1272. Remove Interval

public final class RemoveInterval {
	// Time Complexity: O(n)

	public static List<List<Integer>> removeInterval(int[][] intervals, int[] tbr) {
		int tbrs = tbr[0];
		int tbre = tbr[1];
		List<List<Integer>> list = new ArrayList<>();
		for (int[] interval : intervals) {
			int start = interval[0];
			int end = interval[1];
			if (start >= tbre || end <= tbrs) {
				list.add(Arrays.asList(start, end));
			} else {
				if (start < tbrs) {
					list.add(Arrays.asList(start, tbrs));
				}
				if (end > tbre) {
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
