package com.svetanis.algorithms.slidingwindow.string.minwindow;

import static java.lang.Integer.MAX_VALUE;

import java.util.HashMap;
import java.util.Map;

// 76. Minimum Window Substring
// given a string and a pattern, find the smallest substring
// in the given string which has all the chars of the given pattern
// the required substring can have some additional characters
// and doesn't need to be a permutation of the pattern.

public final class MinWindowSubStrSubmit {

	public static String minWindow(String s, String p) {
		// Time complexity: O(n + m)

		int left = 0;
		int start = 0;
		int matched = 0;
		int n = s.length();
		int min = MAX_VALUE;
		Map<Character, Integer> map = freqMap(p);

		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1);
				if (map.get(c) >= 0) {
					matched++;
				}
			}

			// shrink window
			while (matched == p.length()) {
				if (min > right - left + 1) {
					min = right - left + 1;
					start = left;
				}
				char front = s.charAt(left);
				left++;
				if (map.containsKey(front)) {
					if (map.get(front) == 0) {
						matched--;
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
