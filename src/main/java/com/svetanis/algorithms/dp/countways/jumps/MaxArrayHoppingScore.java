package com.svetanis.algorithms.dp.countways.jumps;

// 3205. Maximum Array Hopping Score I

public final class MaxArrayHoppingScore {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int maxScoreSimple(int[] a) {
		int max = 0;
		int score = 0;
		for(int i = a.length - 1; i > 0; i--) {
			max = Math.max(max, a[i]);
			score += max;
		}
		return score;
	}
	
	
	public int maxScore(int[] a) {
		Integer[] dp = new Integer[a.length];
		return dfs(a, dp, 0);
	}

	private int dfs(int[] a, Integer[] dp, int index) {
		int n = a.length;
		if (dp[index] != null) {
			return dp[index];
		}
		dp[index] = 0;
		for (int j = index + 1; j < n; j++) {
			int score = (j - index) * a[j];
			dp[index] = Math.max(dp[index], score + dfs(a, dp, j));
		}
		return dp[index];
	}

	public static void main(String[] args) {
		MaxArrayHoppingScore mhs = new MaxArrayHoppingScore();
		int[] a1 = { 2, 3, 1, 4 };
		System.out.println(mhs.maxScore(a1)); // 12
	}
}
