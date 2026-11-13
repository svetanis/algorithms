package com.svetanis.algorithms.string.palindrome;

// 1400. Construct K Palindrome Strings
//
// Input: a string of lowercase letters and an integer k. On LeetCode: 1 <= length <= 10^5,
// 1 <= k <= 10^5.
// Return: whether ALL the characters of s can be used to build exactly k non-empty
// palindromes.
//
// The one idea: only the letters with an ODD count matter. A palindrome holds its
// letters in mirrored pairs plus at most one unpaired letter in the middle, so each
// letter with an odd count needs a palindrome of its own to sit in: at least `odd`
// palindromes. The pairs can go anywhere, and splitting a pair into two one-letter
// palindromes adds one more, so every k from `odd` up to the length of s works. Above
// the length there are not enough letters for k non-empty strings.
//
// Siblings -- the same odd-count test:
//   PalindromePermutation -- one palindrome: at most one odd count
//   PalindromeReorder     -- builds that one palindrome
//
// Time: O(n) -- one counting pass, then 26 counts.
// Space: O(n) -- toCharArray copies the string; the counts are a fixed 26.

public final class ConstructKPalindromes {

	public static boolean canConstruct(String s, int k) {
		if (s.length() < k) {
			return false;                    // RETURN false: fewer letters than palindromes
		}
		int[] counts = new int[26];
		for (char c : s.toCharArray()) {
			counts[c - 'a']++;               // COUNT each letter
		}
		int odd = 0;
		for (int count : counts) {
			odd += (count % 2 == 1) ? 1 : 0; // COUNT the letters left without a partner
		}
		return odd <= k;                     // RETURN: each of them needs its own middle
	}

	public static void main(String[] args) {
		System.out.println(canConstruct("annabelle", 2)); // true
		System.out.println(canConstruct("leetcode", 3));  // false
		System.out.println(canConstruct("true", 4));      // true
	}
}
