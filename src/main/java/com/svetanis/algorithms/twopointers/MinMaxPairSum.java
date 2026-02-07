package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 1877. Minimize Maximum Pair Sum in Array

public final class MinMaxPairSum {
	// Time Complexity: O(n log n)
	// Space Complexity: O(log n)

	public static int minPairSum(int[] a) {
		Arrays.sort(a);
		int max = 0;
		int left = 0;
		int right = a.length - 1;
		while (left < right) {
			int sum = a[left] + a[right];
			max = Math.max(max, sum);
			left++;
			right--;
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 5, 2, 3 };
		System.out.println(minPairSum(a1)); // 7

		int[] a2 = { 3, 5, 4, 2, 4, 6 };
		System.out.println(minPairSum(a2)); // 8
	}
}
