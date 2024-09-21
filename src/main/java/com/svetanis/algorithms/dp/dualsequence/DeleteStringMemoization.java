package com.svetanis.algorithms.dp.dualsequence;

import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// given two strings determine the min cost
// required to delete chars from either str
// to make them equal given a particular cost
// to each character.

public final class DeleteStringMemoization {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static int delete(String x, String y, List<Integer> costs) {
		int n = x.length();
		int m = y.length();
		Integer[][] memo = new Integer[n + 1][m + 1];
		return dfs(x, y, n, m, memo, costs);
	}

	private static int dfs(String x, String y, int n, int m, Integer[][] memo, List<Integer> costs) {
		if (n == 0 && m == 0) {
			return 0;
		}
		if (memo[n][m] != null) {
			return memo[n][m];
		}

		if (n == 0) {
			memo[0][m] = dfs(x, y, n, m - 1, memo, costs) + costs.get(y.charAt(m - 1) - 'a');
			return memo[0][m];
		}
		if (m == 0) {
			memo[n][0] = dfs(x, y, n - 1, m, memo, costs) + costs.get(x.charAt(n - 1) - 'a');
			return memo[n][0];
		}

		if (x.charAt(n - 1) == y.charAt(m - 1)) {
			memo[n][m] = dfs(x, y, n - 1, m - 1, memo, costs);
		} else {
			int d1 = dfs(x, y, n - 1, m, memo, costs) + costs.get(x.charAt(n - 1) - 'a');
			int d2 = dfs(x, y, n, m - 1, memo, costs) + costs.get(y.charAt(m - 1) - 'a');
			memo[n][m] = min(d1, d2);
		}
		return memo[n][m];
	}

	public static void main(String[] args) {
		List<Integer> costs = asList(1, 2, 3);
		System.out.println(delete("abb", "bba", costs)); // 2

		List<Integer> costs2 = asList(1, 1, 3, 2, 5, 10, 2, 1);
		System.out.println(delete("abcdefgh", "hgfedcba", costs2)); // 30

		List<Integer> costs3 = asList(4, 2, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 1);
		System.out.println(delete("dzzbdb", "dbdbzz", costs3)); // 0

	}
}
