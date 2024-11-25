package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.min;

// 1312. Minimum Insertion Steps to Make a String Palindrome

public final class MinInsertionsToMakePalindromeBottomUp {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	public static int palindrome(String s) {
		int n = s.length();
		int[][] dp = new int[n][n];
		for (int row = n - 2; row >= 0; row--) {
			for (int col = row + 1; col < n; col++) {
				if (s.charAt(row) == s.charAt(col)) {
					dp[row][col] = dp[row + 1][col - 1];
				} else {
					int top = dp[row][col - 1];
					int left = dp[row + 1][col];
					dp[row][col] = 1 + min(top, left);
				}
			}
		}
		return dp[0][n - 1];
	}

	public static int palindrome2(String str) {
		int n = str.length();
		int[][] dp = new int[n][n];
		for (int gap = 1; gap < n; ++gap) {
			for (int low = 0, high = gap; high < n; ++low, ++high) {
				if (str.charAt(low) == str.charAt(high)) {
					dp[low][high] = dp[low + 1][high - 1];
				} else {
					int top = dp[low][high - 1];
					int left = dp[low + 1][high];
					dp[low][high] = 1 + min(top, left);
				}
			}
		}
		return dp[0][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(palindrome("zzazz")); // 0
		System.out.println(palindrome("mbadm")); // 2
		System.out.println(palindrome("leetcode")); // 5
		System.out.println(palindrome("geeks")); // 3
	}
}