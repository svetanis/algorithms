package com.svetanis.algorithms.string;

// 859. Buddy Strings
//
// Input: strings s and goal. A swap exchanges the characters at two different positions of s.
// Returns true if exactly one swap turns s into goal. On LeetCode: 1 <= s.length, goal.length
// <= 2 * 10^4, both lowercase letters.
//
// The one idea: compare s and goal position by position and count the positions where they
// differ. Exactly one swap changes exactly two positions, so there are three cases.
// (1) The lengths differ, or the two strings do not hold the same letters: no swap can work.
// (2) They differ at exactly 2 positions and hold the same letters: swapping those two characters
// fixes both. (3) They differ nowhere: s already equals goal, and a swap must change nothing,
// which is possible only when some letter appears twice -- swap those two equal letters.
// One difference, or three or more, is never fixed by a single swap.
//
// Time: O(n) -- one pass over both strings, then 26 counts
// Space: O(1) -- two arrays of 26 counts

public final class BuddyStrings {

	public static boolean buddyStrings(String s, String g) {
		if (s.length() != g.length()) {
			return false; // SKIP: different lengths
		}
		int diffs = 0; // DIFFS: positions where s and g disagree
		int[] scount = new int[26]; // COUNT: how often each letter occurs in s
		int[] gcount = new int[26]; // COUNT: how often each letter occurs in g
		for (int i = 0; i < s.length(); i++) {
			scount[s.charAt(i) - 'a']++;
			gcount[g.charAt(i) - 'a']++;
			if (s.charAt(i) != g.charAt(i)) {
				diffs++;
			}
		}
		boolean dups = false; // DUPS: some letter appears at least twice in s
		for (int i = 0; i < 26; i++) {
			if (scount[i] != gcount[i]) {
				return false; // SKIP: different letters, no swap can fix that
			}
			if (scount[i] > 1) {
				dups = true;
			}
		}
		return diffs == 2 || (diffs == 0 && dups); // RETURN: one real swap, or a swap of two equal letters
	}

	public static void main(String[] args) {
		System.out.println(buddyStrings("ab", "ba")); // true
		System.out.println(buddyStrings("ab", "ab")); // false
		System.out.println(buddyStrings("aa", "aa")); // true
	}
}
