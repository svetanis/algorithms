package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static java.lang.Integer.MAX_VALUE;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// 76. Minimum Window Substring
//
// A string s and a pattern p. Returns the shortest substring of s that contains every character
// of p, duplicates included ("AABC" needs two A's); it may hold other characters too. Empty
// when no substring does. LC 76 promises English letters only, and a single shortest answer.
//
// Two maps: what the pattern needs, and what the window holds. count is how many needed copies
// the window holds, so the window is complete once count == p.length(). After that, count never
// drops: for each right, left slides past everything that can go -- a character the pattern does
// not use, or a spare copy of one it does -- and stops at the first character the window cannot
// lose. The window is then still complete, and it is the shortest complete window ending at
// right.
//
// The answer goes through .trim(). With letters only it does nothing; with a space in p it would
// cut a needed space off the window (s = "a b", p = " " gives empty, not " ").
//
// Siblings, LC 76:
//   MinWindowSubStrLen           the same two maps; returns the length
//   MinWindowSubStrEfficient     one map of what is still needed, and a matched counter; shrinks
//                                while the window is complete and records inside that loop
//   MinWindowSubStrLenEfficient  the one-map version; returns the length
//   MinWindowSubStrSubmit        the one-map version; returns a plain String, "" for none
//   MinWindowSubStrLexSmallest   the variant that breaks ties by dictionary order

public final class MinWindowSubStr {

	public static Optional<String> minWindow(String s, String p) {
		// Time complexity: O(n + m), right and left each cross s once; building the map reads p once
		// Space complexity: O(m), both maps hold only characters of p; plus the answer's copy

		int left = 0;
		int count = 0;
		int min = MAX_VALUE;
		String substr = "";
		Map<Character, Integer> map = freqMap(p); // what the pattern needs, never changed
		Map<Character, Integer> sw = new HashMap<>(); // what the window holds

		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			// skip chars not in pattern
			if (!map.containsKey(c)) {
				continue;
			}
			sw.put(c, sw.getOrDefault(c, 0) + 1); // ADD
			if (sw.get(c) <= map.get(c)) {
				count++; // this copy was still needed
			}

			// if window constraint is satisfied
			if (count == p.length()) {
				// SHRINK: advance begin index as far right as possible,
				// stop when advancing breaks window constraint
				while (!map.containsKey(s.charAt(left)) || 
						sw.get(s.charAt(left)) > map.get(s.charAt(left))) {
					char front = s.charAt(left);
					if (map.containsKey(front) && sw.get(front) > map.get(front)) {
						sw.put(front, sw.get(front) - 1); // a spare copy leaves; this is what ends the loop
					}
					left++;
				}
				if (right - left + 1 < min) { // MEASURE: after the shrink, the window is still complete
					min = right - left + 1;
					substr = s.substring(left, right + 1).trim();
				}
			}
		}
		return substr.length() == 0 ? Optional.empty() : Optional.of(substr);
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
