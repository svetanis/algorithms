package com.svetanis.algorithms.slidingwindow.string.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 438. Find All Anagrams in a String
//
// A string s and a pattern p, both lowercase letters 'a'..'z' (a count's index is c - 'a').
// Returns, in increasing order, the start index of every substring of s that is a
// rearrangement of p: the same letters, each as many times.
//
// Such a substring has exactly m = p.length() letters, so the window has a fixed size, and two
// strings are rearrangements of each other exactly when their 26 letter counts are equal. Keep
// the window's 26 counts and compare them with the pattern's at every step. The window is built
// one letter short, so the first step of the loop completes it and no window is special.
//
// Siblings:
//   StringAnagrams  (same problem) one map of what the window still needs and a matched
//                   counter, so no 26-count comparison; any characters, not only 'a'..'z'
//   ContainsAnagram (LC 567) the matched-counter window, stops at the first match
//   CountAnagrams   the matched-counter window, counts the matches
// LC 567 with these two tables: slidingwindow/string/PermutationInString.

public final class StringAnagramsSubmit {
	// Time complexity: O(26 n) = O(n), each step compares two tables of 26 counts
	// Space complexity: O(1), two tables of 26 counts, plus the returned list

	public static List<Integer> anagrams(String s, String p) {
		int n = s.length();
		int m = p.length();
		if (n < m) {
			return new ArrayList<>();
		}
		int[] pcount = frequencies(p, m);
		int[] wcount = frequencies(s, m - 1); // BUILD: the first window, one letter short
		List<Integer> list = new ArrayList<>();
		for (int i = m - 1; i < n; i++) {
			wcount[s.charAt(i) - 'a']++; // IN: completes the window s[i - m + 1 .. i]
			if (Arrays.equals(pcount, wcount)) {
				list.add(i - m + 1); // RECORD: the window's start
			}
			wcount[s.charAt(i - m + 1) - 'a']--; // OUT: the oldest letter leaves
		}
		return list;
	}

	private static int[] frequencies(String s, int size) {
		int[] a = new int[26];
		for (int i = 0; i < size; i++) {
			a[s.charAt(i) - 'a']++;
		}
		return a;
	}

	private static void print(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (int value : list) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		String[][] pairs = {
				{ "ppqp", "pq" }, // 1, 2
				{ "abbcabc", "abc" }, // 2, 3, 4
				{ "cbaebabacd", "abc" }, // 0, 6
				{ "abab", "ab" } // 0, 1, 2
		};
		for (String[] pair : pairs) {
			print(anagrams(pair[0], pair[1]));
		}
	}

}
