package com.svetanis.algorithms.prefixsum.range;

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
		int[][] updates = { { 1, 3, 2 }, { 2, 4, 3 }, { 0, 2, -2 } };
		print(rangeAddition(5, updates)); // -2,0,3,5,3

		int[][] updates2 = { { 2, 4, 6 }, { 5, 6, 8 }, { 1, 9, -4 } };
		print(rangeAddition(10, updates2)); // 0,-4,2,2,2,4,4,-4,-4,-4
	}
}
