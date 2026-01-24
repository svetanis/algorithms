package com.svetanis.algorithms.dp.lis.variations;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 1048. Longest String Chain

public final class LongestStringChainLenTopDown {
	// Time Complexity: O(n * maxLen^2)
	// Space Complexity: O(n)

	public static int lcl(String[] words) {
		Set<String> set = new HashSet<>();
		Collections.addAll(set, words);
		Map<String, Integer> dp = new HashMap<>();
		int max = 0;
		for (String word : words) {
			max = Math.max(max, dfs(word, set, dp));
		}
		return max;
	}

	private static int dfs(String word, Set<String> words, Map<String, Integer> dp) {
		if (dp.containsKey(word)) {
			return dp.get(word);
		}
		int maxLen = 1;
		StringBuilder sb = new StringBuilder(word);
		for (int i = 0; i < word.length(); i++) {
			sb.deleteCharAt(i);
			String s = sb.toString();
			if (words.contains(s)) {
				int len = 1 + dfs(s, words, dp);
				maxLen = Math.max(maxLen, len);
			}
			sb.insert(i, word.charAt(i));
		}
		dp.put(word, maxLen);
		return maxLen;
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
