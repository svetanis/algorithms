package com.svetanis.algorithms.string;

// 2185. Counting Words With a Given Prefix

public final class CountWordsWithPrefix {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int prefixCount(String[] words, String prefix) {
		int count = 0;
		for (String word : words) {
			if (word.startsWith(prefix)) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		String[] words1 = { "pay", "attention", "practice", "attend" };
		System.out.println(prefixCount(words1, "at")); // 2
		String[] words2 = { "leetcode", "win", "loops", "success" };
		System.out.println(prefixCount(words2, "code")); // 0
	}
}
