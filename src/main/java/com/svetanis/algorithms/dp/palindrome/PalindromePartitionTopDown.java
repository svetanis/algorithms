package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.min;

// given a string, 
// cut it into pieces 
// such that each piece
// is a palindrome. 
// find minimum number of cuts.

public final class PalindromePartitionTopDown {
	// Time Complexity: O(n^2)

	public static int palindromePartition(String s) {
		if (isPalindrome(s)) {
			return 0;
		}
		int n = s.length();
		Integer[][] dp = new Integer[n + 1][n + 1];
		return dfs(s, 0, n - 1, dp);
	}

	private static int dfs(String s, int left, int right, Integer[][] dp) {
		if (left == right || isPalindrome(s)) {
			dp[left][right] = 0;
			return 0;
		}
		if (dp[left][right] != null) {
			return dp[left][right];
		}
		int min = right - left;
		for (int index = left; index <= right; index++) {
			String prefix = s.substring(left, index + 1);
			// if a palindrome from left to index
			if (isPalindrome(prefix)) {
				// then cut here
				int cuts = 1 + dfs(s, index + 1, right, dp);
				min = min(min, cuts);
			}
		}
		dp[left][right] = min;
		return dp[left][right];
	}

	private static boolean isPalindrome(String s) {
		int left = 0;
		int right = s.length() - 1;
		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(palindromePartition("abdbca")); // 3
		System.out.println(palindromePartition("cddpd")); // 2
		System.out.println(palindromePartition("pqr")); // 2
		System.out.println(palindromePartition("pp")); // 0
	}
}
