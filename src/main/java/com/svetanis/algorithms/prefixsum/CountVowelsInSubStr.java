package com.svetanis.algorithms.prefixsum;

import java.util.Set;

import com.svetanis.java.base.utils.Print;

public final class CountVowelsInSubStr {
	// Time Complexity: O(n + q)
	// Space Complexity: O(n + q)

	private static final Set<Character> VOWELS = Set.of('a', 'e', 'o', 'u', 'i');

	public static int[] vowels(String s, int[][] queries) {
		int[] prefix = prefix(s);
		int[] a = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			int start = queries[i][0];
			int end = queries[i][1];
			a[i] = prefix[end + 1] - prefix[start];
		}
		return a;
	}

	private static int[] prefix(String s) {
		int n = s.length();
		int[] prefix = new int[n + 1];
		for (int i = 0; i < n; i++) {
			int count = VOWELS.contains(s.charAt(i)) ? 1 : 0;
			prefix[i + 1] = prefix[i] + count;
		}
		return prefix;
	}

	public static void main(String[] args) {
		int[][] q1 = { { 0, 2 }, { 1, 4 }, { 3, 5 } };
		Print.print(vowels("prefixsum", q1)); // 1,2,1

		int[][] q2 = { { 0, 1 }, { 0, 2 } };
		Print.print(vowels("aba", q2)); // 1,2

		int[][] q3 = { { 0, 1 }, { 1, 2 }, { 0, 2 } };
		Print.print(vowels("abc", q3)); // 1,0,1

		int[][] q4 = { { 0, 2 }, { 1, 3 }, { 0, 4 } };
		Print.print(vowels("aeiou", q4)); // 3,3,5

		int[][] q5 = { { 0, 2 }, { 1, 4 }, { 3, 5 } };
		Print.print(vowels("prefix", q5)); // 1,2,1
	}
}
