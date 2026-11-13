package com.svetanis.algorithms.string.palindrome;

// 266. Palindrome Permutation
//
// Input: a string of lowercase letters. On LeetCode: 1 <= length <= 5000.
// Return: whether some rearrangement of its letters is a palindrome.
//
// The one idea: a palindrome pairs every letter with its mirror, and only the middle
// of an odd length has no partner. So the letters can be arranged into one exactly when
// at most ONE letter appears an odd number of times. The order never matters, only the
// counts.
//
// Siblings -- the same odd-count test:
//   PalindromeReorder     -- builds the palindrome, uppercase letters
//   ConstructKPalindromes -- k palindromes instead of one: at most k odd counts
//
// Time: O(n) -- one counting pass, then 26 counts.
// Space: O(n) -- toCharArray copies the string; the counts are a fixed 26.

public final class PalindromePermutation {

	public static boolean canPermutePalindrome(String s) {
		int[] chars = count(s);
		int odd = 0;
		for (int count : chars) {
			if (count % 2 == 1) {
				odd++;                     // COUNT the letters left without a partner
			}
		}
		return odd < 2;                    // RETURN: at most one may sit in the middle
	}

	private static int[] count(String s) {
		int[] chars = new int[26];
		for (char c : s.toCharArray()) {
			chars[c - 'a']++;              // COUNT each letter
		}
		return chars;
	}

	public static void main(String[] args) {
		System.out.println(canPermutePalindrome("code"));    // false
		System.out.println(canPermutePalindrome("aab"));     // true
		System.out.println(canPermutePalindrome("carerac")); // true
		System.out.println(canPermutePalindrome("tactcoa")); // true
	}
}
