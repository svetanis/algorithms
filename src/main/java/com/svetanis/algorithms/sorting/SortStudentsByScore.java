package com.svetanis.algorithms.sorting;

import java.util.Arrays;

import com.svetanis.java.base.utils.Print;

// 2545. Sort the Students by Their Kth Score

public final class SortStudentsByScore {
	// Time Complexity: O(n log n)

	public static int[][] sort(int[][] scores, int k) {
		Arrays.sort(scores, (a, b) -> b[k] - a[k]);
		return scores;
	}

	public static void main(String[] args) {
		int[][] m1 = { { 10, 6, 9, 1 }, { 7, 5, 11, 2 }, { 4, 8, 3, 15 } };
		Print.print(sort(m1, 2));

		int[][] m2 = { { 3, 4 }, { 5, 6 } };
		Print.print(sort(m2, 0));
	}
}
