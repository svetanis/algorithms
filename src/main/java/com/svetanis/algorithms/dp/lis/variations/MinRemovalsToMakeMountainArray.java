package com.svetanis.algorithms.dp.lis.variations;

import java.util.Arrays;

// 1671. Minimum Number of Removals to Make Mountain Array

public final class MinRemovalsToMakeMountainArray {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int minRemovals(int[] a) {
		int[] lil = longestIncreasingLeft(a);
		int[] lir = longestIncreasingRight(a);
		int max = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (lil[i] > 1 && lir[i] > 1) {
				int size = lil[i] + lir[i] - 1;
				max = Math.max(max, size);
			}
		}
		return n - max;
	}

	private static int[] longestIncreasingLeft(int[] a) {
		int[] lil = new int[a.length];
		Arrays.fill(lil, 1);
		for (int i = 1; i < a.length; i++) {
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j]) {
					lil[i] = Math.max(lil[i], lil[j] + 1);
				}
			}
		}
		return lil;
	}

	private static int[] longestIncreasingRight(int[] a) {
		int n = a.length;
		int[] lir = new int[n];
		Arrays.fill(lir, 1);
		for (int i = n - 2; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				if (a[i] > a[j]) {
					lir[i] = Math.max(lir[i], lir[j] + 1);
				}
			}
		}
		return lir;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 1 };
		System.out.println(minRemovals(a1)); // 0
		int[] a2 = { 2, 1, 1, 5, 6, 2, 3, 1 };
		System.out.println(minRemovals(a2)); // 3
	}
}
