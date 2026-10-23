package com.svetanis.algorithms.slidingwindow.string;

import static java.lang.Math.max;

import java.util.HashMap;
import java.util.Map;

// Longest Substring with Exactly Two Distinct Characters
// (no LeetCode number; source unknown -- LeetCode 159 is the "at most two" version)

// Given a string, find the length of the longest
// substring that contains exactly two distinct char
// Returns 0 when the string holds fewer than two different characters.

// The one idea: run the "at most two" window and measure only when it
// holds exactly two. After the shrink, left..right is the longest window
// ending at right with at most two different characters, and every window
// ending at right with exactly two lies inside it. So when it holds only
// one character, no window ending at right holds two, and skipping the
// measurement loses nothing.

// Siblings:
//   LongestSubStrLenAtMostTwoDistinctChars (159) -- the same loop measuring
//     every window, so one repeated character also counts.
//   LongestSubStrLenAtMostKUnique (340) -- at most k.
//   LongestSubStrKUniqueHashMap -- exactly k, returning the substring.

// Time: O(n) -- each character enters the window once and leaves at most once.
// Space: O(1) -- the map holds at most three characters.

public final class LongestSubStrLenTwoDistinctChar {

	public static int maxLen(String str) {
		int n = str.length();
		int max = 0; // max window size
		int left = 0; // current start
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < n; right++) {
			char curr = str.charAt(right);
			map.put(curr, map.getOrDefault(curr, 0) + 1); // ADD
			while (map.size() > 2) { // SHRINK: while three different characters
				char front = str.charAt(left);
				map.put(front, map.get(front) - 1);
				if (map.get(front) == 0) {
					map.remove(front); // REMOVE: count 0, so map.size() stops counting it
				}
				left++;
			}
			if (map.size() == 2) {
				max = max(max, right - left + 1); // MEASURE: only a window with exactly two
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(maxLen("ababababa"));
		System.out.println(maxLen("e"));
		System.out.println(maxLen("baabcbab"));
		System.out.println(maxLen("aabbcca"));
	}
}
