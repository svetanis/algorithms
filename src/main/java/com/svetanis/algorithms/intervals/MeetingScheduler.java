package com.svetanis.algorithms.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1229. Meeting Scheduler

public final class MeetingScheduler {
	// Time Complexity: O(n * log n + m * log m)
	// Space Complexity: O(n + m)

	public static List<Integer> ms(int[][] slots1, int[][] slots2, int duration) {
		int n = slots1.length;
		int m = slots2.length;
		int first = 0;
		int second = 0;
		Arrays.sort(slots1, (s1, s2) -> s1[0] - s2[0]);
		Arrays.sort(slots2, (s1, s2) -> s1[0] - s2[0]);
		while (first < n && second < m) {
			int start = Math.max(slots1[first][0], slots2[second][0]);
			int end = Math.min(slots1[first][1], slots2[second][1]);
			if (end - start >= duration) {
				return Arrays.asList(start, start + duration);
			}
			if (slots1[first][1] < slots2[second][1]) {
				first++;
			} else {
				second++;
			}
		}
		return new ArrayList<>();
	}

	public static void main(String[] args) {
		int[][] slots1 = { { 10, 50 }, { 60, 120 }, { 140, 210 } };
		int[][] slots2 = { { 0, 15 }, { 25, 50 }, { 60, 70 }, { 80, 100 } };
		System.out.println(ms(slots1, slots2, 8)); // [25,33]
	}
}
