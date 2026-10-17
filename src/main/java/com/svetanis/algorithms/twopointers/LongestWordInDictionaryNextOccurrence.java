package com.svetanis.algorithms.twopointers;

import java.util.Arrays;
import java.util.List;

// 524. Longest Word in Dictionary through Deleting
//
// Input: a string s and a list of candidate words, all lowercase.
// Return: the longest candidate that is a subsequence of s; on a tie in length, the
// lexicographically smallest; "" when none is.
//
// The scan version is LongestWordInDictionary.java, and it is the one to write first.
// This file is the follow-up: "the dictionary is huge and s is fixed -- can you do
// better?"
//
// The one idea: next[i][c] answers "starting at index i, where is the next c?" in one
// read, so matching a candidate never touches s again. Building it is O(26n); each
// candidate then costs O(word.length()) instead of O(s.length()), and a candidate
// that fails stops at its first letter with no next occurrence.
//
// Two methods, the same table:
//   longestWord       -- skips a candidate that cannot beat the current answer before
//                        testing it; the skip pays off when candidates MATCH
//   longestWordNoSkip -- tests every candidate; kept so the two speedups can be timed
//                        apart -- the table pays off when candidates FAIL
// Sibling: twopointers.LongestWordInDictionary -- scans s once per candidate, O(1) space.
//
// Time: O(26n + L), n = s.length(), L = total length of the dictionary.
// Space: O(26n) -- the table, one row of 26 per position of s, plus one.

public final class LongestWordInDictionaryNextOccurrence {

	private static final int ALPHABET = 26;   // lowercase English, per the constraints

	public static String longestWord(String s, List<String> list) {
		int[][] next = nextOccurrence(s); // BUILD once: s is fixed
		int n = s.length();
		String result = "";               // START: "" until something matches
		for (String word : list) {
			if (cannotWin(word, result)) {
				continue;                 // SKIP: decided without touching s
			}
			if (isSubSeq(word, next, n)) {
				result = word;            // RECORD: it already beats result
			}
		}
		return result;
	}

	public static String longestWordNoSkip(String s, List<String> list) {
		int[][] next = nextOccurrence(s); // BUILD once: s is fixed
		int n = s.length();
		String result = "";
		for (String word : list) {
			if (isSubSeq(word, next, n) && beats(word, result)) {
				result = word;            // RECORD: a match that beats result
			}
		}
		return result;
	}

	// next[i][c] = the smallest index j >= i with s.charAt(j) == 'a' + c,
	// or s.length() when that letter does not occur at or after i
	private static int[][] nextOccurrence(String s) {
		int n = s.length();
		int[][] next = new int[n + 1][ALPHABET];
		Arrays.fill(next[n], n);            // START: past the end, nothing occurs
		for (int i = n - 1; i >= 0; i--) {  // FILL right to left: i needs i + 1
			next[i] = next[i + 1].clone();  // COPY: all still reachable from i
			next[i][s.charAt(i) - 'a'] = i; // WRITE: and this one letter is closer
		}
		return next;
	}

	private static boolean isSubSeq(String word, int[][] next, int n) {
		int pos = 0;                                  // START: the front of s
		for (int i = 0; i < word.length(); i++) {
			int at = next[pos][word.charAt(i) - 'a']; // JUMP: one read finds the next match
			if (at == n) {
				return false;                         // STOP: this letter never appears again
			}
			pos = at + 1;                             // MOVE: continue strictly after what we consumed
		}
		return true;                                  // FOUND: every letter is placed
	}

	// longer wins; equal length is broken lexicographically, smallest first
	private static boolean beats(String word, String result) {
		int len = word.length();
		int max = result.length();
		return len > max || (len == max && word.compareTo(result) < 0);
	}

	// the same test read backwards, so a candidate that cannot win is
	// skipped BEFORE paying for the subsequence check
	private static boolean cannotWin(String word, String result) {
		return !beats(word, result);
	}

	public static void main(String[] args) {
		String s = "abpcplea";
		List<String> list = Arrays.asList("ale", "apple", "monkey", "plea");
		System.out.println(longestWord(s, list));       // apple
		System.out.println(longestWordNoSkip(s, list)); // apple

		List<String> list1 = Arrays.asList("a", "b", "c");
		System.out.println(longestWord(s, list1));       // a
		System.out.println(longestWordNoSkip(s, list1)); // a

		System.out.println("[%s]".formatted(longestWord("abc", List.of("zz")))); // []
		System.out.println("[%s]".formatted(longestWord("abc", List.of()))); // []
	}
}
