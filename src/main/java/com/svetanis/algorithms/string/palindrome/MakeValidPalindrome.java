package com.svetanis.algorithms.string.palindrome;

// given a string of lowercase english letters
// find if the string can be turned into a
// palindrome through exactly one or two operations
// operation is defined as changing any single
// character in the string to any other character

// palindrome is a word, phrase, number, or
// other sequences of chars which reads the
// same forward and backward, ignoring spaces
// punctuation, and capitalization

public final class MakeValidPalindrome {
	// Time Complexity: O(n)

	public static boolean palindrome(String str) {
		int count = 0;
		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				count++;
				if (count > 2) {
					return false;
				}
			}
			left++;
			right--;
		}
		return true;
	}

	public static void main(String[] args) {
		String s1 = "racebcr";
		String s2 = "racecars";
		System.out.println(palindrome(s1)); // true
		System.out.println(palindrome(s2)); // true
	}
}
