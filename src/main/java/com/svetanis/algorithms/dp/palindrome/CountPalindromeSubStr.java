package com.svetanis.algorithms.dp.palindrome;

// 647. Palindromic Substrings

public final class CountPalindromeSubStr {
	// Time Complexity: O(n^3)
	// Space Space O(1)

	public static int lps(String s) {
		int count = 0;
		int n = s.length();
		for (int start = 0; start < n; start++) {
			for (int end = start; end < n; end++) {
				count += isPalindrome(s, start, end) ? 1 : 0;
			}
		}
		return count;
	}

	private static boolean isPalindrome(String s, int start, int end) {
		while (start < end) {
			if (s.charAt(start) != s.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 7
		System.out.println(lps("cddpd")); // 7
		System.out.println(lps("pqr")); // 7
		System.out.println(lps("abc")); // 3
		System.out.println(lps("aaa")); // 6
	}
}