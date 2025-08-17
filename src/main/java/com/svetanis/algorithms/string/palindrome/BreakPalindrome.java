package com.svetanis.algorithms.string.palindrome;

// 1328. Break a Palindrome

public final class BreakPalindrome {
	// Time Complexity: O(n)

	public static String breakPalindrome(String s) {
		int n = s.length();
		if (n == 1) {
			return "";
		}
		char[] chars = s.toCharArray();
		int index = 0;
		while (index < n / 2 && chars[index] == 'a') {
			index++;
		}
		if (index == n / 2) {
			chars[n - 1] = 'b';
		} else {
			chars[index] = 'a';
		}
		return new String(chars);
	}

	public static void main(String[] args) {
		System.out.println(breakPalindrome("abccba")); // aaccba
		System.out.println(breakPalindrome("a")); //
	}
}
