package com.svetanis.algorithms.dp.sum.max.subarr;

// 3040. Maximum Number of Operations With the Same Score II

public final class MaxOperationsSameScore {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	private int[] a;

	public int maxOperations(int[] a) {
		this.a = a;
		int n = a.length;
		int both = doDfs(1, n - 2, a[0] + a[n - 1]);
		int left = doDfs(2, n - 1, a[0] + a[1]);
		int right = doDfs(0, n - 3, a[n - 1] + a[n - 2]);
		return 1 + Math.max(both, Math.max(left, right));
	}

	private int doDfs(int left, int right, int score) {
		int n = a.length;
		Integer[][] dp = new Integer[n][n];
		return dfs(left, right, score, dp);
	}

	private int dfs(int left, int right, int score, Integer[][] dp) {
		if (right - left < 1) {
			return 0;
		}

		if (dp[left][right] != null) {
			return dp[left][right];
		}
		int max = 0;
		if (a[left] + a[left + 1] == score) {
			max = Math.max(max, 1 + dfs(left + 2, right, score, dp));
		}

		if (a[right] + a[right - 1] == score) {
			max = Math.max(max, 1 + dfs(left, right - 2, score, dp));
		}

		if (a[left] + a[right] == score) {
			max = Math.max(max, 1 + dfs(left + 1, right - 1, score, dp));
		}
		return dp[left][right] = max;
	}

	public static void main(String[] args) {
		MaxOperationsSameScore mos = new MaxOperationsSameScore();
		int[] a = { 3, 2, 1, 2, 3, 4 };
		System.out.println(mos.maxOperations(a)); // 3

		int[] a1 = { 3, 2, 6, 1, 4 };
		System.out.println(mos.maxOperations(a1)); // 2
	}
}
