package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 945. Minimum Increment to Make Array Unique

public final class MinIncrement {
	// Time Complexity: O(n log n)
	// Space Complexity: O(log n)

	public static int minIncrement(int[] a) {
		Arrays.sort(a);
		int totalIncrements = 0;
		int minAvailable = -1;
		for (int num : a) {
			minAvailable = Math.max(minAvailable + 1, num);
			totalIncrements += minAvailable - num;
		}
		return totalIncrements;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 2 };
		System.out.println(minIncrement(a)); // 1

		int[] a1 = { 3, 2, 1, 2, 1, 7 };
		System.out.println(minIncrement(a1)); // 6
	}
}
