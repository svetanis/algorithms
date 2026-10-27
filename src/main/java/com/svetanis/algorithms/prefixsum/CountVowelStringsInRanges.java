package com.svetanis.algorithms.prefixsum;

import java.util.Set;

// 2559. Count Vowel Strings in Ranges
//
// Input: an array of words and an array of queries; query i is [l, r]. A word is a vowel string
// when it starts and ends with a vowel (a, e, i, o, u). Returns an array whose entry i is the
// number of vowel strings among words[l .. r], both ends included. On LeetCode:
// 1 <= words.length <= 10^5, 1 <= words[i].length <= 40, lowercase letters only, the lengths add up
// to at most 3 * 10^5, 1 <= queries.length <= 10^5, 0 <= l <= r < words.length. An empty word is
// outside that range and throws StringIndexOutOfBoundsException at charAt(0).
//
// The one idea: one word counts 1 or 0, so the question is a sum over a range. prefix[i] holds how
// many of the first i words, words[0 .. i - 1], are vowel strings, with prefix[0] = 0. The words
// l .. r are the first r + 1 words minus the first l, so each query is prefix[r + 1] - prefix[l]:
// one subtraction, however wide the range.
//
// Time: O(n + q) -- one pass over the n words (two characters read per word), one subtraction per
//   query
// Space: O(n + q) -- the n + 1 prefix cells and the q answers
//
// Siblings: none for 2559. CountVowelsInSubStr asks the same range question about the letters of
// one string instead of whole words.

public final class CountVowelStringsInRanges {

	private static final Set<Character> VOWELS = Set.of('a', 'e', 'o', 'u', 'i');

	public static int[] vowels(String[] words, int[][] queries) {
		int[] prefix = prefix(words);
		int[] a = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int start = queries[i][0];
			int end = queries[i][1];
			a[i] = prefix[end + 1] - prefix[start]; // QUERY: [0 .. end] minus [0 .. start - 1]
		}
		return a;
	}

	private static int[] prefix(String[] words) {
		int n = words.length;
		int[] prefix = new int[n + 1]; // CELLS: prefix[i] = vowel strings among words[0 .. i - 1]
		for (int i = 0; i < n; i++) {
			prefix[i + 1] = prefix[i] + vowelStr(words[i]); // PREFIX: one word adds 1 or 0
		}
		return prefix;
	}

	private static int vowelStr(String s) {
		boolean startsWith = VOWELS.contains(s.charAt(0)); // FIRST letter
		boolean endsWith = VOWELS.contains(s.charAt(s.length() - 1)); // LAST letter
		return startsWith && endsWith ? 1 : 0;
	}

	private static void print(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int value : a) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		String[] w1 = { "aba", "bcb", "ece", "aa", "e" };
		int[][] q1 = { { 0, 2 }, { 1, 4 }, { 1, 1 } };
		print(vowels(w1, q1)); // 2 3 0

		String[] w2 = { "a", "e", "i" };
		int[][] q2 = { { 0, 2 }, { 0, 1 }, { 2, 2 } };
		print(vowels(w2, q2)); // 3 2 1
	}
}
