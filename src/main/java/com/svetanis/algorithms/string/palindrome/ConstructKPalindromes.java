package com.svetanis.algorithms.string.palindrome;

// 1400. Construct K Palindrome Strings

public final class ConstructKPalindromes {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static boolean canConstruct(String s, int k) {
		if (s.length() < k) {
			return false;
		}
		int[] counts = new int[26];
		for (char c : s.toCharArray()) {
			counts[c - 'a']++;
		}
		int odd = 0;
		for (int count : counts) {
			odd += (count % 2 == 1) ? 1 : 0;
		}
		return odd <= k;
	}

	public static void main(String[] args) {
		System.out.println(canConstruct("annabelle", 2)); // true
		System.out.println(canConstruct("leetcode", 3)); // false
		System.out.println(canConstruct("true", 4)); // true
	}
}
