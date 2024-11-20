package com.svetanis.algorithms.dp.wordbreak;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 139. Word Break

public final class IsWordBreakSubmit {
	// Time Complexity: O(n^2 * k)
	// Space Complexity: O(n + m)

	public static boolean isWordBreak(String s, List<String> dict) {
		int n = s.length();
		if (n == 0) {
			return true;
		}
		Set<String> set = new HashSet<>(dict);
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;
		for (int i = 1; i <= n; i++) {
			// try different break points
			for (int j = 0; j < i; j++) {
				String substr = s.substring(j, i);
				if (dp[j] && set.contains(substr)) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		System.out.println(isWordBreak("leetcode", asList("leet", "code"))); // true
		System.out.println(isWordBreak("applepenapple", asList("apple", "pen"))); // true
		System.out.println(isWordBreak("catsandog", asList("cats", "dog", "sand", "and", "cat"))); // false
	}
}
