package com.svetanis.algorithms.sorting;

import java.util.Arrays;

// 1051. Height Checker

public final class HeightChecker {
	// Time complexity: O(n log n)

	public static int heightChecker(int[] heights) {
		int[] counts = new int[101];
		for (int height : heights) {
			counts[height] += 1;
		}
		int count = 0;
		int index = 0;
		for (int height = 1; height <= 100; height++) {
			while (counts[height]-- > 0) {
				if (heights[index] != height) {
					count += 1;
				}
				index += 1;
			}
		}
		return count;
	}

	public static int heightChecker2(int[] heights) {
		int n = heights.length;
		int[] expected = heights.clone();
		Arrays.sort(expected);
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (heights[i] != expected[i]) {
				count += 1;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 4, 2, 1, 3 };
		System.out.println(heightChecker(a1)); // 3

		int[] a2 = { 5, 1, 2, 3, 4 };
		System.out.println(heightChecker(a2)); // 5

		int[] a3 = { 1, 2, 3, 4, 5 };
		System.out.println(heightChecker(a3)); // 0
	}
}
