package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 893. Groups of Special-Equivalent Strings
//
// Input: an array of words, all of the same length. One move swaps two letters that sit at
// positions of the same parity (both even indexes or both odd indexes). Two words are
// special-equivalent if some moves turn one into the other. Returns the number of groups of
// mutually special-equivalent words. On LeetCode: 1 <= words.length <= 1000,
// 1 <= words[i].length <= 20, lowercase letters, all words the same length.
//
// The one idea: a move never carries a letter from an even index to an odd index, so a word is
// described exactly by its even-index letters as a multiset plus its odd-index letters as a
// multiset; and any rearrangement inside each parity is reachable. Sort the even letters, sort the
// odd letters, and glue them into one key. Two words are equivalent exactly when their keys are
// equal, so the answer is the number of distinct keys.
//
// Time: O(n * m log m) -- n words of length m, each split and sorted.
// Space: O(n * m) -- the set of keys.

public final class SpecialEquivalentGroups {

	public static int seg(String[] words) {
		Set<String> set = new HashSet<>();
		for (String word : words) {
			set.add(convert(word)); // RECORD: the key of this word; equal keys collapse
		}
		return set.size(); // RETURN: one entry per group
	}

	private static String convert(String s) {
		List<Character> even = new ArrayList<>();
		List<Character> odd = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) { // SPLIT by index parity
			char c = s.charAt(i);
			if (i % 2 == 0) {
				even.add(c);
			} else {
				odd.add(c);
			}
		}
		Collections.sort(even); // SORT: the order inside a parity does not matter
		Collections.sort(odd);

		StringBuilder sb = new StringBuilder();
		for (char c : even) {
			sb.append(c);
		}
		for (char c : odd) {
			sb.append(c);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String[] a1 = { "abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx" };
		System.out.println(seg(a1)); // 3
		String[] a2 = { "abc", "acb", "bac", "bca", "cab", "cba" };
		System.out.println(seg(a2)); // 3
	}
}
