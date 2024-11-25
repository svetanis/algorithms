package com.svetanis.algorithms.dp.interleaved;

// 97. Interleaving String

// Given two strings str1 and str2, 
// write a function that prints all 
// interleavings of the given two strings.

public final class InterleavedSubmit {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	public static boolean isInterleaved(String s1, String s2, String s) {
		int n = s1.length();
		int m = s2.length();

		Boolean[][] dp = new Boolean[n + 1][m + 1];
		return isInterleaved(dp, s1, s2, s, 0, 0, 0);
	}

	private static boolean isInterleaved(Boolean[][] dp, String x, String y, String s, int i, int j, int k) {
		int n = x.length();
		int m = y.length();
		int p = s.length();

		if (i == n && j == m && k == p) {
			return true;
		}
		if (k == p) {
			return false;
		}
		if (dp[i][j] != null) {
			return dp[i][j];
		}
		boolean interleaved = false;
		if (i < n && x.charAt(i) == s.charAt(k)) {
			interleaved = interleaved || isInterleaved(dp, x, y, s, i + 1, j, k + 1);
		}
		if (j < m && y.charAt(j) == s.charAt(k)) {
			interleaved = interleaved || isInterleaved(dp, x, y, s, i, j + 1, k + 1);
		}
		return dp[i][j] = interleaved;
	}

	public static void main(String[] args) {
		System.out.println(isInterleaved("XXY", "XXZ", "XXZXXXY")); // false
		System.out.println(isInterleaved("XY", "WZ", "WZXY")); // true
		System.out.println(isInterleaved("XY", "X", "XXY")); // true
		System.out.println(isInterleaved("YX", "X", "XXY")); // false
		System.out.println(isInterleaved("XXY", "XXZ", "XXXXZY")); // true
	}
}
