package com.svetanis.algorithms.prefixsum;

import java.util.Set;

// Count the vowels in a range of a string -- no LeetCode number; source unknown
//
// Input: a string s and an array of queries; query i is [start, end]. Returns an array whose entry
// i is the number of vowels (a, e, i, o, u) in s[start .. end], both ends included. No constraints
// are given; the method assumes lowercase letters (an uppercase vowel is not counted) and
// 0 <= start <= end < s.length().
//
// The one idea: one letter counts 1 or 0, so the question is a sum over a range. prefix[i] holds
// how many of the first i letters, s[0 .. i - 1], are vowels, with prefix[0] = 0. The letters
// start .. end are the first end + 1 letters minus the first start, so each query is
// prefix[end + 1] - prefix[start]: one subtraction, however wide the range.
//
// Time: O(n + q) -- one pass over the n letters, one subtraction per query
// Space: O(n + q) -- the n + 1 prefix cells and the q answers
//
// Siblings: none. CountVowelStringsInRanges (2559) asks the same range question about whole words,
// each counting 1 when it starts and ends with a vowel.

public final class CountVowelsInSubStr {

	private static final Set<Character> VOWELS = Set.of('a', 'e', 'o', 'u', 'i');

	public static int[] vowels(String s, int[][] queries) {
		int[] prefix = prefix(s);
		int[] a = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int start = queries[i][0];
			int end = queries[i][1];
			a[i] = prefix[end + 1] - prefix[start]; // QUERY: s[0 .. end] minus s[0 .. start - 1]
		}
		return a;
	}

	private static int[] prefix(String s) {
		int n = s.length();
		int[] prefix = new int[n + 1]; // CELLS: prefix[i] = vowels among s[0 .. i - 1]
		for (int i = 0; i < n; i++) {
			int count = VOWELS.contains(s.charAt(i)) ? 1 : 0;
			prefix[i + 1] = prefix[i] + count; // PREFIX: one letter adds 1 or 0
		}
		return prefix;
	}

	private static void print(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int value : a) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		int[][] q1 = { { 0, 2 }, { 1, 4 }, { 3, 5 } };
		print(vowels("prefixsum", q1)); // 1 2 1

		int[][] q2 = { { 0, 1 }, { 0, 2 } };
		print(vowels("aba", q2)); // 1 2

		int[][] q3 = { { 0, 1 }, { 1, 2 }, { 0, 2 } };
		print(vowels("abc", q3)); // 1 0 1

		int[][] q4 = { { 0, 2 }, { 1, 3 }, { 0, 4 } };
		print(vowels("aeiou", q4)); // 3 3 5

		int[][] q5 = { { 0, 2 }, { 1, 4 }, { 3, 5 } };
		print(vowels("prefix", q5)); // 1 2 1
	}
}
