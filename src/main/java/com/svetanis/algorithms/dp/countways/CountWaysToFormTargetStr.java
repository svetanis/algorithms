package com.svetanis.algorithms.dp.countways;

// 1639. Number of Ways to Form a Target String Given a Dictionary

public final class CountWaysToFormTargetStr {
	// Time Complexity: O(n * m * k)
	// Space Complexity: O(n * m)

	private static final int MOD = (int) 1e9 + 7;

	private int n; // word length
	private int m; // target length
	private String target;
	private int[][] counts;

	public int countWays(String[] words, String target) {
		this.n = words[0].length();
		this.m = target.length();
		this.target = target;
		this.counts = counts(words);
		Integer[][] dp = new Integer[m][n];
		return dfs(0, 0, dp);
	}

	private int dfs(int i, int j, Integer[][] dp) {
		if (i >= m) {
			return 1;
		}
		if (j >= n) {
			return 0;
		}
		if (dp[i][j] != null) {
			return dp[i][j];
		}
		long excl = dfs(i, j + 1, dp);
		int freq = counts[target.charAt(i) - 'a'][j];
		long incl = (long) freq * dfs(i + 1, j + 1, dp);
		long ways = (excl + incl) % MOD;
		return dp[i][j] = (int) ways;
	}

	private int[][] counts(String[] words) {
		int[][] counts = new int[26][n];
		for (String word : words) {
			for (int i = 0; i < n; i++) {
				int index = word.charAt(i) - 'a';
				counts[index][i]++;
			}
		}
		return counts;
	}

	public static void main(String[] args) {
		String[] w1 = { "acca", "bbbb", "caca" };
		CountWaysToFormTargetStr cwt = new CountWaysToFormTargetStr();
		System.out.println(cwt.countWays(w1, "aba")); // 6

		String[] w2 = { "abba", "baab" };
		CountWaysToFormTargetStr cwt1 = new CountWaysToFormTargetStr();
		System.out.println(cwt1.countWays(w2, "bab")); // 4
	}
}
