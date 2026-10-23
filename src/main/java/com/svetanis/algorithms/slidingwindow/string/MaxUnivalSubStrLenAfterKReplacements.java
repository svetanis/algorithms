package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// Longest Substring with Same Letters after Replacement
// (the lowercase statement of LeetCode 424)

// Given a string with lowercase letters only,
// if you are allowed to replace no more than ‘k’ letters
// with any letter, find the length of the longest substring
// having the same letters after replacement.

// The one idea: in a window, keep the most frequent letter and replace
// every other one, so the window costs (size - most frequent count)
// replacements. Adding a letter can push that over k; losing letters
// from the left brings it back. count is the largest count ever seen
// and is never lowered, so after a shrink the window can be one that
// is not really affordable -- but only at a length already recorded.
// A longer window needs some letter's real count to beat count, and
// that raises count to the true value.

// Same algorithm as LongestRepeatingCharReplacement424 (LC 424),
// which states the problem over an uppercase alphabet.
// The two methods here differ only in the counts: a map, or an
// int[26] indexed c - 'a'.

// Time: O(n) -- each letter enters the window once and leaves at most
//   once.
// Space: O(1) -- at most 26 counts, in the map or in the array.

public final class MaxUnivalSubStrLenAfterKReplacements {

	// the window is valid while (size - most frequent letter) <= k,
	// because every other letter in it has to be replaced

	public static int subStrLen(String s, int k) {
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

	// the count array replaces the map: 26 lowercase letters, so
	// index by c - 'a'. Uppercase input would index out of bounds.

	public static int subStrLen2(String s, int k) {
		int max = 0;
		int left = 0;
		int count = 0;
		int[] a = new int[26];
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			a[c - 'a']++; // ADD
			count = Math.max(count, a[c - 'a']);
			// this 'while' runs at most once: adding one letter puts the
			// window at most one over k, and since count is never lowered,
			// one letter leaving brings it back.
			while (right - left + 1 - count > k) { // SHRINK: while more than k letters need replacing
				char front = s.charAt(left);
				a[front - 'a']--;
				left++;
			}
			max = Math.max(max, right - left + 1); // MEASURE
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(subStrLen("aabccbb", 2)); // 5
		System.out.println(subStrLen("abbcb", 1)); // 4
		System.out.println(subStrLen("abccde", 1)); // 3

		System.out.println(subStrLen2("aabccbb", 2)); // 5
		System.out.println(subStrLen2("abbcb", 1)); // 4
		System.out.println(subStrLen2("abccde", 1)); // 3
	}
}
