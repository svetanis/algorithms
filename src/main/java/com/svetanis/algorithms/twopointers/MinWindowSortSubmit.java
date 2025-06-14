package com.svetanis.algorithms.twopointers;

// 581. Shortest Unsorted Continuous Subarray

public final class MinWindowSortSubmit {
	// Time complexity: O(n)

	public static int subArrayLen(int[] a) {
		int n = a.length;
		int left = -1;
		int right = -1;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (max > a[i]) {
				right = i;
			} else {
				max = a[i];
			}

			if (min < a[n - i - 1]) {
				left = n - i - 1;
			} else {
				min = a[n - i - 1];
			}
		}
		return right == -1 ? 0 : right - left + 1;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 6, 4, 8, 10, 9, 15 };
		System.out.println(subArrayLen(a1)); // 5

		int[] a2 = { 1, 2, 3, 4 };
		System.out.println(subArrayLen(a2)); // 0

		int[] a3 = { 1 };
		System.out.println(subArrayLen(a3)); // 0
	}
}
