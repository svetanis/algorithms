package com.svetanis.algorithms.string.encode;

import static java.lang.Character.isDigit;

// Run-length encoding, count first (no LeetCode number)
//
// Input: a string with no digits in it -- a digit in the input could not be told apart from a
// count. encode writes each run of one repeated letter as its length followed by the letter:
// "aaab" -> "3a1b". decode turns that back into the original string. The empty string encodes and
// decodes to the empty string.
//
// The one idea: walk the string once and count how long the current run is. A run ends where a
// letter differs from the one before it; there, write the count and then the letter, and start
// counting the new run at 1. The last run has nothing after it to end it, so it is written after
// the loop. Decoding reads the digits of a count, and the letter that follows them ends the count.
//
// Time: O(n) for encode; O(n + decoded length) for decode.
// Space: O(1) besides the output.
//
// Sibling: RunLengthEncodingLetterFirst writes the letter before its count ("aaab" -> "a3b1"), so
// its decoder holds each letter until the count after it has been read.

public final class RunLengthEncodingCountFirst {

	public static String encode(String s) {
		if (s.isEmpty()) {
			return ""; // BASE: no runs
		}
		int count = 1; // the length of the current run
		int n = s.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n; ++i) { // WALK: each letter against the one before it
			if (s.charAt(i) == s.charAt(i - 1)) {
				++count; // SAME RUN: one longer
			} else {
				sb.append(count); // RUN ENDS: its count first,
				sb.append(s.charAt(i - 1)); // then its letter
				count = 1; // NEW RUN: starts at this letter
			}
		}
		sb.append(count); // LAST RUN: nothing after it ended it
		sb.append(s.charAt(n - 1));
		return sb.toString();
	}

	public static String decode(String s) {
		int count = 0; // the count read so far, digit by digit
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (isDigit(c)) {
				count = count * 10 + c - '0'; // DIGIT: one more digit of the count
			} else {
				append(sb, c, count); // LETTER: ends its count; write it that many times
				count = 0;
			}
		}
		return sb.toString();
	}

	private static void append(StringBuilder sb, char letter, int count) {
		for (int j = 0; j < count; j++) {
			sb.append(letter);
		}
	}

	public static void main(String[] args) {
		String s1 = "wwwwaaadexxxxxxxxxxxx";
		System.out.println("Source: " + s1);
		System.out.println("Encoded: " + encode(s1)); // 4w3a1d1e12x
		System.out.println("Decoded: " + decode(encode(s1)));

		String s2 = "aaaabbbcc";
		System.out.println("Source: " + s2);
		System.out.println("Encoded: " + encode(s2)); // 4a3b2c
		System.out.println("Decoded: " + decode(encode(s2)));

		System.out.println("Empty: [" + encode("") + "] [" + decode("") + "]"); // Empty: [] []
	}
}
