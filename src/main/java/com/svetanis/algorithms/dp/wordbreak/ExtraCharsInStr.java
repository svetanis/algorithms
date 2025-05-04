package com.svetanis.algorithms.dp.wordbreak;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

// 2707. Extra Characters in a String

public final class ExtraCharsInStr {
	// Time Complexity: O(n^3)
	
	public static int minExtraChars(String s, String[] dictionary) {
		int n = s.length();
		int[] dp = new int[n + 1];
		dp[0] = 0;
		Set<String> set = Arrays.stream(dictionary).collect(Collectors.toSet());
		for (int i = 1; i <= n; i++) {
			dp[i] = dp[i - 1] + 1;
			for (int j = 0; j < i; j++) {
				String ss = s.substring(j, i);
				if (set.contains(ss)) {
					dp[i] = Math.min(dp[i], dp[j]);
				}
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		String[] d1 = { "leet", "code", "leetcode" };
		System.out.println(minExtraChars("leetscode", d1)); // 1
		String[] d2 = { "hello", "world" };
		System.out.println(minExtraChars("sayhelloworld", d2)); // 3
	}
}
