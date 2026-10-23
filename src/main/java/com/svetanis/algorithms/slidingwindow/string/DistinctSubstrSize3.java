package com.svetanis.algorithms.slidingwindow.string;

import java.util.HashMap;
import java.util.Map;

// 1876. Substrings of Size Three with Distinct Characters
//
// Input: a string.
// Return: how many substrings of exactly three characters have three different
// characters. Each position counts separately, so a repeated substring is counted
// every time it occurs.
//
// The one idea: the same jump as LeetCode 3. The map remembers where each character
// was last seen; when the new character was seen inside the window, left jumps
// just past that earlier copy, so the window never holds a repeat. Whenever the
// window reaches three characters it is a valid substring: count it, and move left
// one step so the next window starts one place later.
//
// Siblings: DistinctSubstrSize3Simple -- compares the three characters at every
//   position directly, with no map and no window bookkeeping.
//
// Time: O(n) -- one pass, constant work per character.
// Space: O(d) -- one map entry per different character, d at most the size of
//   the alphabet.

public final class DistinctSubstrSize3 {

	public static int count(String s) {
		int left = 0;
		int count = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int right = 0; right < s.length(); right++) {
			char c = s.charAt(right);
			if (map.containsKey(c)) {
				left = Math.max(left, map.get(c) + 1); // JUMP: past c's last position, never backwards
			}
			if (right - left + 1 == 3) {
				count++; // COUNT: three characters, no repeat among them
				left++; // OUT: the next window starts one place later
			}
			map.put(c, right); // RECORD: c was last seen at right
		}
		return count;
	}

	public static void main(String[] args) {
		String s1 = "xyzzaz";
		System.out.println(count(s1)); // 1

		String s2 = "aababcabc";
		System.out.println(count(s2)); // 4

		String s3 = "owuxoelszb";
		System.out.println(count(s3)); // 8

	}
}
