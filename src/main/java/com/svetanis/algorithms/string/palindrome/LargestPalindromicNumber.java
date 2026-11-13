package com.svetanis.algorithms.string.palindrome;

import java.util.Arrays;

// 2384. Largest Palindromic Number
//
// Input: a string of digits. On LeetCode: 1 <= length <= 10^5.
// Return: the largest palindromic number, as a string, built from some of those digits
// -- at least one, reordered freely -- with no leading zeros.
//
// The one idea: a longer number is larger, and among equal lengths the larger leading
// digit wins. So the left half is built greedily from 9 down to 0, using every PAIR of
// each digit (a pair puts one copy in each half), and the largest digit left without a
// partner goes in the middle. Zeros are the exception: a pair of zeros may only go in
// once a larger digit already leads the left half, or the number would start with 0.
// When nothing but zeros is usable the answer is "0".
//
// Time: O(n) -- one counting pass, then at most n / 2 digits written to each half.
// Space: O(n) for the answer.

public final class LargestPalindromicNumber {

	public static String palindrome(String s) {
		int[] counts = counts(s);
		String middle = "";
		StringBuilder sb = new StringBuilder();                    // the left half
		for (int i = 9; i >= 0; i--) {                             // START at the largest digit
			int f = counts[i];
			char digit = (char) (i + '0');
			if (f > 1) {
				if (i > 0 || (i == 0 && sb.length() > 0)) {        // SKIP zeros that would lead
					char[] chars = new char[f / 2];
					Arrays.fill(chars, digit);
					sb.append(new String(chars));                  // PAIR: half the copies go left
				}
				counts[i] = f % 2;                                 // the copy left without a partner
			}

			if (counts[i] == 1 && middle.isEmpty()) {
				middle = Character.toString(digit);                // MIDDLE: the largest unpaired digit
				counts[i]--;
			}
		}
		String p = sb.toString() + middle + sb.reverse().toString(); // MIRROR the left half
		if (p.isEmpty() || p.replace("0", "").isEmpty()) {
			return "0";                                            // RETURN "0": only zeros were usable
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
		System.out.println(palindrome("00009"));     // 9
		System.out.println(palindrome("00"));        // 0
	}
}
