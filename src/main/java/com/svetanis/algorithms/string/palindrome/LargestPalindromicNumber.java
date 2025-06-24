package com.svetanis.algorithms.string.palindrome;

import java.util.Arrays;

// 2384. Largest Palindromic Number

public final class LargestPalindromicNumber {
	// Time Complexity: O(n)
	// Time Complexity: O(n)

	public static String palindrome(String s) {
		int[] counts = counts(s);
		String middle = "";
		StringBuilder sb = new StringBuilder();
		for (int i = 9; i >= 0; i--) {
			int f = counts[i];
			char digit = (char) (i + '0');
			if (f > 1) {
				if (i > 0 || (i == 0 && sb.length() > 0)) {
					char[] chars = new char[f / 2];
					Arrays.fill(chars, digit);
					sb.append(new String(chars));
				}
				counts[i] = f % 2;
			}

			if (counts[i] == 1 && middle.isEmpty()) {
				middle = Character.toString(digit);
				counts[i]--;
			}
		}
		String p = sb.toString() + middle + sb.reverse().toString();
		if (p.isEmpty() || p.replace("0", "").isEmpty()) {
			return "0";
		}
		return p;
	}

	private static int[] counts(String s) {
		int[] counts = new int[10];
		for (char c : s.toCharArray()) {
			counts[c - '0']++;
		}
		return counts;
	}

	public static void main(String[] args) {
		System.out.println(palindrome("444947137")); // 7449447
		System.out.println(palindrome("00009")); // 9
	}
}
