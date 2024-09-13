package com.svetanis.algorithms.string.palindrome;

import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.toLowerCase;

// a phrase is a palindrome if, after
// converting all uppercase letters
// into lowercase letters and removing
// all non-alphanumeric characters, it
// reads the same forward and backward.
// alphanumeric characters include 
// letters and numbers

public final class ValidPalindrome {
	// Time Complexity: O(n)

	public static boolean palindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			char c1 = str.charAt(left);
			char c2 = str.charAt(right);
			if (!isLetterOrDigit(c1)) {
				left++;
			} else if (!isLetterOrDigit(c2)) {
				right--;
			} else if (toLowerCase(c1) != toLowerCase(c2)) {
				return false;
			} else {
				left++;
				right--;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		String s1 = "A man, a plan, a canal: Panama";
		String s2 = "race a car";
		String s3 = "";
		System.out.println(palindrome(s1)); // true
		System.out.println(palindrome(s2)); // false
		System.out.println(palindrome(s3)); // true
	}
}
