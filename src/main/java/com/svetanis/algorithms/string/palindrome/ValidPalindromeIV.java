package com.svetanis.algorithms.string.palindrome;

// 2330. Valid Palindrome IV
//
// Input: a string of lowercase letters. On LeetCode: 1 <= length <= 10^5.
// Return: whether it can be made a palindrome with EXACTLY one or two operations, one
// operation being a change of a single character to a different character.
//
// The one idea: count the mirrored pairs that disagree. Each one needs one change --
// either end can be set to the other -- so at most two of them can be repaired. Zero
// disagreeing pairs is fine too: the operations can be spent without breaking the
// palindrome (change the middle of an odd length, or change both ends of one pair to
// the same new letter).
//
// Siblings:
//   ValidPalindromeAfterRemovingOneChar -- LC 680: a DELETION shifts the pairs after it, so that one must branch
//
// Time: O(n) -- one pass, stopping early at the third mismatch.
// Space: O(1).

public final class ValidPalindromeIV {

	public static boolean makePalindrome(String s) {
		int left = 0;                                    // START at both ends
		int right = s.length() - 1;
		int count = 0;
		while (left < right) {                           // STOP when they meet
			char c1 = s.charAt(left);
			char c2 = s.charAt(right);
			if (c1 != c2) {
				count += 1;                              // MISMATCH: costs one operation
				if (count > 2) {
					return false;                        // RETURN false: a third change is not allowed
				}
			}
			left++;                                      // MOVE both inward
			right--;
		}
		return true;                                     // RETURN true: two mismatches at most
	}

	public static void main(String[] args) {
		System.out.println(makePalindrome("abcdba")); // true
		System.out.println(makePalindrome("aa"));     // true
		System.out.println(makePalindrome("abcdef")); // false
	}
}
