package com.svetanis.algorithms.dp.sum.given.subseq;

import static com.svetanis.java.base.utils.Nums.isOdd;
import static java.util.Arrays.asList;

import java.util.List;

// Partition problem is to determine whether a given set can be partitioned  
// into two subsets such that the sum of elements in both subsets is same. 

public final class BalancedPartitionTopDown {

	public static boolean canPartition(List<Integer> nums) {
		// Time Complexity: O(sum * n)
		// Space Complexity: O(sum * n)

		int sum = nums.stream().mapToInt(Integer::intValue).sum();
		// if sum is odd there can't be
		// two subsets with equal sum
		if (isOdd(sum)) {
			return false;
		}
		return isSum(nums, sum/2);
	}

	private static boolean isSum(List<Integer> nums, int sum) {
		int n = nums.size();
		Boolean[][] dp = new Boolean[n + 1][sum + 1];
		return isSum(nums, 0, sum, dp);
	}

	private static boolean isSum(List<Integer> nums, int i, int sum, 
			Boolean[][] dp) {
		// Time complexity: O(n * sum)

		if (sum == 0) {
			return true;
		}

		if (i >= nums.size() || sum < 0) {
			return false;
		}

		if (dp[i][sum] != null) {
			return dp[i][sum];
		}

		// 1. include a[i]
		boolean incl = isSum(nums, i + 1, sum - nums.get(i), dp);

		// 2. exclude a[i]
		boolean excl = isSum(nums, i + 1, sum, dp);

		dp[i][sum] = incl || excl;
		return dp[i][sum];
	}

	public static void main(String[] args) {
		System.out.println(canPartition(asList(3, 4, 7))); // true
		System.out.println(canPartition(asList(1, 5, 11, 5))); // true
		System.out.println(canPartition(asList(4, 5, 10, 7))); // false
		System.out.println(canPartition(asList(0, 0, 0, 0))); // true
	}
}
