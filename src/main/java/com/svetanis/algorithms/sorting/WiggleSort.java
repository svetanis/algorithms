package com.svetanis.algorithms.sorting;

import com.svetanis.java.base.utils.Print;

// 280. Wiggle Sort

public final class WiggleSort {
	// Time Complexity: O(n)

	public static void sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			boolean odd = i % 2 == 1;
			boolean even = i % 2 == 0;
			boolean one = odd && a[i] < a[i - 1];
			boolean two = even && a[i] > a[i - 1];
			if (one || two) {
				swap(a, i, i - 1);
			}
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a = { 6, 4, 7, 5, 2 };
		sort(a);
		Print.print(a); // 6,7,5,4,2

		int[] a1 = { 3, 5, 2, 1, 6, 4 };
		sort(a1);
		Print.print(a1); // 3,5,1,6,2,4
	}
}
