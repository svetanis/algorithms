package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.svetanis.java.base.utils.Maps.freqMap;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

import java.util.Map;

import com.google.common.base.Optional;

// given a string and a pattern, find the smallest substring
// in the given string which has all the chars of the given pattern
// the required substring can have some additional characters
// and doesn't need to be a permutation of the pattern.

public final class MinWindowSubStrLenEfficient {
	// Time complexity: O(n + m)

	public static Optional<Integer> minWindow(String s, String p) {

		int left = 0;
		int matched = 0;
		int min = MAX_VALUE;
		Map<Character, Integer> map = freqMap(p);

		for (int right = 0; right < s.length(); right++) {
			// 1. keep a running count of every
			// matching instance of a char
			char c = s.charAt(right);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1);
				// include '>' to account for duplicates in pattern
				if (map.get(c) >= 0) {
					matched++;
				}
			}

			// 2. whenever all the chars are matched,
			// shrink the window from the beginning
			while (matched == p.length()) {
				// keep track of the smallest substr
				// that has all the matching chars
				min = min(min, right - left + 1);
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
		return min == MAX_VALUE ? absent() : of(min);
	}

	public static void main(String[] args) {
		System.out.println(minWindow("ADOBECODEBANC", "ABC")); // BANC
		System.out.println(minWindow("coobdafceeaxab", "abc")); // bdafc
		System.out.println(minWindow("aabdec", "abc")); // abdec
		System.out.println(minWindow("aabdec", "abac")); // aabdec
		System.out.println(minWindow("abdbca", "abc")); // bca
		System.out.println(minWindow("adcad", "abc")); // ""
	}
}
