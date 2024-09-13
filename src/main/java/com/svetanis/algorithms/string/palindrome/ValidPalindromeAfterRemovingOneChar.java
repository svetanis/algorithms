package com.svetanis.algorithms.string.palindrome;

// find if a given string can be made into 
// a palindrome by removing at most one char

// palindrome is a word, phrase, number, or
// other sequences of chars which reads the
// same forward and backward, ignoring spaces
// punctuation, and capitalization

public final class ValidPalindromeAfterRemovingOneChar {
	// Time Complexity: O(n)

	public static boolean palindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				boolean one = isPalindrome(str, left + 1, right);
				boolean two = isPalindrome(str, left, right - 1);
				return one || two;
			}
			left++;
			right--;
		}
		return true;
	}

	private static boolean isPalindrome(String s, int left, int right) {
		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	public static void main(String[] args) {
		String s1 = "abca";
		String s2 = "racecars";
		System.out.println(palindrome(s1)); // true
		System.out.println(palindrome(s2)); // true
	}
}
