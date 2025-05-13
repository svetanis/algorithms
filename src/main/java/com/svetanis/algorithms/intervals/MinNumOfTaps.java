package com.svetanis.algorithms.intervals;

// 1326. Minimum Number of Taps to Open to Water a Garden

public final class MinNumOfTaps {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int minTaps(int n, int[] ranges) {
		int len = ranges.length;
		int[] last = new int[len + 1];
		for (int i = 0; i < len; i++) {
			if (ranges[i] > 0) {
				int left = Math.max(0, i - ranges[i]);
				int right = i + ranges[i];
				last[left] = Math.max(last[left], right);
			}
		}
		int curr = 0;
		int prev = 0;
		int taps = 0;
		for (int i = 0; i < n; i++) {
			curr = Math.max(curr, last[i]);
			if (curr <= i) {
				return -1;
			}
			if (i == prev) {
				taps++;
				prev = curr;
			}
		}
		return taps;
	}

	public static void main(String[] args) {
		int[] ranges1 = { 3, 4, 1, 1, 0, 0 };
		System.out.println(minTaps(5, ranges1)); // 1

		int[] ranges2 = { 0, 0, 0, 0 };
		System.out.println(minTaps(3, ranges2)); // -1
	}
}
