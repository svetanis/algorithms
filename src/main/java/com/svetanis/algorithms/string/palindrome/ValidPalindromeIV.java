package com.svetanis.algorithms.string.palindrome;

// 2330. Valid Palindrome IV

public final class ValidPalindromeIV {
	// Time Complexity: O(n)

	public static boolean makePalindrome(String s) {
		int left = 0;
		int right = s.length() - 1;
		int count = 0;
		while (left < right) {
			char c1 = s.charAt(left);
			char c2 = s.charAt(right);
			if (c1 != c2) {
				count += 1;
				if(count > 2) {
					return false;
				}
			}
			left++;
			right--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(makePalindrome("abcdba")); // true
		System.out.println(makePalindrome("aa")); // true
		System.out.println(makePalindrome("abcdef")); // false
	}
}
