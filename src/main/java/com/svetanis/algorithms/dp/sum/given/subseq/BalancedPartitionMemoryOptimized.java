package com.svetanis.algorithms.dp.sum.given.subseq;

import static com.svetanis.java.base.utils.Nums.isOdd;
import static java.util.Arrays.asList;

import java.util.List;

// Partition problem is to determine whether a given set can be partitioned  
// into two subsets such that the sum of elements in both subsets is same. 

public final class BalancedPartitionMemoryOptimized {

	public static boolean canPartition(List<Integer> nums) {
		// Time Complexity: O(sum * n)
		// Space Complexity: O(sum * n)

		int sum = nums.stream().mapToInt(Integer::intValue).sum();
		// if sum is odd there can't be
		// two subsets with equal sum
		if (isOdd(sum)) {
			return false;
		}
		return isSum(nums, sum / 2);
	}

	private static boolean isSum(List<Integer> nums, int target) {
		int n = nums.size();
		boolean[][] dp = new boolean[2][target + 1];
		dp[0][0] = true;
		for (int i = 1; i <= n; i++) {
			for (int s = 0; s <= target; s++) {
				int diff = s - nums.get(i - 1);
				if (diff < 0) {
					dp[1][s] = dp[0][s];
				} else {
					dp[1][s] = dp[0][s] || dp[0][diff];
				}
			}
			for (int s = 1; s <= target; s++) {
				dp[0][s] = dp[1][s];
				dp[1][s] = false;
			}
		}
		return dp[0][target];
	}

	public static void main(String[] args) {
		System.out.println(canPartition(asList(3, 4, 7))); // true
		System.out.println(canPartition(asList(1, 5, 11, 5))); // true
		System.out.println(canPartition(asList(4, 5, 10, 7))); // false
		System.out.println(canPartition(asList(0, 0, 0, 0))); // true
	}
}