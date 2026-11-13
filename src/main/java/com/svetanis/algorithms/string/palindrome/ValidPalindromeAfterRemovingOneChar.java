package com.svetanis.algorithms.string.palindrome;

// 680. Valid Palindrome II
//
// Input: a string of lowercase letters, at least one. On LeetCode: 1 <= length <= 10^5.
// Return: whether it can be made a palindrome by deleting AT MOST one character.
//
// The one idea: walk both ends inward while they match -- those pairs are settled
// whatever happens later. At the first mismatch the one deletion is spent on s[left]
// or s[right]: one of the two has no partner, and deleting anything else leaves the
// pair as it is. After that the budget is gone, so the rest must be a palindrome as it
// is: s[left+1..right] without the left character, or s[left..right-1] without the
// right one. Either branch passing is enough (||). The two characters cannot tell
// which deletion is right, so both are tried, each once.
//
// Siblings:
//   ValidPalindromeIV -- LC 2330: CHANGE up to two characters; a change keeps the pairs lined up, so a count is enough
//
// Time: O(n) -- the outer walk, then at most two walks over the unsettled middle.
// Space: O(1).

public final class ValidPalindromeAfterRemovingOneChar {

	public static boolean palindrome(String str) {
		int left = 0;                                             // START at both ends
		int right = str.length() - 1;
		while (left < right) {                                    // STOP when they meet: nothing to delete
			if (str.charAt(left) != str.charAt(right)) {          // MISMATCH: the one deletion is spent here
				boolean one = isPalindrome(str, left + 1, right); // SPLIT: delete the LEFT character
				boolean two = isPalindrome(str, left, right - 1); // SPLIT: delete the RIGHT character
				return one || two;                                // RETURN: either branch is enough
			}
			left++;                                               // MATCH: MOVE both inward
			right--;
		}
		return true;
	}

	private static boolean isPalindrome(String s, int left, int right) {
		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;                                     // MISMATCH: no deletion left
			}
			left++;                                               // MATCH: MOVE both inward
			right--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(palindrome("aba"));      // true
		System.out.println(palindrome("abca"));     // true
		System.out.println(palindrome("abc"));      // false
		System.out.println(palindrome("racecars")); // true
	}
}
