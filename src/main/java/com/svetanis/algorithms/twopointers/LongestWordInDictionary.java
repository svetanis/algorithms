package com.svetanis.algorithms.twopointers;

import java.util.Arrays;
import java.util.List;

// 524. Longest Word in Dictionary through Deleting
//
// Input: a string s and a list of candidate words, all lowercase.
// Return: the longest candidate that can be made by deleting letters from s -- that is,
// a subsequence of s. On a tie in length, the lexicographically smallest. "" when
// no candidate is a subsequence.
//
// The one idea: test each candidate with the two-sequence scan. One pointer walks s
// and moves on every step; the other walks the word and moves only when the letters
// match. The word is a subsequence exactly when its pointer reaches the end. Wrap that
// in a loop over the candidates and keep the best: strictly longer, or equal length
// and strictly smaller. "" is both the starting value and the "nothing matched"
// answer, because no candidate is shorter than it.
//
// Two methods, the same problem:
//   longestWord         -- tests every candidate, then asks whether it wins
//   longestWordWithSkip -- asks whether it could win first, and tests only those
// Sibling: twopointers.LongestWordInDictionaryNextOccurrence -- builds a table of
//   "where is the next c from index i" once, so each candidate costs its own length
//   instead of a scan of s.
// Not LC 720 Longest Word in Dictionary (string.LongestWordInDictionary), which
// builds a word one letter at a time from other words in the list.
//
// Time: O(n * k), n = s.length(), k = number of candidates. isSubSeq is O(n), not
//   O(n * word.length()): i advances every iteration and the loop stops at i == n,
//   so the word length never multiplies in. The tie-break's compareTo runs only on a
//   match, and a matching word is no longer than s.
// Space: O(1) -- beyond the answer itself.

public final class LongestWordInDictionary {

	public static String longestWord(String s, List<String> list) {
		String result = "";                // START: "" until something matches
		for (String word : list) {
			if (isSubSeq(s, word)) {       // TEST every candidate
				int len = word.length();
				int max = result.length();
				boolean equals = len == max && word.compareTo(result) < 0;
				if (len > max || equals) { // COMPARE: longer, or the same length and smaller
					result = word;         // RECORD
				}
			}
		}
		return result;
	}

	// the same scan, with one guard in front of it: decide whether the word
	// could win BEFORE paying to test it. three lines, no data structure, and
	// it needs candidates to MATCH -- with nothing matching, result stays ""
	// and nothing is ever filtered, so it degrades to longestWord above
	public static String longestWordWithSkip(String s, List<String> list) {
		String result = "";
		for (String word : list) {
			if (cannotWin(word, result)) {
				continue;            // SKIP: decided without touching s
			}
			if (isSubSeq(s, word)) { // TEST: the same scan as longestWord
				result = word;       // RECORD: it already beats result
			}
		}
		return result;
	}

	// longer wins; equal length is broken lexicographically, smallest first
	private static boolean beats(String word, String result) {
		int len = word.length();
		int max = result.length();
		return len > max || (len == max && word.compareTo(result) < 0);
	}

	private static boolean cannotWin(String word, String result) {
		return !beats(word, result);
	}

	private static boolean isSubSeq(String s, String word) {
		int n = s.length();
		int m = word.length();
		int i = 0;               // START: the front of s
		int j = 0;               // START: the front of word
		while (i < n && j < m) { // STOP: s ran out, or every letter is placed
			if (s.charAt(i) == word.charAt(j)) {
				j++;             // MATCH: this letter of word is placed
			}
			i++;                 // MOVE: s advances no matter what
		}
		return j == m;           // FOUND only when every letter is placed
	}

	public static void main(String[] args) {
		String s1 = "abpcplea";
		List<String> list1 = Arrays.asList("ale", "apple", "monkey", "plea");
		System.out.println(longestWord(s1, list1)); // apple

		String s2 = "abpcplea";
		List<String> list2 = Arrays.asList("a", "b", "c");
		System.out.println(longestWord(s2, list2)); // a

		System.out.println(longestWordWithSkip(s1, list1)); // apple
		System.out.println(longestWordWithSkip(s2, list2)); // a
	}
}
