package com.svetanis.algorithms.search.binary;

import java.util.Arrays;

// 1099. Two Sum Less Than K

public final class TwoSumLessThanK1099 {
	// Time Complexity: O(n log n)

	public static int maxSum(int[] a, int target) {
		Arrays.sort(a);
		int max = -1;
		int left = 0;
		int right = a.length - 1;
		while (left < right) {
			int sum = a[left] + a[right];
			if (sum < target) {
				max = Math.max(max, sum);
				left++;
			} else {
				right--;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a = { 34, 23, 1, 24, 75, 33, 54, 8 };
		System.out.println(maxSum(a, 60)); // 58 -- 24 + 34, LeetCode's own example 1

		int[] a1 = { 10, 20, 30 };
		System.out.println(maxSum(a1, 15)); // -1 -- no pair sums below k
	}
}