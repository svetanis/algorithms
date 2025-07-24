package com.svetanis.algorithms.dp.sum.given.subseq;

import java.util.stream.IntStream;

// 1049. Last Stone Weight II

public final class LastStoneWeightTopDown {
	// Time Complexity: O(target * n)
	// Space Complexity: O(n)

	public static int lastStoneWeight(int[] stones) {
		int sum = IntStream.of(stones).sum();
		int target = sum / 2;
		Integer[][] dp = new Integer[stones.length][target + 1];
		int max = dfs(stones, 0, 0, target, dp);
		return sum - 2 * max;
	}

	private static int dfs(int[] stones, int index, int sum, int target, Integer[][] dp) {
		if (index == stones.length) {
			return sum;
		}
		if (dp[index][sum] != null) {
			return dp[index][sum];
		}
		int incl = 0;
		if (sum + stones[index] <= target) {
			incl = dfs(stones, index + 1, sum + stones[index], target, dp);
		}
		int excl = dfs(stones, index + 1, sum, target, dp);
		return dp[index][sum] = Math.max(incl, excl);
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 7, 4, 1, 8, 1 };
		System.out.println(lastStoneWeight(a1)); // 1
		int[] a2 = { 31, 26, 33, 21, 40 };
		System.out.println(lastStoneWeight(a2)); // 5
	}
}
