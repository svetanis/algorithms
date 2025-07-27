package com.svetanis.algorithms.dp.interval;

// 312. Burst Balloons

public final class BurstBalloonsTopDown {
	// Time Complexity: O(n^3)
	// Space Complexity: O(n^2)

	public static int burstBalloons(int[] a) {
		int[] ext = extended(a);
		int n = ext.length;
		Integer[][] dp = new Integer[n][n];
		return dfs(ext, 0, n - 1, dp);
	}

	private static int dfs(int[] a, int start, int end, Integer[][] dp) {
		if (start + 1 == end) {
			return 0;
		}
		if (dp[start][end] != null) {
			return dp[start][end];
		}
		int max = 0;
		for (int i = start + 1; i < end; i++) {
			int coins = a[start] * a[i] * a[end];
			int left = dfs(a, start, i, dp);
			int right = dfs(a, i, end, dp);
			max = Math.max(max, left + coins + right);
		}
		return dp[start][end] = max;
	}

	private static int[] extended(int[] a) {
		int n = a.length;
		int[] extended = new int[n + 2];
		extended[0] = 1;
		extended[extended.length - 1] = 1;
		System.arraycopy(a, 0, extended, 1, n);
		return extended;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 1, 5, 8 };
		System.out.println(burstBalloons(a1)); // 167
		int[] a2 = { 1, 5 };
		System.out.println(burstBalloons(a2)); // 10
	}
}
