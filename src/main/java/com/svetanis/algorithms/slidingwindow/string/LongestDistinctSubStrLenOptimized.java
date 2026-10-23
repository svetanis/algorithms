package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 3. Longest Substring Without Repeating Characters
//
// Input: a string.
// Return: the length of the longest substring in which no character appears
// twice; 0 for an empty string.
//
// The one idea: remember where each character was last seen. When the new
// character was last seen inside the window, every window that still holds that
// earlier copy has a repeat, so left jumps straight to the position after it --
// one step instead of removing characters one at a time. The map is never cleaned,
// so it also holds positions from before the window; jumping to one of those
// would move left backwards and let a repeat back in. Math.max keeps left where it
// is in that case.
//
// Siblings:
//   LongestDistinctSubStrLen -- a set of the window's characters, with left
//     stepping forward one character at a time.
//   LongestDistinctSubStr -- the set window, returning the substring.
//
// Time: O(n) -- one pass, constant work per character.
// Space: O(d) -- one map entry per different character in the string.

public final class LongestDistinctSubStrLenOptimized {

	public static int ldl(String str) {
		int max = 0;
		int left = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < str.length(); right++) {
			char c = str.charAt(right);
			if (map.containsKey(c)) {
				left = Math.max(left, map.get(c) + 1); // JUMP: past c's last position, never backwards
			}
			map.put(c, right); // RECORD: c was last seen at right
			max = Math.max(max, right - left + 1); // MEASURE: no repeat in left..right
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(ldl("abcadbef"));
		System.out.println(ldl("abac"));
		System.out.println(ldl("aaaaa"));
		System.out.println(ldl("abccdefgh"));
		System.out.println();
		System.out.println(ldl("aabccbb")); // 3 - abc
		System.out.println(ldl("abbbb")); // 2 - ab
		System.out.println(ldl("abccde")); // 3 - abc & cde
		System.out.println();
		System.out.println(ldl("abcabcbb")); // 3 - abc
		System.out.println(ldl("bbbbb")); // 1 - b
		System.out.println(ldl("pwwkew")); // 3 - wke
	}
}
