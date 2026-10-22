package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static java.lang.Integer.MAX_VALUE;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// 76. Minimum Window Substring
//
// A string s and a pattern p (at least one character). Returns the shortest substring of s that
// contains every character of p, duplicates included ("AABC" needs two A's); it may hold other
// characters too. Empty when no substring does. LC 76 promises a single shortest answer; on a
// tie this returns the leftmost.
//
// One map: how many more of each pattern character the window still needs. It goes negative
// when the window holds a spare. matched counts needed copies, not distinct characters, so the
// window is complete at matched == p.length(). Shrink while the window is complete, measuring
// each time before the left character leaves; a 0 in the map means that character has no spare,
// so losing it breaks the window and the shrink stops.
//
// Siblings, LC 76:
//   MinWindowSubStr              two maps (needed and held) and a count that never drops;
//                                records after the shrink
//   MinWindowSubStrLen           the two-map version; returns the length
//   MinWindowSubStrLenEfficient  this one map; returns the length
//   MinWindowSubStrSubmit        this one map; returns a plain String, "" for none
//   MinWindowSubStrLexSmallest   the variant that breaks ties by dictionary order

public final class MinWindowSubStrEfficient {

	public static Optional<String> minWindow(String s, String p) {
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

			// SHRINK: while the window is complete
			while (matched == p.length()) {
				if (min > right - left + 1) { // MEASURE: before the left character leaves
					min = right - left + 1;
					start = left;
				}
				char front = s.charAt(left++);
				if (map.containsKey(front)) {
					if (map.get(front) == 0) {
						matched--; // no spare: losing front breaks the window
					}
					map.put(front, map.get(front) + 1);
				}
			}
		}
		return min > n ? Optional.empty() : Optional.of(s.substring(start, start + min));
	}

	private static Map<Character, Integer> freqMap(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(minWindow("ADOBECODEBANC", "ABC")); // Optional[BANC]
		System.out.println(minWindow("coobdafceeaxab", "abc")); // Optional[bdafc]
		System.out.println(minWindow("aabdec", "abc")); // Optional[abdec]
		System.out.println(minWindow("aabdec", "abac")); // Optional[aabdec]
		System.out.println(minWindow("abdbca", "abc")); // Optional[bca]
		System.out.println(minWindow("adcad", "abc")); // Optional.empty
	}
}
