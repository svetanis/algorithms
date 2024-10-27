package com.svetanis.algorithms.prefixsum;

import static com.svetanis.java.base.utils.Print.print;

// 370. Range Addition

public final class RangeAddition {
	// Query Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] rangeAddition(int n, int[][] updates) {
		int[] a = new int[n];
		for (int[] update : updates) {
			int srt = update[0];
			int end = update[1];
			int inc = update[2];
			a[srt] += inc;
			if (end + 1 < n) {
				a[end + 1] -= inc;
			}
		}
		for (int i = 1; i < n; i++) {
			a[i] += a[i - 1];
		}
		return a;
	}

	public static void main(String[] args) {
		int[][] updates = { { 1, 3, 2 }, { 2, 4, 3 } };
		print(rangeAddition(5, updates)); // 0,2,5,5,5
	}
}
