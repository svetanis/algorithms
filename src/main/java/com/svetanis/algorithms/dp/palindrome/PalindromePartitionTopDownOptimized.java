package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.min;

// 132. Palindrome Partitioning II

// given a string, 
// cut it into pieces 
// such that each piece
// is a palindrome. 
// find minimum number of cuts.

public final class PalindromePartitionTopDownOptimized {
	// Time Complexity: O(n^2)

	public static int palindromePartition(String s) {
		int n = s.length();
		Boolean[][] pp = new Boolean[n + 1][n + 1];
		Integer[][] dp = new Integer[n + 1][n + 1];
		return dfs(s, 0, n - 1, dp, pp);
	}

	private static int dfs(String s, int left, int right, Integer[][] dp, Boolean[][] pp) {
		if (left == right || isPalindrome(s, left, right, pp)) {
			dp[left][right] = 0;
			return 0;
		}
		if (dp[left][right] != null) {
			return dp[left][right];
		}
		int min = right - left;
		for (int index = left; index <= right; index++) {
			// if a palindrome from left to index
			if (isPalindrome(s, left, index, pp)) {
				// then cut here
				int cuts = 1 + dfs(s, index + 1, right, dp, pp);
				min = min(min, cuts);
			}
		}
		dp[left][right] = min;
		return dp[left][right];
	}

	// 68ms
	private static boolean isPalindrome(String s, int start, int end, Boolean[][] dp) {
		if (dp[start][end] == null) {
			dp[start][end] = true;
			int left = start;
			int right = end;
			while (left < right) {
				if (s.charAt(left) != s.charAt(right)) {
					dp[start][end] = false;
					break;
				}
				left++;
				right--;
				if (left < right && dp[left][right] != null) {
					dp[start][end] = dp[left][right];
					break;
				}
			}
		}
		return dp[start][end];
	}

	// 2167ms
	private static boolean isPalindrome2(String s, int start, int end, Boolean[][] dp) {
		if (dp[start][end] != null) {
			return dp[start][end];
		}
		int left = start;
		int right = end;
		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				dp[start][end] = false;
				return false;
			}
			left++;
			right--;
		}
		dp[start][end] = true;
		return true;
	}

	public static void main(String[] args) {
		System.out.println(palindromePartition("abdbca")); // 3
		System.out.println(palindromePartition("cddpd")); // 2
		System.out.println(palindromePartition("pqr")); // 2
		System.out.println(palindromePartition("pp")); // 0
	}
}
