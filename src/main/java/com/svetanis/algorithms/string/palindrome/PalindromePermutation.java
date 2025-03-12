package com.svetanis.algorithms.string.palindrome;

// 266. Palindrome Permutation

public final class PalindromePermutation {
	// Time Complexity: O(n)

	public static boolean canPermutePalindrome(String s) {
		int[] chars = count(s);
		int odd = 0;
		for (int count : chars) {
			if (count % 2 == 1) {
				odd++;
			}
		}
		return odd < 2;
	}

	private static int[] count(String s) {
		int[] chars = new int[26];
		for (char c : s.toCharArray()) {
			chars[c - 'a']++;
		}
		return chars;
	}

	public static void main(String[] args) {
		System.out.println(canPermutePalindrome("code")); // false
		System.out.println(canPermutePalindrome("tactcoa")); // true
	}
}
