package com.svetanis.algorithms.dp.lis;

import java.util.Arrays;

// 673. Number of Longest Increasing Subsequence

public final class CountLisBottomUp {
	// Time Complexity: O(n^2)

	public static int count(int[] a) {
		int n = a.length;
		int[] dp = new int[n];
		int[] count = new int[n];
		Arrays.fill(dp, 1);
		Arrays.fill(count, 1);
		int maxLen = 0;
		int maxCount = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j]) {
					// extend existing subseq
					if (dp[j] + 1 > dp[i]) {
						dp[i] = dp[j] + 1;
						count[i] = count[j];
						// another subseq of the 
						// same max length ending at i found
					} else if (dp[i] == dp[j] + 1) {
						count[i] += count[j];
					}
				}
			}
			if (dp[i] > maxLen) {
				maxLen = dp[i];
				maxCount = count[i];
			} else if (dp[i] == maxLen) {
				maxCount += count[i];
			}
		}
		return maxCount;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 5, 4, 7 };
		System.out.println(count(a1)); // 2
		int[] a2 = { 2, 2, 2, 2, 2 };
		System.out.println(count(a2)); // 5
	}
}