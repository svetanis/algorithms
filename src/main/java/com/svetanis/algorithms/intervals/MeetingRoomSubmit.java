package com.svetanis.algorithms.intervals;

import java.util.Arrays;

// 252. Meeting Rooms

// Given an array of meeting time intervals 
// consisting of start and end times
// find if a person could attend all meetings.

public final class MeetingRoomSubmit {
	// Time Complexity: O(n * log n)
	// Space Complexity: O(1)

	public static boolean canAttend(int[][] intervals) {
		// sort intervals based on the start time
		Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
		for (int i = 1; i < intervals.length; i++) {
			int[] prev = intervals[i - 1];
			int[] curr = intervals[i];
			if (prev[1] > curr[0]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] intervals = { { 0, 30 }, { 5, 10 }, { 15, 20 } };
		System.out.println(canAttend(intervals)); // false

		int[][] intervals2 = { { 7, 10 }, { 2, 4 } };
		System.out.println(canAttend(intervals2)); // true
	}
}
