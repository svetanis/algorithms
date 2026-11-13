package com.svetanis.algorithms.string.palindrome;

// 1328. Break a Palindrome
//
// Input: a palindrome of lowercase letters. On LeetCode: 1 <= length <= 1000.
// Return: the lexicographically smallest string that is NOT a palindrome, made by
// replacing exactly one character with a different lowercase letter; "" when no
// replacement can break it.
//
// The one idea: the earliest position that can be made smaller wins. Writing 'a' over
// the first non-'a' in the left half is the smallest change there is, and it breaks the
// palindrome because its mirror is untouched. The middle of an odd length is never
// used: it is its own mirror, so changing it leaves a palindrome -- which is also why
// a single character cannot be broken. If the whole left half is already 'a', nothing
// can get smaller, so the cheapest damage is at the far end: the last character
// becomes 'b'.
//
// Time: O(n) -- one pass over the left half, one copy.
// Space: O(n) for the char array; a String cannot be changed in place.

public final class BreakPalindrome {

	public static String breakPalindrome(String s) {
		int n = s.length();
		if (n == 1) {
			return "";                                 // RETURN: the only character is the middle
		}
		char[] chars = s.toCharArray();
		int index = 0;
		while (index < n / 2 && chars[index] == 'a') { // SKIP the 'a's; STOP before the middle
			index++;
		}
		if (index == n / 2) {
			chars[n - 1] = 'b';                        // RAISE the last: the left half is all 'a'
		} else {
			chars[index] = 'a';                        // LOWER the first non-'a' of the left half
		}
		return new String(chars);
	}

	public static void main(String[] args) {
		System.out.println(breakPalindrome("abccba")); // aaccba
		System.out.println(breakPalindrome("a"));      // (empty line)
		System.out.println(breakPalindrome("aa"));     // ab
		System.out.println(breakPalindrome("aba"));    // abb
	}
}
