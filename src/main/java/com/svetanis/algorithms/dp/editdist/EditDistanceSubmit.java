package com.svetanis.algorithms.dp.editdist;

import static java.lang.Math.min;

// 72. Edit Distance

// Given strings s1 and s2, we need to transform s1 into s2 
// by deleting, inserting, or replacing characters. 
// Write a function to calculate the count 
// of the min number of edit operations.

public final class EditDistanceSubmit {
	// Time complexity: O(n * m)

	public static int editDist(String x, String y) {
		int n = x.length();
		int m = y.length();
		Integer[][] dp = new Integer[n + 1][m + 1];
		return dfs(x, y, n, m, dp);
	}

	private static int dfs(String x, String y, int n, int m, Integer[][] dp) {
		// base cases
		if (n == 0 && m == 0) {
			return 0;
		}
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		if (dp[n][m] != null) {
			return dp[n][m];
		}
		if (x.charAt(n - 1) == y.charAt(m - 1)) {
			dp[n][m] = dfs(x, y, n - 1, m - 1, dp);
		} else {
			int delete = 1 + dfs(x, y, n - 1, m, dp);
			int insert = 1 + dfs(x, y, n, m - 1, dp);
			int replace = 1 + dfs(x, y, n - 1, m - 1, dp);
			dp[n][m] = min(min(delete, insert), replace);
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		System.out.println(editDist("Zeil", "trials")); // 4
		System.out.println(editDist("cat", "act")); // 2
		System.out.println(editDist("COMBO", "COIN")); // 3
		System.out.println(editDist("Anshuman", "Antihuman")); // 2
		System.out.println(editDist("intention", "execution")); // 5
		System.out.println(editDist("brainstorming", "imagination")); // 9
		System.out.println(editDist("dj", "abcdef")); // 5
	}
}
