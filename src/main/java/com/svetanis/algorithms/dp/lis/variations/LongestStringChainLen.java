package com.svetanis.algorithms.dp.lis.variations;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;

// 1048. Longest String Chain

public final class LongestStringChainLen {

	public static int lcl(String[] words) {
		Arrays.sort(words, comparingInt(a -> a.length()));
		int n = words.length;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		// no words means no chain, so the seed is only a length
		// when there is at least one word to be it
		int max = n == 0 ? 0 : 1;
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

		// a seed of 1 answers 1 here, because neither loop runs
		System.out.println(lcl(new String[0])); // 0
	}
}
