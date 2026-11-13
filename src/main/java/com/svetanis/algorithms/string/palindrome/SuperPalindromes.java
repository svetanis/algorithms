package com.svetanis.algorithms.string.palindrome;

import java.util.ArrayList;
import java.util.List;

// 906. Super Palindromes
//
// Input: two positive integers left <= right, as strings. On LeetCode: 1 <= left <= right
// <= 10^18 - 1.
// Return: how many numbers in [left, right] are super-palindromes: palindromes that are
// also the square of a palindrome.
//
// The one idea: generate the square ROOTS, not the squares. A square below 10^18 has a
// root below 10^9, and a palindromic root is fixed by its first half, so every half s
// from 1 to 99999 gives two roots: s + reverse(s), even length, and s + reverse(s
// without its last digit), odd length. That is under 2 * 10^5 roots however wide the
// range. Each root is squared, and a square is counted when it is in the range and is a
// palindrome itself. Roots of 10 digits are skipped: their squares are 10^18 or more,
// and a long cannot hold every one of them.
//
// Time: O(10^5 * d) -- under 2 * 10^5 roots, each built and its square tested in O(d),
// d <= 18 digits; the width of the range never enters.
// Space: O(10^5) for the list of roots.

public final class SuperPalindromes {

	public static int superPalindromeInRange(String left, String right) {
		long start = Long.parseLong(left);
		long end = Long.parseLong(right);
		int count = 0;
		List<Long> palindromes = palindromes();
		for (long palindrome : palindromes) {
			if (palindrome >= 1_000_000_000L) {
				continue;                                  // SKIP: the square is past any right
			}
			long square = palindrome * palindrome;
			boolean in = square >= start && square <= end;
			if (in && isPalindrome(square)) {
				count++;                                   // COUNT a palindrome square in range
			}
		}
		return count;
	}

	private static boolean isPalindrome(long num) {
		long reversed = 0;
		long temp = num;
		while (temp > 0) {
			reversed = reversed * 10 + temp % 10;          // MOVE the last digit onto reversed
			temp /= 10;
		}
		return num == reversed;
	}

	private static List<Long> palindromes() {
		List<Long> list = new ArrayList<>();
		for (int num = 1; num < 100000; num++) {           // the first half of a root
			String s = Integer.toString(num);
			String reverse = new StringBuilder(s).reverse().toString();
			String ss = s.substring(0, s.length() - 1);
			String ssReverse = new StringBuilder(ss).reverse().toString();
			list.add(Long.parseLong(s + reverse));         // RECORD the even-length root
			list.add(Long.parseLong(s + ssReverse));       // RECORD the odd-length root
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(superPalindromeInRange("4", "1000")); // 4
		System.out.println(superPalindromeInRange("1", "2"));    // 1
	}
}
