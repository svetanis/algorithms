package com.svetanis.algorithms.string.palindrome;

import java.util.ArrayList;
import java.util.List;

// 906. Super Palindromes

public final class SuperPalindromes {
	// Time Complexity: O(n log n)
	// Space Complexity: O(1)

	public static int superPalindromeInRange(String left, String right) {
		long start = Long.parseLong(left);
		long end = Long.parseLong(right);
		int count = 0;
		List<Long> palindromes = palindromes();
		for (long palindrome : palindromes) {
			long square = palindrome * palindrome;
			boolean in = square >= start && square <= end;
			if (in && isPalindrome(square)) {
				count++;
			}
		}
		return count;
	}

	private static boolean isPalindrome(long num) {
		long reversed = 0;
		long temp = num;
		while (temp > 0) {
			reversed = reversed * 10 + temp % 10;
			temp /= 10;
		}
		return num == reversed;
	}

	private static List<Long> palindromes() {
		List<Long> list = new ArrayList<>();
		for (int num = 1; num < 100000; num++) {
			String s = Integer.toString(num);
			String reverse = new StringBuilder(s).reverse().toString();
			String ss = s.substring(0, s.length() - 1);
			String ssReverse = new StringBuilder(ss).reverse().toString();
			list.add(Long.parseLong(s + reverse));
			list.add(Long.parseLong(s + ssReverse));
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(superPalindromeInRange("4", "1000")); // 4
		System.out.println(superPalindromeInRange("1", "2")); // 1
	}
}
