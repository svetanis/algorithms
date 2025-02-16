package com.svetanis.algorithms.dp.palindrome;

import java.util.Arrays;

// 132. Palindrome Partitioning II

// given a string, 
// cut it into pieces 
// such that each piece
// is a palindrome. 
// find minimum number of cuts.

public final class PalindromePartitionMinCutsBottomUp {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	public static int palindromePartition(String s) {
		int n = s.length();
		int[] minCuts = cutsInit(n);
		boolean[][] dp = isPalindrome(s);
		for (int right = 1; right < n; right++) {
			for (int left = 0; left <= right; left++) {
				if (dp[left][right]) {
					int cuts = left == 0 ? 0 : 1 + minCuts[left - 1];
					minCuts[right] = Math.min(minCuts[right], cuts);
				}
			}
		}
		return minCuts[n - 1];
	}

	private static int[] cutsInit(int n) {
		int[] cuts = new int[n];
		for (int i = 0; i < n; i++) {
			cuts[i] = i;
		}
		return cuts;
	}

	private static boolean[][] isPalindrome(String s) {
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		for (boolean[] row : dp) {
			Arrays.fill(row, true);
		}
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				boolean equals = s.charAt(i) == s.charAt(j);
				dp[i][j] = dp[i + 1][j - 1] && equals;
			}
		}
		return dp;
	}

	public static void main(String[] args) {
		System.out.println(palindromePartition("abdbca")); // 3
		System.out.println(palindromePartition("cddpd")); // 2
		System.out.println(palindromePartition("pqr")); // 2
		System.out.println(palindromePartition("pp")); // 0
	}
}
