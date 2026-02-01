package com.svetanis.algorithms.twopointers;

import com.svetanis.java.base.utils.Print;

// 922. Sort Array By Parity II

public final class SortByParityII {
	// Time Complexity: O(n)

	public static int[] sort(int[] a) {
		int even = 0;
		int odd = 1;
		int n = a.length;
		while (even < n) {
			if (a[even] % 2 != 0) {
				while (a[odd] % 2 != 0) {
					odd += 2;
				}
				swap(a, even, odd);
			}
			even += 2;
		}
		return a;
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 2, 5, 7 };
		Print.print(sort(a1)); // 4 5 2 7

		int[] a2 = { 2, 3 };
		Print.print(sort(a2)); // 2 3
	}
}
