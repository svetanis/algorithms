package com.svetanis.algorithms.string;

// 2185. Counting Words With a Given Prefix
//
// Input: an array of words and a string prefix. Returns the number of words that start with
// prefix. On LeetCode: 1 <= words.length <= 100, 1 <= words[i].length, prefix.length <= 100,
// lowercase letters.
//
// The one idea: ask each word whether it starts with the prefix, and count the yes answers.
//
// Time: O(n * p) -- n words, and each startsWith compares at most p = prefix.length characters
// Space: O(1)

public final class CountWordsWithPrefix {

	public static int prefixCount(String[] words, String prefix) {
		int count = 0;
		for (String word : words) { // WALK: every word
			if (word.startsWith(prefix)) { // TEST: its first characters are the prefix
				count++; // RECORD
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
