package com.svetanis.algorithms.sorting.cyclic;

import static com.svetanis.java.base.utils.Swap.swap;

// given an unsorted array containing n + 1 numbers
// taken from the range 1 to n. the array has only
// one duplicate but it can be repeated multiple times
// find that duplicate number without using any extra 
// space. allowed to modify the input array 

public final class DuplicateNumber {

	public static int duplicate(int[] a) {
		// Time Complexity: O(n)

		int i = 0;
		while (i < a.length) {
			int j = a[i] - 1;
			if (a[i] != i + 1) {
				if (a[i] != a[j]) {
					swap(a, i, j);
				} else {
					return a[i];
				}
			} else {
				i++;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 4, 4, 3, 2 };
		System.out.println(duplicate(a1));
		int[] a2 = { 2, 1, 3, 3, 5, 4 };
		System.out.println(duplicate(a2));
		int[] a3 = { 2, 4, 1, 4, 4 };
		System.out.println(duplicate(a3));
	}
}
