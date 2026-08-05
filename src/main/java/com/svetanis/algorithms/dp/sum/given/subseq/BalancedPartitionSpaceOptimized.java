package com.svetanis.algorithms.dp.sum.given.subseq;

import static com.svetanis.java.base.utils.Nums.isOdd;
import static java.util.Arrays.asList;

import java.util.List;

// 416. Partition Equal Subset Sum

// Partition problem is to determine 
// whether a given set can be partitioned  
// into two subsets such that the sum 
// of elements in both subsets is same.

// the halfway step between BalancedPartitionBottomUp and
// BalancedPartitionSubmit. BottomUp keeps all n + 1 rows, but the loop only
// ever reads row i - 1, so two rows are enough: build into row 1, copy it
// down to row 0, repeat. Submit takes the last step and collapses those two
// rows into one, which is what SubSetSumSpaceOptimized also does.
//
// dp[0][0] stays true throughout and is never cleared by the copy loop,
// which starts at s = 1: a sum of 0 is reachable at every i by taking
// nothing, so that cell is a real base case, not leftover state.

public final class BalancedPartitionSpaceOptimized {
	// Time Complexity: O(target * n)
	// Space Complexity: O(target) -- two rows of target + 1, not a full table

	public static boolean canPartition(List<Integer> nums) {
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
