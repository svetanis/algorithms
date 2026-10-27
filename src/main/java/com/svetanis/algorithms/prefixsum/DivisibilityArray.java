package com.svetanis.algorithms.prefixsum;

// 2575. Find the Divisibility Array of a String
//
// Input: a string word of n digits and a positive integer m. Returns an array whose entry i is 1
// when the number written by word[0 .. i] is divisible by m, and 0 otherwise. On LeetCode:
// 1 <= n <= 10^5, word holds the digits 0 to 9, 1 <= m <= 10^9.
//
// The one idea: the number written by word[0 .. i] can have 10^5 digits, far too many for any
// numeric type, but only its remainder after division by m matters. Appending a digit d turns the
// number x into 10 * x + d, and the remainder of that depends only on the remainder of x. So num
// holds the remainder of the prefix so far, always below m, and one step is
// num = (num * 10 + d) % m. The prefix is divisible exactly when num is 0.
//
// Time: O(n) -- one step per digit
// Space: O(n) -- the output array; one variable besides it
//
// num is a long: num * 10 + 9 can reach about 10^10 when m is near 10^9, past the int range.

public final class DivisibilityArray {

	public static int[] divisibilityArray(String word, int m) {
		long num = 0; // REMAINDER of the prefix so far, below m
		int n = word.length();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			num = (num * 10 + (word.charAt(i) - '0')) % m; // APPEND a digit, keep the remainder
			a[i] = num == 0 ? 1 : 0; // RECORD: divisible when the remainder is 0
		}
		return a;
	}

	private static void print(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int value : a) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		print(divisibilityArray("998244353", 3)); // 1 1 0 0 0 1 1 0 0
		print(divisibilityArray("1010", 10)); // 0 1 0 1
	}
}
