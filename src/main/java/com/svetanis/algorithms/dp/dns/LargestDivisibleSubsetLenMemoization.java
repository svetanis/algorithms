package com.svetanis.algorithms.dp.dns;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 368. Largest Divisible Subset

public final class LargestDivisibleSubsetLenMemoization {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int lds(List<Integer> nums) {
		int n = nums.size();
		Collections.sort(nums);
		int[] dp = new int[n];
		Arrays.fill(dp, -1);
		int max = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, lds(nums, i, dp));
		}
		return max;
	}

	private static int lds(List<Integer> nums, int index, int[] dp) {
		// base case: first num can always form a subset
		if (index == 0) {
			return 1;
		}
		if (dp[index] != -1) {
			return dp[index];
		}
		// a num can always be a subset of itself
		int max = 1;
		int curr = nums.get(index);
		for (int i = 0; i < index; i++) {
			// extend prev chain
			int prev = nums.get(i);
			if (curr % prev == 0) {
				int len = 1 + lds(nums, i, dp);
				max = Math.max(max, len);
			}
		}
		dp[index] = max;
		return max;
	}

	public static void main(String[] args) {
		System.out.println(lds(asList(1, 2, 3))); // 2
		System.out.println(lds(asList(1, 2, 4, 8))); // 4
	}
}
