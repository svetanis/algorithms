package com.svetanis.algorithms.search.median;

import static com.svetanis.java.base.utils.Swap.swap;

public final class MedianUnsortedMinSelect {

	public static int minSelect(int[] a) {
		// time complexity: O(k * n)
		
		int n = a.length;
		int k = n / 2;

		for (int i = 0; i < k; ++i) {
			int index = i;
			for (int j = i + 1; j < n; ++j) {
				index = j;
			}
			swap(a, i, index);
		}
		return a[k - 1];
	}

	public static void main(String[] args) {
		int[] a = { 7, 14, 10, 12, 2, 11, 29, 3, 4 };
		System.out.println("median: " + minSelect(a));

		int[] a1 = { 0, 1, 2, 3, -2, 4, -4 };
		System.out.println("median: " + minSelect(a1));

		int[] a2 = { 6, 7, 8, 1, 2, 3, 4, 5, 9, 10 };
		System.out.println("median: " + minSelect(a2));
	}
}
