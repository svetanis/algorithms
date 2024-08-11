package com.svetanis.algorithms.sorting.mergesort;

import static com.svetanis.java.base.utils.Print.print;

// given two sorted array,
// merge them in a sorted manner

public final class Merge2SortedArrays {
	// Time Complexity: O(n + m)
	// Space Complexity: O(n + m)

	public static int[] mergeSorted(int[] a, int[] b) {
		int n = a.length;
		int m = b.length;

		if (a[n - 1] < b[0]) {
			return mergeInOrder(a, b);
		}

		if (b[m - 1] < a[0]) {
			return mergeInOrder(b, a);
		}
		return merge(a, b);
	}

	private static int[] merge(int[] a, int[] b) {
		int n = a.length;
		int m = b.length;
		int i = 0, j = 0, k = 0;
		int[] c = new int[n + m];
		while (i < n && j < m) {
			if (a[i] <= b[j]) {
				c[k] = a[i++];
			} else {
				c[k] = b[j++];
			}
			k++;
		}
		if (i < n) {
			for (int p = i; p < n; ++p) {
				c[k++] = a[p];
			}
		}
		if (j < m) {
			for (int p = j; p < m; ++p) {
				c[k++] = b[p];
			}
		}
		return c;
	}

	private static int[] mergeInOrder(int[] a, int[] b) {
		int n = a.length;
		int m = b.length;
		int[] c = new int[n + m];

		int k = 0;
		for (int i = 0; i < n; ++i) {
			c[k++] = a[i];
		}
		for (int i = 0; i < m; ++i) {
			c[k++] = b[i];
		}
		return c;
	}

	public static void main(String[] args) {
		int[] a = { 2, 8, 13, 15, 20 };
		int[] b = { 5, 7, 9, 25 };
		print(mergeSorted(a, b));
	}
}
