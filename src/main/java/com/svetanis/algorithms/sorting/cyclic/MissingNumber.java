package com.svetanis.algorithms.sorting.cyclic;

import static com.svetanis.java.base.utils.Swap.swap;

// given an array containing n distinct numbers
// taken from the range 0 to n. since the array
// has only n numbers out of the total n + 1
// numbers, find the missing number

public final class MissingNumber {

	public static int missing(int[] a) {
		// Time Complexity: O(n)

		sort(a);
		for (int i = 0; i < a.length; i++) {
			if (a[i] != i) {
				return i;
			}
		}
		return -1;
	}

	public static void sort(int[] a) {
		// Time Complexity: O(n)

		int i = 0;
		while (i < a.length) {
			int j = a[i];
			if (j < a.length && a[i] != a[j]) {
				swap(a, i, j);
			} else {
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] a1 = { 4, 0, 3, 1 };
		System.out.println(missing(a1));
		int[] a2 = { 8, 3, 5, 2, 4, 6, 0, 1 };
		System.out.println(missing(a2));
	}
}
