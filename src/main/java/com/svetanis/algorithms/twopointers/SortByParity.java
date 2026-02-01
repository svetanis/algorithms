package com.svetanis.algorithms.twopointers;

import com.svetanis.java.base.utils.Print;

// 905. Sort Array By Parity

public final class SortByParity {
	// Time Complexity: O(n)

	public static int[] sortByParity(int[] a) {
		int index = 0;
		int n = a.length;
		for (int j = 0; j < n; j++) {
			if (a[j] % 2 == 0) {
				swap(a, index, j);
				index += 1;
			}
		}
		return a;
	}

	public static int[] sortByParity2(int[] a) {
		int n = a.length;
		int left = 0;
		int right = n - 1;
		while (left < right) {
			while (left < right && a[left] % 2 == 0) {
				left += 1;
			}
			while (left < right && a[right] % 2 != 0) {
				right -= 1;
			}
			swap(a, left, right);
			left += 1;
			right -= 1;
		}
		return a;
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 1, 2, 4 };
		Print.print(sortByParity(a1)); // 2 4 3 1

		int[] a2 = { 0 };
		Print.print(sortByParity(a2)); // 0
	}
}
