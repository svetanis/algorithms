package com.svetanis.algorithms.string.anagram;

// 1347. Minimum Number of Steps to Make Two Strings Anagram
//
// Input: two strings s and t of the same length, lowercase letters. One step replaces one
// character of t with any letter. Returns: the fewest steps that make t an anagram of s.
//
// The one idea: a letter of t that s also has, not yet matched, can stay; every other letter
// of t must be replaced, and one step per such letter is enough, because s has exactly that
// many letters left unmatched to replace them with (the lengths are equal). So count s's
// letters, then walk t taking one off its letter's count: a count that goes below zero is a
// letter of t with no partner left in s -- one step.
//
// Siblings: AnagramCounting answers whether two strings are anagrams with the same counting
// table; here they need not be, and the question is how far apart they are.

public final class MinStepsToMakeAnagram {
	// Time Complexity: O(n + m), one pass over s to count it, one pass over t
	// Space Complexity: O(1), a fixed table of 26 counts

	public static int minSteps(String s, String t) {
		int count = 0;
		int[] chars = freq(s); // COUNT: s's letters
		for(char c : t.toCharArray()) { // WALK: each letter of t
			if(--chars[c - 'a'] < 0) { // MATCH: take one partner from s; below zero means none was left
				count++; // STEP: this letter of t must be replaced
			}
		}
		return count; // RETURN
	}

	private static int[] freq(String s) {
		int[] chars = new int[26];
		for (char c : s.toCharArray()) {
			chars[c - 'a']++; // COUNT: one more of this letter
		}
		return chars;
	}

	public static void main(String[] args) {
		System.out.println(minSteps("bab", "aba")); // 1
		System.out.println(minSteps("leetcode", "practice")); // 5
		System.out.println(minSteps("anagram", "mangaar")); // 0
	}
}