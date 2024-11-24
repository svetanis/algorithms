package com.svetanis.algorithms.dp.dualsequence;

import static java.lang.Math.min;

// 583. Delete Operation for Two Strings 

public final class DeleteOperationMinStepsBruteForce {

	public static int delete(String x, String y) {
		int n = x.length();
		int m = y.length();
		return dfs(x, y, n, m);
	}

	private static int dfs(String x, String y, int n, int m) {
		if (n == 0 && m == 0) {
			return 0;
		}
		if (n == 0) {
			return 1 + dfs(x, y, n, m - 1);
		}
		if (m == 0) {
			return 1 + dfs(x, y, n - 1, m);
		}
		if (x.charAt(n - 1) == y.charAt(m - 1)) {
			return dfs(x, y, n - 1, m - 1);
		}
		int d1 = 1 + dfs(x, y, n - 1, m);
		int d2 = 1 + dfs(x, y, n, m - 1);
		return min(d1, d2);
	}

	public static void main(String[] args) {
		System.out.println(delete("sea", "eat")); // 2
		System.out.println(delete("leetcode", "etco")); // 4
	}
}
