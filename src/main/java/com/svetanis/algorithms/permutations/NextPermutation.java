package com.svetanis.algorithms.permutations;

import com.svetanis.java.base.utils.Print;

// 31. Next Permutation

public final class NextPermutation {
	// Time Complexity: O(n)

	public static void nextPermutation(int[] a) {
		int len = a.length;
		int pivot = len - 2;
		// find digit that is less than
		// its immediate right digit
		while (pivot >= 0 && a[pivot] >= a[pivot + 1]) {
			pivot--;
		}
		if (pivot < 0) {
			reverse(a, 0, len - 1);
			return;
		}
		// find digit that is greater
		// than the digit found above
		int rightMostSuccessor = len - 1;
		while (a[pivot] >= a[rightMostSuccessor]) {
			rightMostSuccessor--;
		}
		swap(a, pivot, rightMostSuccessor);
		reverse(a, pivot + 1, len - 1);
	}

	private static void reverse(int[] a, int start, int end) {
		while (start < end) {
			swap(a, start, end);
			start++;
			end--;
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		nextPermutation(a1);
		Print.print(a1); // 1, 3, 2

		int[] a2 = { 3, 2, 1 };
		nextPermutation(a2);
		Print.print(a2); // 1, 2, 3

		int[] a3 = { 1, 1, 5 };
		nextPermutation(a3);
		Print.print(a3); // 1, 5, 1
	}
}
