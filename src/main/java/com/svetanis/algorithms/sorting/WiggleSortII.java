package com.svetanis.algorithms.sorting;

import java.util.Arrays;

import com.svetanis.java.base.utils.Print;

// 324. Wiggle Sort II

public final class WiggleSortII {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static void sort(int[] a) {
		int[] sorted = a.clone();
		Arrays.sort(sorted);
		int n = a.length;
		int left = (n - 1) / 2;
		int right = n - 1;
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				a[i] = sorted[left];
				left -= 1;
			} else {
				a[i] = sorted[right];
				right -= 1;
			}
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 5, 1, 1, 6, 4 };
		sort(a);
		Print.print(a); // 1 6 1 5 1 4

		int[] a1 = { 1, 3, 2, 2, 3, 1 };
		sort(a1);
		Print.print(a1); // 2 3 1 3 1 2
	}
}
