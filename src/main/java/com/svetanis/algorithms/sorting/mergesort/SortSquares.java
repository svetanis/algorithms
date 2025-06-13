package com.svetanis.algorithms.sorting.mergesort;

import static com.svetanis.java.base.utils.Print.print;

// 977. Squares of a Sorted Array
// twopointers.SquaringSortedArray.java

// given sorted array of both 
// positive and negative integers
// sort squares 

public final class SortSquares {
	// Time Complexity: O(n)
	// Aux Space: O(n)

	public static void sort(int[] a) {
		int pivot = pivot(a);
		if (pivot == -1) {
			return;
		}
		int[] temp = merge(a, pivot);
		for (int i = 0; i < a.length; i++) {
			a[i] = temp[i];
		}
	}

	private static int[] merge(int[] a, int pivot) {
		int n = a.length;
		int i = pivot - 1;
		int j = pivot;
		int k = 0;
		
		int[] temp = new int[n];
		while (i >= 0 && j < n) {
			int sqr1 = a[i] * a[i];
			int sqr2 = a[j] * a[j];
			if (sqr1 < sqr2) {
				temp[k] = sqr1;
				i--;
			} else {
				temp[k] = sqr2;
				j++;
			}
			k++;
		}

		while (i >= 0) {
			temp[k++] = a[i] * a[i];
			i--;
		}

		while (j < n) {
			temp[k++] = a[j] * a[j];
			j++;
		}
		return temp;
	}

	private static int pivot(int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] >= 0) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] a1 = { -6, -3, -1, 2, 4, 5 };
		int[] a2 = { -5, -4, -2, 0, 1 };
		sort(a1);
		sort(a2);
		print(a1);
		print(a2);
	}
}
