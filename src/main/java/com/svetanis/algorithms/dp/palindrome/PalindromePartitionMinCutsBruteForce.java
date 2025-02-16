package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Math.min;

// given a string, 
// cut it into pieces 
// such that each piece
// is a palindrome. 
// find minimum number of cuts.

public final class PalindromePartitionMinCutsBruteForce {
	// Time Complexity: O(2^n)

	public static int palindromePartition(String s) {
		if (isPalindrome(s)) {
			return 0;
		}
		return dfs(s, 0, s.length() - 1);
	}

	private static int dfs(String s, int left, int right) {
		if (left == right || isPalindrome(s)) {
			return 0;
		}
		int min = right - left;
		for (int index = left; index <= right; index++) {
			String prefix = s.substring(left, index + 1);
			// if a palindrome from left to index
			if (isPalindrome(prefix)) {
				// then cut here
				int cuts = 1 + dfs(s, index + 1, right);
				min = min(min, cuts);
			}
		}
		return min;
	}

	private static boolean isPalindrome(String s) {
		int left = 0;
		int right = s.length() - 1;
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
		System.out.println(palindromePartition("abdbca")); // 3
		System.out.println(palindromePartition("cddpd")); // 2
		System.out.println(palindromePartition("pqr")); // 2
		System.out.println(palindromePartition("pp")); // 0
	}
}
