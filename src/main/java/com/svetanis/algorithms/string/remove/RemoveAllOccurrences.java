package com.svetanis.algorithms.string.remove;

// 1910. Remove All Occurrences of a Substring
//
// Input: strings s and part. While part occurs in s, remove its leftmost occurrence. Returns the
// final s. On LeetCode: 1 <= s.length <= 1000, 1 <= part.length <= 1000, both are lowercase
// English letters. (part is used as a regular expression by removeOccurrencesSimple; lowercase
// letters have no special meaning in one.)
//
// The one idea: cutting out an occurrence can join its two neighbours into a new occurrence, so one
// pass is not enough. In "axxxxyyyyb" with part "xy", the only "xy" is in the middle; cutting it
// leaves "axxxyyyb", which has a new one. So repeat: find the leftmost occurrence, cut it out,
// search again, until there is none. Each cut makes s shorter by at least one letter, so the
// loop ends.
//
// Time: O(n^2 / m) for n = s.length and m = part.length -- at most n / m removals, and each one
// searches and rebuilds a string of up to n letters.
// Space: O(n) -- the copy of s that is being edited.
//
// Siblings: RemoveAllOccurrencesRecursive does the same with recursion and String.replaceFirst.
// removeOccurrencesSimple is that same repeat on a String; removeOccurrences edits one builder.

public final class RemoveAllOccurrences {

	public static String removeOccurrencesSimple(String s, String p) {
		while (s.contains(p)) { // REPEAT: until no occurrence is left
			s = s.replaceFirst(p, ""); // CUT: the leftmost occurrence
		}
		return s;
	}

	public static String removeOccurrences(String s, String p) {
		StringBuilder sb = new StringBuilder(s);
		int index = sb.indexOf(p); // FIND: leftmost occurrence, -1 if none
		while (index >= 0) {
			sb.replace(index, index + p.length(), ""); // CUT it out
			index = sb.indexOf(p);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(removeOccurrencesSimple("daabcbaabcbc", "abc")); // dab
		System.out.println(removeOccurrencesSimple("axxxxyyyyb", "xy")); // ab
		System.out.println(removeOccurrencesSimple("aabababa", "aba")); // ba

		System.out.println(removeOccurrences("daabcbaabcbc", "abc")); // dab
		System.out.println(removeOccurrences("axxxxyyyyb", "xy")); // ab
		System.out.println(removeOccurrences("aabababa", "aba")); // ba
	}
}
