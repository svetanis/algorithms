package com.svetanis.algorithms.dp.countways;

// 1416. Restore The Array

public final class RestoreArrayTopDown {

	private static final int MOD = (int) 1e9 + 7;

	private int n;
	private String s;

	public int numberOfArrays(String s, int k) {
		this.s = s;
		this.n = s.length();
		Integer[] dp = new Integer[n + 1];
		return dfs(0, k, dp);
	}

	private int dfs(int index, int k, Integer[] dp) {
		if (index == n) {
			return 1;
		}
		if (s.charAt(index) == '0') {
			return 0;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		long num = 0;
		long count = 0;
		for (int end = index; end < n; end++) {
			num = num * 10 + (s.charAt(end) - '0');
			if (num > k) {
				break;
			}
			count = (count + dfs(end + 1, k, dp)) % MOD;
		}
		return dp[index] = (int) count;
	}

	public static void main(String[] args) {
		RestoreArrayTopDown cwt = new RestoreArrayTopDown();
		System.out.println(cwt.numberOfArrays("1000", 10000)); // 1
		System.out.println(cwt.numberOfArrays("1000", 10)); // 0
		System.out.println(cwt.numberOfArrays("1317", 2000)); // 8
	}
}
