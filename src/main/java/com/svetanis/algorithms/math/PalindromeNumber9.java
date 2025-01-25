package com.svetanis.algorithms.math;

// 9. Palindrome Number

public final class PalindromeNumber9 {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static boolean palindrome(int num) {
		// negative num can't be palindrome
		// if last digit is zero, num can't be palindrome
		if (num < 0 || (num % 10 == 0 && num > 0)) {
			return false;
		}

		int reverse = 0;
		int original = num;
		while (num != 0) {
			int remainder = num % 10;
			reverse = reverse * 10 + remainder;
			num = num / 10;
		}
		return original == reverse;
	}

	public static void main(String[] args) {
		System.out.println(palindrome(121)); // true
		System.out.println(palindrome(-121)); // false
		System.out.println(palindrome(10)); // false
	}
}