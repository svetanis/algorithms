package com.svetanis.algorithms.dp.palindrome;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;

// 1312. Minimum Insertion Steps to Make a String Palindrome

public final class MinInsertionsToMakePalindromeRecursive {

	public static int palindrome(String str) {
		int n = str.length();
		return dfs(str, 0, n - 1);
	}

	private static int dfs(String str, int low, int high) {
		if (low > high) {
			return MAX_VALUE;
		}
		if (low == high) {
			return 0;
		}
		if (low == high - 1) {
			return (str.charAt(low) == str.charAt(high) ? 0 : 1);
		}

		// check if the first and last chars are same
		if (str.charAt(low) == str.charAt(high)) {
			return dfs(str, low + 1, high - 1);
		}
		int left = dfs(str, low, high - 1);
		int right = dfs(str, low + 1, high);
		return 1 + min(left, right);
	}

	public static void main(String[] args) {
		System.out.println(palindrome("zzazz")); // 0
		System.out.println(palindrome("mbadm")); // 2
		System.out.println(palindrome("leetcode")); // 5
		System.out.println(palindrome("geeks")); // 3
	}
}