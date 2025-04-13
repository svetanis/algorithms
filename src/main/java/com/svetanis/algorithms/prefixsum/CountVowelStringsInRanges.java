package com.svetanis.algorithms.prefixsum;

import java.util.Set;

import com.svetanis.java.base.utils.Print;

// 2559. Count Vowel Strings in Ranges

public final class CountVowelStringsInRanges {
	// Time Complexity: O(n + m)
	// Space Complexity: O(n + m)

	private static final Set<Character> VOWELS = Set.of('a', 'e', 'o', 'u', 'i');

	public static int[] vowels(String[] words, int[][] queries) {
		int[] prefix = prefix(words);
		int[] a = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int start = queries[i][0];
			int end = queries[i][1];
			a[i] = prefix[end + 1] - prefix[start];
		}
		return a;
	}

	private static int[] prefix(String[] words) {
		int n = words.length;
		int[] prefix = new int[n + 1];
		for (int i = 0; i < n; i++) {
			prefix[i + 1] = prefix[i] + vowelStr(words[i]);
		}
		return prefix;
	}

	private static int vowelStr(String s) {
		boolean startsWith = VOWELS.contains(s.charAt(0));
		boolean endsWith = VOWELS.contains(s.charAt(s.length() - 1));
		return startsWith && endsWith ? 1 : 0;
	}

	public static void main(String[] args) {
		String[] w1 = { "aba", "bcb", "ece", "aa", "e" };
		int[][] q1 = { { 0, 2 }, { 1, 4 }, { 1, 1 } };
		Print.print(vowels(w1, q1)); // 2,3,0

		String[] w2 = { "a", "e", "i" };
		int[][] q2 = { { 0, 2 }, { 0, 1 }, { 2, 2 } };
		Print.print(vowels(w2, q2)); // 3,2,1
	}
}
