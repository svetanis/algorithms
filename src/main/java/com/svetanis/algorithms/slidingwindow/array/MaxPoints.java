package com.svetanis.algorithms.slidingwindow.array;

import static java.lang.Math.max;

// collect maximum points in array by moving either in single or
// both directions from given index i and making k moves.

public final class MaxPoints {

	public static int maxPoints(int[] a, int k, int i) {
		// Time Complexity: O(n)
		// Space Complexity: O(1)
		int n = a.length;
		int sum = 0;
		int left = (k > i) ? 0 : i - k;

		for (int j = left; j <= left + k && j < n; j++) {
			sum += a[j];
		}

		int max = sum;
		for (int j = left + k + 1; j < n && j <= i + k; j++) {
			sum += a[j] - a[j - k - 1];
			max = max(max, sum);
		}
		return max;
	}

	public static void main(String[] args) {

		int[] a1 = { 5, 6, 4, 2, 8, 3, 1 };
		System.out.println(maxPoints(a1, 3, 3));

		int[] a2 = { 5, 6, 4, 2, 8, 3, 1 };
		System.out.println(maxPoints(a2, 2, 5));

	}
}
