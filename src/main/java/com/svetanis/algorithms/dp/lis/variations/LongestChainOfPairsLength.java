package com.svetanis.algorithms.dp.lis.variations;

import java.util.Arrays;
import java.util.Comparator;

// 646. Maximum Length of Pair Chain

// You can select pairs in any order.

public final class LongestChainOfPairsLength {
	// Time Complexity: O(n log n)

	public static int maxLength(int[][] pairs) {
		Arrays.sort(pairs, Comparator.comparingInt(p -> p[1]));
		int max = 0;
		int end = Integer.MIN_VALUE;
		for (int[] pair : pairs) {
			if (end < pair[0]) {
				end = pair[1];
				max++;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] g1 = { { 1, 2 }, { 2, 3 }, { 3, 4 } };
		System.out.println(maxLength(g1)); // 2
		int[][] g2 = { { 1, 2 }, { 7, 8 }, { 4, 5 } };
		System.out.println(maxLength(g2)); // 3
	}
}
