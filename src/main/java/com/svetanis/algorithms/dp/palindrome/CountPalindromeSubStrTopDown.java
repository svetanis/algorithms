package com.svetanis.algorithms.dp.palindrome;

// 647. Palindromic Substrings

public final class CountPalindromeSubStrTopDown {
	// Time Complexity: O(n^2)
	// Auxiliary Space O(1)

	public static int lps(String s) {
		int n = s.length();
		if (n == 0) {
			return 0;
		}
		int count = 0;
		Boolean[][] dp = new Boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (isPalindrome(s, i, j, dp)) {
					count += 1;
				}
			}
		}
		return count;
	}

	private static boolean isPalindrome(String s, int i, int j, 
			Boolean[][] dp) {
		if (i >= j) {
			return true;
		}
		if (dp[i][j] != null) {
			return dp[i][j];
		}
		if (s.charAt(i) != s.charAt(j)) {
			dp[i][j] = false;
		} else {
			dp[i][j] = isPalindrome(s, i + 1, j - 1, dp);
		}
		return dp[i][j];
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 7
		System.out.println(lps("cddpd")); // 7
		System.out.println(lps("pqr")); // 3
		System.out.println(lps("abc")); // 3
		System.out.println(lps("aaa")); // 6
	}
}