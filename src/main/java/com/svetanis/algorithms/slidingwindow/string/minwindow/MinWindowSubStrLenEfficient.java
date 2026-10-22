package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// 76. Minimum Window Substring -- the length only
//
// A string s and a pattern p (at least one character). Returns the LENGTH of the shortest
// substring of s that contains every character of p, duplicates included ("AABC" needs two
// A's); it may hold other characters too. Empty when no substring does.
//
// One map: how many more of each pattern character the window still needs. It goes negative
// when the window holds a spare. matched counts needed copies, not distinct characters, so the
// window is complete at matched == p.length(). Shrink while the window is complete, measuring
// each time before the left character leaves; a 0 in the map means that character has no spare,
// so losing it breaks the window and the shrink stops.
//
// Siblings, LC 76:
//   MinWindowSubStr              two maps (needed and held) and a count that never drops;
//                                records after the shrink; returns the substring
//   MinWindowSubStrLen           the two-map version; returns the length
//   MinWindowSubStrEfficient     this one map; returns the substring
//   MinWindowSubStrSubmit        this one map; returns a plain String, "" for none
//   MinWindowSubStrLexSmallest   the variant that breaks ties by dictionary order

public final class MinWindowSubStrLenEfficient {
	// Time complexity: O(n + m), right and left each cross s once; building the map reads p once
	// Space complexity: O(m), one map entry per distinct character of p

	public static Optional<Integer> minWindow(String s, String p) {

		int left = 0;
		int matched = 0;
		int min = MAX_VALUE;
		Map<Character, Integer> map = freqMap(p); // still needed; changed as the window moves

		for (int right = 0; right < s.length(); right++) {
			// 1. keep a running count of every
			// matching instance of a char
			char c = s.charAt(right);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1); // ADD: one fewer of c still needed
				// include '>=' to account for duplicates in pattern
				if (map.get(c) >= 0) {
					matched++;
				}
			}

			// 2. whenever all the chars are matched,
			// shrink the window from the beginning
			while (matched == p.length()) { // SHRINK: while the window is complete
				// keep track of the smallest substr
				// that has all the matching chars
				min = min(min, right - left + 1); // MEASURE: before the left character leaves
				// 3. stop the shrinking process as soon as
				// a matched char removed from sliding window
				char front = s.charAt(left++);
				// redundant matching char (aa)
				// when need only one
				if (map.containsKey(front)) {
					// second redundant: decrement the matched count
					// when second char goes out of the window
					if (map.get(front) == 0) {
						matched--;
					}
					// first redundant: shrink window without
					// decrementing the matched count
					map.put(front, map.get(front) + 1);
				}
			}
		}
		return min == MAX_VALUE ? Optional.empty() : Optional.of(min);
	}

	private static Map<Character, Integer> freqMap(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(minWindow("ADOBECODEBANC", "ABC")); // Optional[4]  -- the length of "BANC"
		System.out.println(minWindow("coobdafceeaxab", "abc")); // Optional[5]  -- the length of "bdafc"
		System.out.println(minWindow("aabdec", "abc")); // Optional[5]  -- the length of "abdec"
		System.out.println(minWindow("aabdec", "abac")); // Optional[6]  -- the length of "aabdec"
		System.out.println(minWindow("abdbca", "abc")); // Optional[3]  -- the length of "bca"
		System.out.println(minWindow("adcad", "abc")); // Optional.empty -- no window contains every character
	}
}
