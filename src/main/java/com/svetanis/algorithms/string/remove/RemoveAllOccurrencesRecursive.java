package com.svetanis.algorithms.string.remove;

// 1910. Remove All Occurrences of a Substring
//
// Input: strings s and p (LeetCode calls it part). While p occurs in s, remove its leftmost
// occurrence. Returns the final s. On LeetCode: 1 <= s.length <= 1000, 1 <= p.length <= 1000, both
// are lowercase English letters. (p is used as a regular expression by replaceFirst; lowercase
// letters have no special meaning in one.)
//
// The one idea: cutting out an occurrence can join its two neighbours into a new occurrence, so
// one cut is not enough. If s has no occurrence, s is the answer. Otherwise cut the leftmost one
// and solve the shorter string the same way. Each call makes s shorter by at least one letter, so
// the recursion ends; it is at most s.length / p.length calls deep, at most 1000.
//
// Time: O(n^2 / m) for n = s.length and m = p.length -- at most n / m calls, and each one searches
// and rebuilds a string of up to n letters.
// Space: O(n^2 / m) -- n / m calls are open at the deepest point, each holding its own copy of s.
//
// Siblings: RemoveAllOccurrences makes the same repeat with a loop, in two versions.

public final class RemoveAllOccurrencesRecursive {

	public static String removeOccurrences(String s, String p) {
		if (!s.contains(p)) { // BASE: nothing left to cut
			return s;
		}
		s = s.replaceFirst(p, ""); // CUT: the leftmost occurrence
		return removeOccurrences(s, p); // RECURSE: on the shorter string
	}

	public static void main(String[] args) {
		System.out.println(removeOccurrences("daabcbaabcbc", "abc")); // dab
		System.out.println(removeOccurrences("axxxxyyyyb", "xy")); // ab
		System.out.println(removeOccurrences("aabababa", "aba")); // ba
	}
}
