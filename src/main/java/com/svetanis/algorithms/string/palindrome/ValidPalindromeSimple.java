package com.svetanis.algorithms.string.palindrome;

import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.toLowerCase;

// 125. Valid Palindrome
//
// Input: a string of printable ASCII characters. On LeetCode: 1 <= length <= 2 * 10^5.
// Return: whether it reads the same forwards and backwards once every uppercase letter
// is lowercased and every character that is not a letter or a digit is removed.
//
// The one idea: do the removing during the walk, with no copy. Each step looks at both
// ends; an end that does not take part moves inward ALONE and the loop tests again from
// the top, so a punctuation mark is never compared against a letter. Only when both
// ends are letters or digits are they compared, ignoring case, and only a match moves
// BOTH inward.
//
// Siblings -- the same problem:
//   ValidPalindrome -- filters and lowercases into a list first, then a two-branch walk; O(n) space
//
// Time: O(n) -- every step moves at least one end inward.
// Space: O(1).

public final class ValidPalindromeSimple {

	public static boolean palindrome(String s) {
		int left = 0;                                    // START at both ends
		int right = s.length() - 1;
		while (left < right) {                           // STOP when they meet
			char c1 = s.charAt(left);
			char c2 = s.charAt(right);
			if (!isLetterOrDigit(c1)) {
				left++;                                  // SKIP: advance ONE pointer, re-test
			} else if (!isLetterOrDigit(c2)) {
				right--;                                 // SKIP: the right end alone
			} else if (toLowerCase(c1) != toLowerCase(c2)) {
				return false;                            // MISMATCH, ignoring case
			} else {
				left++;                                  // MATCH: only here do BOTH move
				right--;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String s1 = "A man, a plan, a canal: Panama";
		String s2 = "race a car";
		String s3 = " ";
		System.out.println(palindrome(s1)); // true
		System.out.println(palindrome(s2)); // false
		System.out.println(palindrome(s3)); // true
	}
}
