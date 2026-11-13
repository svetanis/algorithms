package com.svetanis.algorithms.string.anagram;

import java.util.HashMap;
import java.util.Map;

// Valid anagram, counted in a HashMap -- not a LeetCode file; the question is LeetCode 242,
// which AnagramCounting carries.
//
// Input: two strings str1 and str2. Returns: true when str2 is an anagram of str1 -- the same
// characters, each the same number of times, in any order. Two empty strings are anagrams.
//
// The one idea: COUNT the characters in a map, character -> count. A character of str1 adds
// one and the character of str2 at the same position takes one away. The strings are
// anagrams exactly when every character in the map ends at zero: the number of keys equals
// the number of keys whose count is zero. Different lengths are answered first.
//
// Siblings -- the same question, answered three other ways:
//   AnagramCounting -- the same counting in a fixed array of 256 slots: no hashing, but only
//                      character codes up to 255
//   AnagramSorting  -- sorts both strings' characters and compares the results; no counts
//   AnagramSubStr   -- crosses each character of str1 off a shrinking copy of str2; O(n^2)

public final class AnagramHashing {
	// Time Complexity: O(n), one pass over both strings, then one pass over the k distinct
	// characters, k <= n
	// Space Complexity: O(k), one map entry per distinct character

	public static boolean areAnagrams(String str1, String str2) {
		if (str1.length() != str2.length()) { // LENGTH: different lengths are never anagrams
			return false;
		}
		Map<Character, Integer> map = new HashMap<>(); // TABLE: character -> count
		for (int i = 0; i < str1.length(); i++) { // WALK: both strings, position by position
			char c1 = str1.charAt(i);
			char c2 = str2.charAt(i);
			map.put(c1, map.getOrDefault(c1, 0) + 1); // COUNT: a character of str1 adds one
			map.put(c2, map.getOrDefault(c2, 0) - 1); // COUNT: a character of str2 takes one away
		}
		long zeros = map.values().stream().filter(v -> v == 0).count(); // CHECK: the counts that cancelled
		return map.size() == zeros; // RETURN: every count cancelled
	}

	public static void main(String[] args) {
		for (String[] pair : pairs()) {
			String w1 = pair[0];
			String w2 = pair[1];
			System.out.println(areAnagrams(w1, w2));
		}
	}

	private static String[][] pairs() {
		return new String[][] {
				{ "anagramm", "nagaramm" }, // true
				{ "aaca", "aca" }, // false
				{ "apple", "papel" }, // true
				{ "carrot", "tarroc" }, // true
				{ "hello", "llloh" }, // false
		};
	}
}
