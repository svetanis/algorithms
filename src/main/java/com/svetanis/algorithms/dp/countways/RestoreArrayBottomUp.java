package com.svetanis.algorithms.dp.countways;

// 1416. Restore The Array

public final class RestoreArrayBottomUp {

	private static final int MOD = (int) 1e9 + 7;

	public int numberOfArrays(String s, int k) {
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[n] = 1;
		for (int i = n - 1; i >= 0; i--) {
			long num = 0, count = 0;
			for (int j = i; j < n; j++) {
				num = num * 10 + (s.charAt(j) - '0');
				if (num == 0 || num > k) {
					break;
				}
				count += dp[j + 1];
			}
			dp[i] = (int) (count % MOD);
		}
		return dp[0];
	}

	public static void main(String[] args) {
		RestoreArrayBottomUp cwt = new RestoreArrayBottomUp();
		System.out.println(cwt.numberOfArrays("1000", 10000)); // 1
		System.out.println(cwt.numberOfArrays("1000", 10)); // 0
		System.out.println(cwt.numberOfArrays("1317", 2000)); // 8
	}
}
