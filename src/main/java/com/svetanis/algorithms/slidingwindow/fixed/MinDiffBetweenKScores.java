package com.svetanis.algorithms.slidingwindow.fixed;

import java.util.Arrays;

// 1984. Minimum Difference Between Highest and Lowest of K Scores 

public final class MinDiffBetweenKScores {
	// Time Complexity: O(n log n)
	// Aux Space: O(1)

	public static int minDiff(int[] nums, int k) {
		if (k == 1) {
			return 0;
		}
		Arrays.sort(nums);
		int n = nums.length;
		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; i <= n - k; i++) {
			int curr = nums[i];
			minDiff = Math.min(minDiff, nums[i + k - 1] - curr);
		}
		return minDiff;
	}

	public static int minDiff2(int[] nums, int k) {
		if (k == 1) {
			return 0;
		}
		Arrays.sort(nums);
		int n = nums.length;
		int minDiff = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int curr = nums[i];
			if (i + 1 >= k) {
				minDiff = Math.min(minDiff, curr - nums[i - k + 1]);
			}
		}
		return minDiff;
	}

	public static void main(String[] args) {
		int[] a1 = { 90 };
		System.out.println(minDiff(a1, 1)); // 0

		int[] a2 = { 9, 4, 1, 7 };
		System.out.println(minDiff(a2, 2)); // 2
	}
}
