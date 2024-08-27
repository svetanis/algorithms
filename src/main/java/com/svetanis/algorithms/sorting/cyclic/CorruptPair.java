package com.svetanis.algorithms.sorting.cyclic;

import static com.svetanis.java.base.utils.Swap.swap;

import com.svetanis.java.base.Pair;

// given an unsorted array containing n numbers
// taken from the range 1 to n. the array originally
// contained all the numbers from 1 to n, but due to
// a data error, one of the numbers got duplicated
// which also resulted in one number going missing.
// find both these numbers

public final class CorruptPair {

	public static Pair<Integer, Integer> corruptPair(int[] a) {
		// Time Complexity: O(n)

		sort(a);
		for (int i = 0; i < a.length; i++) {
			if (a[i] != i + 1) {
				// a[i] is duplicate
				// i + 1 is missing
				return Pair.build(a[i], i + 1);
			}
		}
		return Pair.build(-1, -1);
	}

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
		int[] a1 = { 3, 1, 2, 5, 2 };
		System.out.println(corruptPair(a1));
		int[] a2 = { 3, 1, 2, 3, 6, 4 };
		System.out.println(corruptPair(a2));
	}
}
