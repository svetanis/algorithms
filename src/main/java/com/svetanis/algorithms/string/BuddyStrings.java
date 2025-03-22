package com.svetanis.algorithms.string;

// 859. Buddy Strings

public final class BuddyStrings {
	// Time Complexity: O(n)

	public static boolean buddyStrings(String s, String g) {
		if (s.length() != g.length()) {
			return false;
		}
		int diffs = 0;
		int[] scount = new int[26];
		int[] gcount = new int[26];
		for (int i = 0; i < s.length(); i++) {
			scount[s.charAt(i) - 'a']++;
			gcount[g.charAt(i) - 'a']++;
			if (s.charAt(i) != g.charAt(i)) {
				diffs++;
			}
		}
		boolean dups = false;
		for (int i = 0; i < 26; i++) {
			if (scount[i] != gcount[i]) {
				return false;
			}
			if (scount[i] > 1) {
				dups = true;
			}
		}
		return diffs == 2 || (diffs == 0 && dups);
	}

	public static void main(String[] args) {
		System.out.println(buddyStrings("ab", "ba")); // true
		System.out.println(buddyStrings("ab", "ab")); // false
		System.out.println(buddyStrings("aa", "aa")); // true
	}
}
