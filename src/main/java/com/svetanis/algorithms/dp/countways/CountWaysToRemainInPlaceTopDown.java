package com.svetanis.algorithms.dp.countways;

// 1269. Number of Ways to Stay in the Same Place After Some Steps

public final class CountWaysToRemainInPlaceTopDown {
	// Time Complexity: O(steps * len)
	// Space Complexity: O(steps * len)

	private static final int MOD = (int) 1e9 + 7;

	private int len; // array length
	private int steps; // number of steps

	public int countWays(int steps, int len) {
		this.steps = steps;
		this.len = Math.min(steps, len);
		Integer[][] dp = new Integer[this.len + 1][steps + 1];
		return dfs(0, 0, dp);
	}

	private int dfs(int i, int step, Integer[][] dp) {
		if (i < 0 || i >= len || step > steps) {
			return 0;
		}
		if (step == steps && i == 0) {
			return 1;
		}
		if (dp[i][step] != null) {
			return dp[i][step];
		}
		int count = dfs(i, step + 1, dp);
		if (i > 0) {
			count = (count + dfs(i - 1, step + 1, dp)) % MOD;
		}
		if (i < len - 1) {
			count = (count + dfs(i + 1, step + 1, dp)) % MOD;
		}
		return dp[i][step] = count;
	}

	public static void main(String[] args) {
		CountWaysToRemainInPlaceTopDown cwt = new CountWaysToRemainInPlaceTopDown();
		System.out.println(cwt.countWays(3, 2)); // 4
		System.out.println(cwt.countWays(2, 4)); // 2
		System.out.println(cwt.countWays(4, 2)); // 8
	}
}
