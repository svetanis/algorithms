package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.utils.Maps.freqMap;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

import java.util.Map;

import com.google.common.base.Optional;

// given a string and a pattern, find the smallest substring
// in the given string which has all the chars of the given pattern
// the required substring can have some additional characters
// and doesn't need to be a permutation of the pattern.

public final class MinWindowSubStrLen {

	public static Optional<Integer> minWindow(String s, String p) {
		// Time complexity: O(n + m)

		int left = 0;
		int count = 0;
		int min = MAX_VALUE;
		Map<Character, Integer> map = freqMap(p);
		Map<Character, Integer> swm = newHashMap();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			// skip chars not in pattern
			if (!map.containsKey(c)) {
				continue;
			}
			swm.put(c, swm.getOrDefault(c, 0) + 1);
			if (swm.get(c) <= map.get(c)) {
				count++;
			}

			// if window constraint is satisfied
			if (count == p.length()) {
				// advance left index as far right as possible,
				// stop when advancing breaks window constraint
				while (!map.containsKey(s.charAt(left)) || 
						swm.get(s.charAt(left)) > map.get(s.charAt(left))) {
					char front = s.charAt(left);
					if (map.containsKey(front) && swm.get(front) > map.get(front)) {
						swm.put(front, swm.get(front) - 1);
					}
					left++;
				}
				min = min(min, right - left + 1);
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
