package com.svetanis.algorithms.slidingwindow.string.minwindow;

import java.util.HashMap;
import java.util.Map;

// Minimum window substring, ties broken by dictionary order (no LeetCode number; source
// unknown -- LC 76 with a tie rule added)
//
// Given two strings, original and check,
// return the minimum substring of original
// such that each character in check,
// including duplicates, are included in this substring.
// By "minimum", I mean the shortest substring.
// If two substrings that satisfy the condition have the same length,
// the one that comes lexicographically first is smaller.
// Returns "" when no substring qualifies.
//
// The shortest-window loop of LC 76: shrink while the window is complete, measuring before the
// left character leaves. Two maps: what the pattern needs (pc) and what the window holds (wc).
// matched counts DISTINCT characters, not copies: it goes up when a character's count in the
// window reaches exactly its need, and down when it falls below, so the window is complete at
// matched == pc.size(). On a tie in length, the window is compared with the best so far as text.
//
// Siblings, LC 76 without the tie rule (each counts needed copies, matched == p.length()):
//   MinWindowSubStr, MinWindowSubStrLen            two maps; substring / length
//   MinWindowSubStrEfficient, ...LenEfficient      one map of what is still needed; substring / length
//   MinWindowSubStrSubmit                          one map; a plain String, "" for none

public final class MinWindowSubStrLexSmallest {

	public static String minWindow(String s, String p) {
		// Time complexity: O(n^2) in the worst case. right and left each cross s once, but every
		// shrink step copies the current window to compare it: s = "aa...ab", p = "ab" shrinks
		// one long window step by step, copying about n^2 / 2 characters
		// Space complexity: O(n), the window copy, and wc holds every distinct character of s

		int left = 0;
		int start = 0;
		int matched = 0;
		int n = s.length();
		int min = Integer.MAX_VALUE;
		Map<Character, Integer> pc = freqMap(p);
		Map<Character, Integer> wc = new HashMap<>();

		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			wc.put(c, wc.getOrDefault(c, 0) + 1); // ADD
			// equals, not ==: these are two Integers, and a reference compare
			// only works inside the -128..127 cache -- a character occurring
			// 128 times in one window would never register as matched
			if (pc.containsKey(c) && pc.get(c).equals(wc.get(c))) {
				matched++; // c's count just reached its need
			}

			// shrink window
			while (matched == pc.size()) { // SHRINK: while the window is complete
				int windowSize = right - left + 1;
				boolean equals = min == windowSize;
				String substr = s.substring(left, right + 1);
				boolean first = equals && substr.compareTo(s.substring(start, start + min)) < 0;
				if (min > windowSize || first) { // MEASURE: shorter, or as short and first in dictionary order
					min = windowSize;
					start = left;
				}
				char front = s.charAt(left);
				wc.put(front, wc.get(front) - 1);
				if (pc.containsKey(front) && wc.get(front) < pc.get(front)) {
					matched--; // front fell below its need: the window is broken
				}
				left++;
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
