package com.svetanis.algorithms.dp.fibvariants;

// 377. Combination Sum IV

// given an array of distinct integers and
// a target integer, return the number of 
// possible combinations that add up to target

public final class CombinationSumBottomUp {
	// Time Complexity: O(target * n)
	// Space Complexity: O(target)

	public static int count(int[] nums, int target) {
		int[] dp = new int[target + 1];
		dp[0] = 1;
		for (int sum = 1; sum <= target; ++sum) {
			for (int num : nums) {
				if (sum >= num) {
					dp[sum] += dp[sum - num];
				}
			}
		}
		return dp[target];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		int[] a2 = { 9 };
		int[] a3 = { 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 };
		System.out.println(count(a1, 4)); // 7
		System.out.println(count(a2, 3)); // 0
		System.out.println(count(a3, 10)); // 9

	}
}
