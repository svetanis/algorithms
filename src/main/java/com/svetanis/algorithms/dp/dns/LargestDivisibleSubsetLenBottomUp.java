package com.svetanis.algorithms.dp.dns;

import static java.lang.Math.max;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;

import java.util.Collections;
import java.util.List;

// 368. Largest Divisible Subset

public final class LargestDivisibleSubsetLenBottomUp {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int lds(List<Integer> nums) {
		int n = nums.size();
		Collections.sort(nums);
		int[] dp = new int[n];
		fill(dp, 1);
		int max = 1;
		for (int i = 1; i < n; i++) {
			int curr = nums.get(i);
			for (int j = 0; j < i; j++) {
				int prev = nums.get(j);
				if (curr % prev == 0 && dp[i] <= dp[j]) {
					dp[i] = dp[j] + 1;
					max = max(max, dp[i]);
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(lds(asList(1, 2, 3))); // 2
		System.out.println(lds(asList(1, 2, 4, 8))); // 4
	}
}
