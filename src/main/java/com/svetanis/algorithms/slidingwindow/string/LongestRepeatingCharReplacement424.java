package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 424. Longest Repeating Character Replacement

// Given a string s of UPPERCASE English letters and an integer k,
// you may change any character to any other uppercase English
// letter, at most k times. Return the length of the longest
// substring containing a single repeated letter.

// Same algorithm as MaxUnivalSubStrLenAfterKReplacements,
// which states the problem over a lowercase alphabet.

public final class LongestRepeatingCharReplacement424 {
	// Time complexity: O(n)

	// the window is valid while (size - most frequent letter) <= k,
	// because every other letter in it has to be replaced

	public static int characterReplacement(String s, int k) {
		int max = 0;
		int left = 0;
		int count = 0; // max repeated letter count
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			map.put(c, map.getOrDefault(c, 0) + 1);
			count = Math.max(count, map.get(c));
			while (right - left + 1 - count > k) {
				char front = s.charAt(left);
				map.put(front, map.get(front) - 1);
				left++;
			}
			max = Math.max(max, right - left + 1);
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
			a[c - 'A']++;
			count = Math.max(count, a[c - 'A']);
			// 'if', not 'while': the window never has to shrink, only
			// slide. count is allowed to go stale, because a smaller
			// window can never beat the best one already recorded.
			if (right - left + 1 - count > k) {
				char front = s.charAt(left);
				a[front - 'A']--;
				left++;
			}
			max = Math.max(max, right - left + 1);
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
