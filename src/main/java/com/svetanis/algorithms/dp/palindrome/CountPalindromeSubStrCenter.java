package com.svetanis.algorithms.dp.palindrome;

// 647. Palindromic Substrings

public final class CountPalindromeSubStrCenter {
	// Time Complexity: O(n^2)
	// Auxiliary Space O(1)

	public static int lps(String s) {
		int n = s.length();
		int count = 0;
		if (n == 0) {
			return 0;
		}
		for (int i = 0; i < n; i++) {
			// odd-length palindromes, single char center
			count += countPalindrome(s, i, i);
			// even-length palindromes, consecutive char center
			count += countPalindrome(s, i, i + 1);
		}
		return count;
	}

	private static int countPalindrome(String s, int low, int high) {
		int count = 0;
		while (low >= 0 && high < s.length()) {
			if (s.charAt(low) != s.charAt(high)) {
				break;
			}
			low--;
			high++;
			count += 1;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(lps("abdbca")); // 7
		System.out.println(lps("cddpd")); // 7
		System.out.println(lps("pqr")); // 3
		System.out.println(lps("abc")); // 3
		System.out.println(lps("aaa")); // 6
	}
}