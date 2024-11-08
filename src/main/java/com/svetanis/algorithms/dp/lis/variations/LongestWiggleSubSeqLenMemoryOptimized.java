package com.svetanis.algorithms.dp.lis.variations;

import static java.lang.Math.max;

// 376. Wiggle SubSequence

// a wiggle sequence is a sequence where
// the differences between successive
// numbers strictly alternate between
// positive and negative. 

public final class LongestWiggleSubSeqLenMemoryOptimized {
	// Time Complexity : O(n)

	public static int lws(int[] a) {
		int n = a.length;
		int up = 1;
		int down = 1;
		for (int i = 1; i < n; i++) {
			if (a[i] > a[i - 1]) {
				up = max(up, down + 1);
			} else if (a[i] < a[i - 1]) {
				down = max(down, up + 1);
			}
		}
		return up > down ? up : down;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 7, 4, 9, 2, 5 };
		System.out.println(lws(a1)); // 6

		int[] a2 = { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 };
		System.out.println(lws(a2)); // 7

		int[] a3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println(lws(a3)); // 2
	}
}
