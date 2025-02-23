package com.svetanis.algorithms.dp.lis.variations;

import java.util.Arrays;

// 1048. Longest String Chain

public final class LongestStringChainLen {

	public static int lcl(String[] words) {
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		int n = words.length;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		int max = 1;
		for (int curr = 0; curr < n; curr++) {
			for (int prev = 0; prev < curr; prev++) {
				if (predecessor(words[prev], words[curr])) {
					dp[curr] = Math.max(dp[curr], dp[prev] + 1);
					max = Math.max(max, dp[curr]);
				}
			}
		}
		return max;
	}

	private static boolean predecessor(String prev, String curr) {
		if (prev.length() + 1 != curr.length()) {
			return false;
		}
		for (int k = 0; k < curr.length(); k++) {
			String ss = curr.substring(0, k) + curr.substring(k + 1);
			if (prev.equals(ss)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String[] a1 = { "a", "b", "ba", "bca", "bda", "bdca" };
		System.out.println(lcl(a1)); // 4

		String[] a2 = { "xbc", "pcxbcf", "xb", "cxbc", "pcxbc" };
		System.out.println(lcl(a2)); // 5

		String[] a3 = { "abcd", "dbqca" };
		System.out.println(lcl(a3)); // 1
	}
}
