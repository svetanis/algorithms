package com.svetanis.algorithms.dp.interleaved;

// 97. Interleaving String

// Given two strings str1 and str2, 
// write a function that prints all 
// interleavings of the given two strings.

public final class InterleavedBottomUpSpaceOptimized {

	public static boolean isInterleaved(String s1, String s2, String s) {
		int n = s1.length();
		int m = s2.length();
		int k = s.length();
		if (k != n + m) {
			return false;
		}

		boolean[] dp = new boolean[m + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				int len = i + j - 1;
				if (i == 0 && j == 0) {
					dp[j] = true;
				} else if (i == 0) {
					dp[j] = dp[j - 1] && s2.charAt(j - 1) == s.charAt(len);
				} else if (j == 0) {
					dp[j] = dp[j] && s1.charAt(i - 1) == s.charAt(len);
				} else {
					boolean one = dp[j] && s1.charAt(i - 1) == s.charAt(len);
					boolean two = dp[j - 1] && s2.charAt(j - 1) == s.charAt(len);
					dp[j] = one || two;
				}
			}
		}
		return dp[m];
	}

	public static void main(String[] args) {
		System.out.println(isInterleaved("XXY", "XXZ", "XXZXXXY")); // false
		System.out.println(isInterleaved("XY", "WZ", "WZXY")); // true
		System.out.println(isInterleaved("XY", "X", "XXY")); // true
		System.out.println(isInterleaved("YX", "X", "XXY")); // false
		System.out.println(isInterleaved("XXY", "XXZ", "XXXXZY")); // true
	}
}
