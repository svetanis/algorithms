package com.svetanis.algorithms.string.palindrome;

import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.toLowerCase;
import static java.util.stream.Collectors.toList;

import java.util.List;

// 125. Valid Palindrome
//
// Input: a string of printable ASCII characters. On LeetCode: 1 <= length <= 2 * 10^5.
// Return: whether it reads the same forwards and backwards once every uppercase letter
// is lowercased and every character that is not a letter or a digit is removed.
//
// The one idea: do the removing and the lowercasing FIRST, into a list, so the walk
// from both ends meets only characters that take part and the loop needs just two
// branches. The list holds Character objects, so each pair is unboxed into char before
// it is compared.
//
// Siblings -- the same problem:
//   ValidPalindromeSimple -- no list: skips the other characters in place, O(1) space
//
// Time: O(n) -- one pass to filter, one walk over what is left.
// Space: O(n) for the filtered list.

public final class ValidPalindrome {

	public static boolean palindrome(String s) {
		List<Character> list = s.codePoints()
				.mapToObj(c -> (char) c)
				.filter(c -> isLetterOrDigit(c))         // SKIP all but letters and digits
				.map(c -> toLowerCase(c))                // LOWER the case
				.collect(toList());
		int left = 0;                                    // START at both ends
		int right = list.size() - 1;
		while (left < right) {                           // STOP when they meet
			// unbox before comparing: != on two Characters compares
			// references, which only works inside the 0..127 cache
			char c1 = list.get(left);
			char c2 = list.get(right);
			if (c1 != c2) {
				return false;                            // MISMATCH
			} else {
				left++;                                  // MATCH: MOVE both inward
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
