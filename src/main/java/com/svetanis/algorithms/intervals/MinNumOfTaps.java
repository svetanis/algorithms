package com.svetanis.algorithms.intervals;

// 1326. Minimum Number of Taps to Open to Water a Garden

public final class MinNumOfTaps {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int minTaps(int n, int[] ranges) {
		int[] last = intervals(ranges);
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
	
	private static int[] intervals(int[] ranges) {
		int n = ranges.length;
		int[] a = new int[n + 1];
		for (int i = 0; i < n; i++) {
			if (ranges[i] > 0) {
				int left = Math.max(0, i - ranges[i]);
				int right = i + ranges[i];
				a[left] = Math.max(a[left], right);
			}
		}
		return a;
	}

	public static void main(String[] args) {
		int[] ranges1 = { 3, 4, 1, 1, 0, 0 };
		System.out.println(minTaps(5, ranges1)); // 1

		int[] ranges2 = { 0, 0, 0, 0 };
		System.out.println(minTaps(3, ranges2)); // -1
	}
}
