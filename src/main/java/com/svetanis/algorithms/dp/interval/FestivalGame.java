package com.svetanis.algorithms.dp.interval;

import java.util.Arrays;
import java.util.List;

// same as 312. Burst Balloons

public final class FestivalGame {
	// Time Complexity: O(n^3)

	public static long burstBalloons(List<Integer> target) {
		int n = target.size();
		Long[][] dp = new Long[n][n];
		return dfs(target, 0, n - 1, dp);
	}

	private static long dfs(List<Integer> a, int start, int end, Long[][] dp) {
		if (start > end) {
			return 0;
		}
		if (dp[start][end] != null) {
			return dp[start][end];
		}
		long max = 0;
		for (int i = start; i <= end; i++) {
			long lm = (start == 0 ? 1 : a.get(start - 1));
			long rm = (end == a.size() - 1) ? 1 : a.get(end + 1);
			long coins = lm * a.get(i) * rm;
			long left = dfs(a, start, i - 1, dp);
			long right = dfs(a, i + 1, end, dp);
			max = Math.max(max, left + coins + right);
		}
		return dp[start][end] = max;
	}

	public static void main(String[] args) {
		System.out.println(burstBalloons(Arrays.asList(3, 1, 5, 8))); // 167
		System.out.println(burstBalloons(Arrays.asList(1, 5))); // 10
	}
}
