package com.svetanis.algorithms.dp.sum.given.subseq;

// 474. Ones and Zeros

public final class OnesAndZeros {
	// Time Complexity: O(k * m * n)
	// Space Complexity: O(m * n)

	public static int largestSubsetLen(String[] strs, int m, int n) {
		int[][] dp = new int[m + 1][n + 1];
		for (String s : strs) {
			long count = s.chars().filter(c -> c == '0').count();
			int zeros = new Long(count).intValue();
			int ones = s.length() - zeros;
			for (int i = m; i >= zeros; i--) {
				for (int j = n; j >= ones; j--) {
					int incl = 1 + dp[i - zeros][j - ones];
					dp[i][j] = Math.max(dp[i][j], incl);
				}
			}
		}
		return dp[m][n];
	}

	public static void main(String[] args) {
		String[] s1 = { "10", "0001", "111001", "1", "0" };
		System.out.println(largestSubsetLen(s1, 5, 3)); // 4
		String[] s2 = { "10", "0", "1" };
		System.out.println(largestSubsetLen(s2, 1, 1)); // 2
	}
}
