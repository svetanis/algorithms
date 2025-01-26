package com.svetanis.algorithms.sorting.quicksort.impl;

import static com.svetanis.java.base.utils.Print.print;

// 912. Sort an Array

public final class QuickSortSubmit {
	// Time Complexity: O(n log n)

	public static int[] sortArray(int[] a) {
		quickSort(a, 0, a.length - 1);
		return a;
	}

	public static void quickSort(int[] a, int left, int right) {
		if (left >= right) {
			return;
		}
		int pivot = pivot(a, left, right);
		quickSort(a, left, pivot);
		quickSort(a, pivot + 1, right);
	}

	private static int pivot(int[] a, int left, int right) {
		int pivot = a[(left + right) >> 1];
		int start = left - 1;
		int end = right + 1;
		while (start < end) {
			while (a[++start] < pivot) {
			}
			while (a[--end] > pivot) {
			}
			if (start < end) {
				int temp = a[start];
				a[start] = a[end];
				a[end] = temp;
			}
		}
		return end;
	}

	public static void main(String[] args) {
		int[] a = { 1000, 80, 10, 50, 70, 60, 90, 20, 30, 40, 0, -1000 };
		print(sortArray(a));
		int[] a1 = { 5, 2, 3, 1 };
		print(sortArray(a1));
		int[] a2 = { 5, 1, 1, 2, 0, 0 };
		print(sortArray(a2));
		int[] a3 = { -2, 3, -5 };
		print(sortArray(a3));
	}
}