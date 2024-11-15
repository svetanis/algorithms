package com.svetanis.algorithms.dp.dns;

import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.List;

// 368. Largest Divisible Subset

public final class LargestDivisibleSubsetLenTopDown {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int lds(List<Integer> nums) {
		int n = nums.size();
		Collections.sort(nums);
		int[][] dp = new int[n][n + 1];
		return lds(nums, 0, -1, dp);
	}

	private static int lds(List<Integer> nums, int index, int prev, int[][] dp) {
		int n = nums.size();
		// base case
		if (index == n) {
			return 0;
		}
		if (dp[index][prev + 1] != 0) {
			return dp[index][prev + 1];
		}

		// include
		int incl = 0;
		if (prev == -1 || nums.get(index) % nums.get(prev) == 0) {
			incl = 1 + lds(nums, index + 1, index, dp);
		}
		// exclude
		int excl = lds(nums, index + 1, prev, dp);
		dp[index][prev + 1] = max(incl, excl);
		return dp[index][prev + 1];
	}

	public static void main(String[] args) {
		System.out.println(lds(asList(1, 2, 3))); // 2
		System.out.println(lds(asList(1, 2, 4, 8))); // 4
	}
}
