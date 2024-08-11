package com.svetanis.algorithms.sorting.mergesort;

import static com.svetanis.java.base.utils.Print.print;

// given an integer array of which 
// both first half and second half are sorted.
// merge two sorted halves of array into 
// single sorted array

public final class MergeTwoHalves {

	public static void sort(int[] a) {
		// Time Complexity: O(n)
		// Space Complexity: O(n)

		int n = a.length;
		int index = pivot(a);
		if (index == 0) {
			return;
		}

		int[] temp = merge(a, index);

		for (int i = 0; i < n; i++) {
			a[i] = temp[i];
		}
	}

	private static int[] merge(int[] a, int pivot) {
		int n = a.length;
		int i = 0, k = 0, j = pivot;
		int[] temp = new int[n];
		while (i < pivot && j < n) {
			if (a[i] < a[j]) {
				temp[k] = a[i++];
			} else {
				temp[k] = a[j++];
			}
			k++;
		}

		while (i < pivot) {
			temp[k++] = a[i++];
		}

		while (j < n) {
			temp[k++] = a[j++];
		}
		return temp;
	}

	private static int pivot(int[] a) {
		int n = a.length;
		int index = 0;
		for (int i = 0; i < n - 1; i++) {
			if (a[i] > a[i + 1]) {
				index = i + 1;
				break;
			}
		}
		return index;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, 8, -1, 7, 10 };
		int[] a2 = { -4, 6, 9, -1, 3 };
		sort(a1);
		sort(a2);
		print(a1);
		print(a2);
	}
}
