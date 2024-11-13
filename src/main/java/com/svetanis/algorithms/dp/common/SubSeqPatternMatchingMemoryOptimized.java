package com.svetanis.algorithms.dp.common;

// 115. Distinct Subsequences

// Given a string and a pattern, 
// write a method to count the number of times 
// the pattern appears in the string as a subsequence.

public final class SubSeqPatternMatchingMemoryOptimized {
	// Time Complexity: O(n * m)

	public static int count(String str, String pat) {
		int m = pat.length();
		int[] dp = new int[m + 1];
		dp[0] = 1;
		for (char c : str.toCharArray()) {
			for (int j = m; j > 0; j--) {
				if (c == pat.charAt(j - 1)) {
					dp[j] += dp[j - 1];
				}
			}
		}
		return dp[m];
	}

	public static void main(String[] args) {
		System.out.println(count("baxmx", "ax"));
		System.out.println(count("tomorrow", "tor"));
		System.out.println(count("rabbbit", "rabbit")); // 3
		System.out.println(count("babgbag", "bag")); // 5
	}
}