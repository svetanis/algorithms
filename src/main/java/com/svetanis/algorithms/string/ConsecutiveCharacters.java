package com.svetanis.algorithms.string;

// 1446. Consecutive Characters
// CSES 1069. Repetitions is the same task on DNA letters and longer strings.
//
// Input: a string s. Returns the length of the longest run of one repeated letter. On LeetCode:
// 1 <= s.length <= 500, lowercase letters. On CSES: 1 <= s.length <= 10^6, the letters A, C, G, T.
// An empty string (outside both limits) returns 1.
//
// The one idea: a run ends where the letter changes, so keep only the length of the run that
// ends at the current character. count holds that length: it grows by 1 when the letter equals
// the one before it, and restarts at 1 when it differs. The answer is the largest count ever
// seen.
//
// Time: O(n) -- one pass.
// Space: O(1).

public final class ConsecutiveCharacters {

	public static int longestRepetition(String s) {
		int max = 1;
		int count = 1; // count: length of the run ending at s[i]
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				count += 1; // EXTEND: same letter as before
			} else {
				count = 1; // RESTART: a new run begins here
			}
			max = Math.max(max, count); // RECORD: the best run so far
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(longestRepetition("leetcode")); // 2
		System.out.println(longestRepetition("abbcccddddeeeeedcba")); // 5
		System.out.println(longestRepetition("ATTCGGGA")); // 3
	}
}
