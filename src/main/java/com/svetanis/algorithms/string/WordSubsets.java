package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.List;

// 916. Word Subsets

public final class WordSubsets {

	public static List<String> wordSubsets(String[] words1, String[] words2) {
		int[] maxCount = maxCount(words2);
		List<String> list = new ArrayList<>();
		for (String w : words1) {
			int[] count = count(w);
			if (subset(maxCount, count)) {
				list.add(w);
			}
		}
		return list;
	}

	private static boolean subset(int[] maxCount, int[] count) {
		for (int i = 0; i < 26; i++) {
			if (count[i] < maxCount[i]) {
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
			int[] count = new int[26];
			for (char c : s.toCharArray()) {
				count[c - 'a']++;
				max[c - 'a'] = Math.max(max[c - 'a'], count[c - 'a']);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		String[] w1 = { "amazon", "apple", "facebook", "google", "leetcode" };
		String[] w2 = { "e", "o" };
		System.out.println(wordSubsets(w1, w2)); // facebook, google, leetcode

		String[] w3 = { "amazon", "apple", "facebook", "google", "leetcode" };
		String[] w4 = { "lc", "eo" };
		System.out.println(wordSubsets(w3, w4)); // leetcode

		String[] w5 = { "acaac", "cccbb", "aacbb", "caacc", "bcbbb" };
		String[] w6 = { "c", "cc", "b" };
		System.out.println(wordSubsets(w5, w6)); // cccbb

		String[] w7 = { "amazon", "apple", "facebook", "google", "leetcode" };
		String[] w8 = { "lo", "eo" };
		System.out.println(wordSubsets(w7, w8)); // google, leetcode

	}
}
