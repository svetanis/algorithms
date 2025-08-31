package com.svetanis.algorithms.permutations;

import com.svetanis.java.base.utils.Print;

// 1053. Previous Permutation With One Swap

public final class PreviousPermutationOneSwap {
	// Time Complexity: O(n^2)

	public static int[] prevPermutation(int[] a) {
		int n = a.length;
		for (int i = n - 1; i > 0; --i) {
			if(a[i - 1] > a[i]) {
				for(int j = n - 1; j > i - 1; --j) {
					if(a[j] < a[i - 1] && a[j] != a[j - 1]) {
						swap(a, i - 1, j);
						return a;
					}
				}
			}
		}
		return a;
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 2, 1 };
		Print.print(prevPermutation(a1)); // 3 1 2

		int[] a2 = { 1, 1, 5 };
		Print.print(prevPermutation(a2)); // 1 1 5

		int[] a3 = { 1, 9, 4, 6, 7 };
		Print.print(prevPermutation(a3)); // 1 7 4 6 9
	}
}
