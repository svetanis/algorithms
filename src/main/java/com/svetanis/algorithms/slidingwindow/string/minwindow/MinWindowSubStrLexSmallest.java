package com.svetanis.algorithms.slidingwindow.string.minwindow;

import java.util.HashMap;
import java.util.Map;

// Given two strings, original and check, 
// return the minimum substring of original 
// such that each character in check, 
// including duplicates, are included in this substring. 
// By "minimum", I mean the shortest substring. 
// If two substrings that satisfy the condition have the same length, 
// the one that comes lexicographically first is smaller.

public final class MinWindowSubStrLexSmallest {

	public static String minWindow(String s, String p) {
		// Time complexity: O(n + m)

		int left = 0;
		int start = 0;
		int matched = 0;
		int n = s.length();
		int min = Integer.MAX_VALUE;
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
				int windowSize = right - left + 1;
				boolean equals = min == windowSize;
				String substr = s.substring(left, right + 1);
				boolean first = equals && substr.compareTo(s.substring(start, start + min)) < 0;
				if (min > windowSize || first) {
					min = windowSize;
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
		System.out.println(minWindow("cdbaebaecd", "abc")); // baec
		System.out.println(minWindow("AEOIEAIOUAOEIAOEIIAAEIUIOUUA", "AEIOU")); // EAIOU
		System.out.println(minWindow("aabbababaabaabbaaabbabbabbaabbabaabbabbbbabbaaababbaabb", "bababa")); // aababb
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
