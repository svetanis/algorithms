package com.svetanis.algorithms.sorting.cyclic;

import static com.svetanis.java.base.utils.Print.print;
import static com.svetanis.java.base.utils.Swap.swap;

// given an array containing n objects
// each object when created was assigned
// a unique number from the range 1 to n
// based on their creation sequence

// sort objects in-place on their creation
// sequence number in O(n) and without
// using any extra space.

public final class CyclicSort {

	public static void sort(int[] a) {
		// Time Complexity: O(n)

		int i = 0;
		while (i < a.length) {
			int j = a[i] - 1;
			if (a[i] != a[j]) {
				swap(a, i, j);
			} else {
				i++;
			}
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 1, 5, 4, 2 };
		sort(a1);
		print(a1);
		int[] a2 = { 2, 6, 4, 3, 1, 5 };
		sort(a2);
		print(a2);
		int[] a3 = { 1, 5, 6, 4, 3, 2 };
		sort(a3);
		print(a3);
	}
}
