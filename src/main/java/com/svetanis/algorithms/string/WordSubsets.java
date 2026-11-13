package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.List;

// 916. Word Subsets
//
// Input: two arrays of words. A word b is a subset of a word a if every letter of b appears in a
// at least as many times as in b. A word a is universal if every word of words2 is a subset of a.
// Returns all universal words of words1, in order. On LeetCode: 1 <= words1.length,
// words2.length <= 10^4, 1 <= word length <= 10, lowercase letters, words1 has no repeats.
//
// The one idea: to be a superset of every word of words2, a word must hold each letter at least
// as often as the most demanding word of words2 asks for it. So collapse words2 into one demand:
// maxCount[c] is the largest number of times any single word of words2 uses letter c. One cell of
// a count array is one letter's count. A word of words1 is universal when its own count of every
// letter is at least that demand. The demand is a maximum per letter, not a sum: two words that
// each need one 'c' can share the same 'c'.
//
// Time: O(A + B) -- A and B are the total letters of words1 and words2; each is counted once, and
// every word of words1 is compared in 26 steps.
// Space: O(1) -- two arrays of 26 (plus the answer list).

public final class WordSubsets {

	public static List<String> wordSubsets(String[] words1, String[] words2) {
		int[] maxCount = maxCount(words2); // DEMAND: the most each letter is needed
		List<String> list = new ArrayList<>();
		for (String w : words1) {
			int[] count = count(w);
			if (subset(maxCount, count)) { // TAKE: this word meets the demand
				list.add(w);
			}
		}
		return list;
	}

	private static boolean subset(int[] maxCount, int[] count) {
		for (int i = 0; i < 26; i++) {
			if (count[i] < maxCount[i]) { // too few of this letter
				return false;
			}
		}
		return true;
	}

	private static int[] count(String w) {
		int[] count = new int[26];
		for (char c : w.toCharArray()) {
			count[c - 'a']++;
		}
		return count;
	}

	private static int[] maxCount(String[] w) {
		int[] max = new int[26];
		for (String s : w) {
			int[] count = new int[26]; // this word's own counts
			for (char c : s.toCharArray()) {
				count[c - 'a']++;
				max[c - 'a'] = Math.max(max[c - 'a'], count[c - 'a']); // RECORD: the largest so far
			}
		}
		return max;
	}

	public static void main(String[] args) {
		String[] w1 = { "amazon", "apple", "facebook", "google", "leetcode" };
		String[] w2 = { "e", "o" };
		System.out.println(wordSubsets(w1, w2)); // [facebook, google, leetcode]

		String[] w3 = { "amazon", "apple", "facebook", "google", "leetcode" };
		String[] w4 = { "lc", "eo" };
		System.out.println(wordSubsets(w3, w4)); // [leetcode]

		String[] w5 = { "acaac", "cccbb", "aacbb", "caacc", "bcbbb" };
		String[] w6 = { "c", "cc", "b" };
		System.out.println(wordSubsets(w5, w6)); // [cccbb]

		String[] w7 = { "amazon", "apple", "facebook", "google", "leetcode" };
		String[] w8 = { "lo", "eo" };
		System.out.println(wordSubsets(w7, w8)); // [google, leetcode]

	}
}
