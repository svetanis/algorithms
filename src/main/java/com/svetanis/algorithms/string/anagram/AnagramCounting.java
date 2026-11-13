package com.svetanis.algorithms.string.anagram;

// 242. Valid Anagram
//
// Input: two strings s1 and s2. Returns: true when s2 is an anagram of s1 -- the same
// characters, each the same number of times, in any order. Two empty strings are anagrams.
//
// The one idea: COUNT the characters. One array holds a count per character code; a
// character of s1 adds one to its slot and the character of s2 at the same position takes
// one away. The strings are anagrams exactly when every slot ends at zero. The two strings
// are walked together, so different lengths are answered first -- they can never be
// anagrams. The table has 256 slots, so a character above code 255 throws; LeetCode's
// strings are lowercase letters only.
//
// Siblings -- the same question, answered three other ways:
//   AnagramSorting  -- sorts both strings' characters and compares the results; no counts
//   AnagramHashing  -- the same counting, kept in a HashMap from character to count, so any
//                      character works and only the characters present take space
//   AnagramSubStr   -- crosses each character of s1 off a shrinking copy of s2; O(n^2)

public final class AnagramCounting {
	// Time Complexity: O(n), one pass over both strings, then 256 slots checked
	// Space Complexity: O(1), a fixed table of 256 counts, whatever the length

	public static boolean areAnagrams(String s1, String s2) {
		if (s1.length() != s2.length()) { // LENGTH: different lengths are never anagrams
			return false;
		}
		int[] a = new int[256]; // TABLE: one count per character code
		for (int i = 0; i < s1.length(); ++i) { // WALK: both strings, position by position
			a[s1.charAt(i)]++; // COUNT: a character of s1 adds one
			a[s2.charAt(i)]--; // COUNT: a character of s2 takes one away
		}

		for (int count : a) { // CHECK: every slot
			if (count != 0) { // TEST: a nonzero slot is a character the two hold unequally
				return false;
			}
		}
		return true; // RETURN: every count cancelled
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
