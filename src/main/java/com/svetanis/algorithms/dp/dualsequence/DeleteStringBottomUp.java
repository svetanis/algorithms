package com.svetanis.algorithms.dp.dualsequence;

import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// given two strings determine the min cost
// required to delete chars from either str
// to make them equal given a particular cost
// to each character.

public final class DeleteStringBottomUp {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int delete(String x, String y, List<Integer> costs) {
		int n = x.length();
		int m = y.length();
		int[][] dp = new int[1001][1001];
		for (int i = 1; i <= n; i++) {
			dp[i][0] = dp[i - 1][0] + costs.get(x.charAt(i - 1) - 'a');
		}
		for (int j = 1; j <= y.length(); j++) {
			dp[0][j] = dp[0][j - 1] + costs.get(y.charAt(j - 1) - 'a');
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					int left = dp[i - 1][j] + costs.get(x.charAt(i - 1) - 'a');
					int top = dp[i][j - 1] + costs.get(y.charAt(j - 1) - 'a');
					dp[i][j] = min(left, top);
				}
			}
		}
		return dp[n][m];
	}

	public static void main(String[] args) {
		List<Integer> costs1 = asList(1, 2, 3);
		System.out.println(delete("abb", "bba", costs1)); // 2

		List<Integer> costs2 = asList(1, 1, 3, 2, 5, 10, 2, 1);
		System.out.println(delete("abcdefgh", "hgfedcba", costs2)); // 30

		List<Integer> costs3 = asList(4, 2, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 1);
		System.out.println(delete("dzzbdb", "dbdbzz", costs3)); // 0
	}
}
