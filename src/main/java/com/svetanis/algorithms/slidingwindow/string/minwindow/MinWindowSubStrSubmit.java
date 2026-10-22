package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static java.lang.Integer.MAX_VALUE;

import java.util.HashMap;
import java.util.Map;

// 76. Minimum Window Substring
// given a string and a pattern, find the smallest substring
// in the given string which has all the chars of the given pattern
// the required substring can have some additional characters
// and doesn't need to be a permutation of the pattern.
//
// A string s and a pattern p (at least one character). Duplicates in p count: "AABC" needs two
// A's. Returns the shortest such substring, or "" when none exists. LC 76 promises a single
// shortest answer; on a tie this returns the leftmost.
//
// One map: how many more of each pattern character the window still needs. It goes negative
// when the window holds a spare. matched counts needed copies, not distinct characters, so the
// window is complete at matched == p.length(). Shrink while the window is complete, measuring
// each time before the left character leaves; a 0 in the map means that character has no spare,
// so losing it breaks the window and the shrink stops.
//
// Siblings, LC 76:
//   MinWindowSubStrEfficient     this one map; returns Optional<String>
//   MinWindowSubStrLenEfficient  this one map; returns the length
//   MinWindowSubStr              two maps (needed and held) and a count that never drops;
//                                records after the shrink; returns Optional<String>
//   MinWindowSubStrLen           the two-map version; returns the length
//   MinWindowSubStrLexSmallest   the variant that breaks ties by dictionary order

public final class MinWindowSubStrSubmit {

	public static String minWindow(String s, String p) {
		// Time complexity: O(n + m), right and left each cross s once; building the map reads p once
		// Space complexity: O(m), one map entry per distinct character of p; plus the answer's copy

		int left = 0;
		int start = 0;
		int matched = 0;
		int n = s.length();
		int min = MAX_VALUE;
		Map<Character, Integer> map = freqMap(p); // still needed; changed as the window moves

		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1); // ADD: one fewer of c still needed
				if (map.get(c) >= 0) {
					matched++; // this copy was needed, not a spare
				}
			}

			// shrink window
			while (matched == p.length()) { // SHRINK: while the window is complete
				if (min > right - left + 1) { // MEASURE: before the left character leaves
					min = right - left + 1;
					start = left;
				}
				char front = s.charAt(left);
				left++;
				if (map.containsKey(front)) {
					if (map.get(front) == 0) {
						matched--; // no spare: losing front breaks the window
					}
					map.put(front, map.get(front) + 1);
				}
			}
		}
		return min > n ? "" : s.substring(start, start + min);
	}

	private static Map<Character, Integer> freqMap(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char element : s.toCharArray()) {
			map.put(element, map.getOrDefault(element, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
		System.out.println(minWindow("coobdafceeaxab", "abc")); // bdafc
		System.out.println(minWindow("aabdec", "abc")); // abdec
		System.out.println(minWindow("aabdec", "abac")); // aabdec
		System.out.println(minWindow("abdbca", "abc")); // bca
		System.out.println(minWindow("adcad", "abc")); // ""
		System.out.println(minWindow("a", "a")); // a
		System.out.println(minWindow("a", "aa")); // ""
	}
}
