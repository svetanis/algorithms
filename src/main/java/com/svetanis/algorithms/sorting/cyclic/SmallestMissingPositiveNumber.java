package com.svetanis.algorithms.sorting.cyclic;

import static com.svetanis.java.base.utils.Swap.swap;

// given an unsorted array containing numbers,
// find the smallest missing positive number in it

// positive numbers start from 1

public final class SmallestMissingPositiveNumber {

	public static int smpn(int[] a) {
		// Time Complexity: O(n)

		sort(a);
		for (int i = 0; i < a.length; i++) {
			if (a[i] != i + 1) {
				return i + 1;
			}
		}
		return -1;
	}

	public static void sort(int[] a) {
		// Time Complexity: O(n)

		int n = a.length;
		int i = 0;
		while (i < n) {
			int j = a[i] - 1;
			if (a[i] > 0 && a[i] < n && a[i] != a[j]) {
				swap(a, i, j);
			} else {
				i++;
			}
		}
	}

	public static void main(String[] args) {
		int[] a1 = { -3, 1, 5, 4, 2 };
		System.out.println(smpn(a1));
		int[] a2 = { 3, -2, 0, 1, 2 };
		System.out.println(smpn(a2));
		int[] a3 = { 3, 2, 5, 1 };
		System.out.println(smpn(a3));
	}
}
