package com.svetanis.algorithms.dp.lis.variations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 1048. Longest String Chain

public final class LongestStringChainLenBottomUp {
	// Time Complexity: O(n * (log n + maxLen^2))
	// Space Complexity: O(n)

	public static int lcl(String[] words) {
		Map<String, Integer> dp = new HashMap<>();
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		int maxLen = 1;
		for (String word : words) {
			int len = 1;
			for (int i = 0; i < word.length(); i++) {
				StringBuilder sb = new StringBuilder(word);
				sb.deleteCharAt(i);
				String predecessor = sb.toString();
				int prevLen = dp.getOrDefault(predecessor, 0);
				len = Math.max(len, prevLen + 1);
			}
			dp.put(word, len);
			maxLen = Math.max(maxLen, len);
		}
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
