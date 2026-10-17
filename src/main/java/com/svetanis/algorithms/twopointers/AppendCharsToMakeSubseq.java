package com.svetanis.algorithms.twopointers;

// 2486. Append Characters to String to Make Subsequence
//
// Input: two strings s and t of lowercase letters.
// Return: the fewest characters to append to the END of s so that t becomes a
// subsequence of it -- t's letters appear in s in the same order, not necessarily
// next to each other.
//
// The one idea: whatever is appended goes after everything in s, so it can only
// supply a TAIL of t. The answer is t's length minus the longest PREFIX of t that is
// already a subsequence of s. One scan of s finds that prefix: match t's next letter
// whenever it appears. Taking the earliest match never uses up a letter of s that a
// later match needed, because every later letter of s is still ahead of i.
//
// Siblings -- the same scan, a different last line:
//   string.SubsequenceIterative         -- LC 392: did j reach the end? (a boolean)
//   twopointers.LongestWordInDictionary -- LC 524: the same scan once per candidate word
//
// Time: O(n) -- every step moves i one letter along s (n = length of s).
// Space: O(1).

public final class AppendCharsToMakeSubseq {

	public static int appendChars(String s, String t) {
		int i = 0, j = 0;                 // START: i scans s, j is the next letter of t to find
		int n = s.length();
		int m = t.length();
		while(i < n && j < m) {           // STOP: s is used up, or all of t is found
			if(s.charAt(i) == t.charAt(j)) {
				j += 1;                   // MATCH: the next letter of t is found
			}
			i += 1;                       // MOVE: every letter of s is looked at once
		}
		return m - j;                     // RETURN: the unmatched TAIL of t is what must be appended
	}

	public static void main(String[] args) {
		System.out.println(appendChars("coaching", "coding")); // 4
		System.out.println(appendChars("abcde", "a"));         // 0
		System.out.println(appendChars("z", "abcde"));         // 5
		System.out.println(appendChars("lbg", "g"));           // 0
	}
}
