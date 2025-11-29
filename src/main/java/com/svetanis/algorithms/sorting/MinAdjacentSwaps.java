package com.svetanis.algorithms.sorting;

// 2340. Minimum Adjacent Swaps to Make a Valid Array

public final class MinAdjacentSwaps {
	// Time complexity: O(n)

	public static int minAdjacentSwaps(int[] a) {
		int n = a.length;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int minIndex = 0;
		int maxIndex = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] < min) {
				min = a[i];
				minIndex = i;
			}
			if (a[i] >= max) {
				max = a[i];
				maxIndex = i;
			}
		}
		if (minIndex == maxIndex) {
			return 0;
		}
		int swap = minIndex > maxIndex ? 1 : 0;
		return minIndex + n - 1 - maxIndex - swap;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 4, 5, 5, 3, 1 };
		System.out.println(minAdjacentSwaps(a1)); // 6
	}
}
