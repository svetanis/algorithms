package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 424. Longest Repeating Character Replacement

// Given a string s of UPPERCASE English letters and an integer k,
// you may change any character to any other uppercase English
// letter, at most k times. Return the length of the longest
// substring containing a single repeated letter.

// The one idea: in a window, keep the most frequent letter and replace
// every other one, so the window costs (size - most frequent count)
// replacements. Adding a letter can push that over k; losing letters
// from the left brings it back. count is the largest count ever seen
// and is never lowered, so after a shrink the window can be one that
// is not really affordable -- but only at a length already recorded.
// A longer window needs some letter's real count to beat count, and
// that raises count to the true value.

// Same algorithm as MaxUnivalSubStrLenAfterKReplacements,
// which states the problem over a lowercase alphabet.
// The two methods here differ only in the counts: a map, or an
// int[26] indexed c - 'A'.

// Time: O(n) -- each letter enters the window once and leaves at most
//   once.
// Space: O(1) -- at most 26 counts, in the map or in the array.

public final class LongestRepeatingCharReplacement424 {

	// the window is valid while (size - most frequent letter) <= k,
	// because every other letter in it has to be replaced

	public static int characterReplacement(String s, int k) {
		int max = 0;
		int left = 0;
		int count = 0; // max repeated letter count
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			map.put(c, map.getOrDefault(c, 0) + 1); // ADD
			count = Math.max(count, map.get(c));
			while (right - left + 1 - count > k) { // SHRINK: while more than k letters need replacing
				char front = s.charAt(left);
				map.put(front, map.get(front) - 1);
				left++;
			}
			max = Math.max(max, right - left + 1); // MEASURE
		}
		return max;
	}

	// the count array replaces the map: 26 uppercase letters, so
	// index by c - 'A'. Lowercase input would index out of bounds.

	public static int characterReplacementCounts(String s, int k) {
		int max = 0;
		int left = 0;
		int count = 0;
		int[] a = new int[26];
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			a[c - 'A']++; // ADD
			count = Math.max(count, a[c - 'A']);
			// this 'while' runs at most once: adding one letter puts the
			// window at most one over k, and since count is never lowered,
			// one letter leaving brings it back.
			while (right - left + 1 - count > k) { // SHRINK: while more than k letters need replacing
				char front = s.charAt(left);
				a[front - 'A']--;
				left++;
			}
			max = Math.max(max, right - left + 1); // MEASURE
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(characterReplacement("ABAB", 2)); // 4
		System.out.println(characterReplacement("AABABBA", 1)); // 4
		System.out.println(characterReplacement("ABCCDE", 1)); // 3

		System.out.println(characterReplacementCounts("ABAB", 2)); // 4
		System.out.println(characterReplacementCounts("AABABBA", 1)); // 4
		System.out.println(characterReplacementCounts("ABCCDE", 1)); // 3
	}
}
