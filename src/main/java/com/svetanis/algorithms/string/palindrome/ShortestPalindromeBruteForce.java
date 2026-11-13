package com.svetanis.algorithms.string.palindrome;

// 214. Shortest Palindrome
//
// Input: a string of lowercase letters, possibly empty. On LeetCode: 0 <= length <= 5 * 10^4.
// Return: the shortest palindrome made by adding characters only in FRONT of it.
//
// The one idea: whatever is added in front must mirror the end of s, so the answer
// keeps the longest PREFIX of s that is already a palindrome and puts the rest,
// reversed, in front. With r = s reversed and left = n - right, the prefix s[0, right)
// is a palindrome exactly when it equals r[left, n), and then r[0, left) -- the last
// left characters of s, reversed -- is what goes in front. Prefixes are tried longest
// first, down to a single character, which is always a palindrome; so the loop runs
// while right > 0, and only the empty string ever leaves it.
//
// Siblings -- the same problem, the longest palindromic prefix found in O(n):
//   todo.string.ShortestPalindromeKmp         -- the KMP failure table of s + "#" + r
//   todo.string.ShortestPalindromeRollingHash -- a forward and a backward hash of each prefix
//
// Time: O(n^2) -- up to n prefixes, each cut and compared in O(n).
// Space: O(n) for the reversed string and the substrings.

public final class ShortestPalindromeBruteForce {

	public static String palindrome(String s) {
		int left = 0;                                // START with the whole string as the prefix
		int right = s.length();
		StringBuilder sb = new StringBuilder(s);
		String r = sb.reverse().toString();
		while (right > 0) {                          // STOP after the one-character prefix
			String s1 = s.substring(0, right);
			String s2 = r.substring(left);           // s1 reversed
			if (s1.equals(s2)) {                     // COMPARE: is the prefix a palindrome
				return r.substring(0, left) + s;     // RETURN: the reversed rest goes in front
			}
			left++;                                  // MOVE: a prefix one character shorter
			right--;
		}
		return s;                                    // RETURN: only the empty string gets here
	}

	public static void main(String[] args) {
		System.out.println(palindrome("aacecaaa")); // aaacecaaa
		System.out.println(palindrome("abcd"));     // dcbabcd
		System.out.println(palindrome("ab"));       // bab
		System.out.println(palindrome(""));         // (empty line)
	}
}
