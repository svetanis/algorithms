package com.svetanis.algorithms.string.encode;

import static java.lang.Character.isDigit;

// Run-length encoding, letter first (no LeetCode number)
//
// Input: a string with no digits in it -- a digit in the input could not be told apart from a
// count. encode writes each run of one repeated letter as the letter followed by its length:
// "aaab" -> "a3b1". decode turns that back into the original string. The empty string encodes and
// decodes to the empty string.
//
// The one idea: walk the string once and count how long the current run is. A run ends where a
// letter differs from the one before it; there, write the letter and then the count, and start
// counting the new run at 1. The last run has nothing after it to end it, so it is written after
// the loop. Decoding holds a letter while the digits after it are read; the next letter (or the
// end of the string) says the held letter's count is complete.
//
// Time: O(n) for encode; O(n + decoded length) for decode.
// Space: O(1) besides the output.
//
// Sibling: RunLengthEncodingCountFirst writes the count before its letter ("aaab" -> "3a1b"), so
// its decoder writes each letter the moment it reads it.

public final class RunLengthEncodingLetterFirst {

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
				sb.append(s.charAt(i - 1)); // RUN ENDS: its letter first,
				sb.append(count); // then its count
				count = 1; // NEW RUN: starts at this letter
			}
		}
		sb.append(s.charAt(n - 1)); // LAST RUN: nothing after it ended it
		sb.append(count);
		return sb.toString();
	}

	public static String decode(String s) {
		if (s.isEmpty()) {
			return ""; // BASE: no runs
		}
		char letter = s.charAt(0); // the held letter, whose count is being read
		int count = 0; // its count so far, digit by digit
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < s.length(); i++) {
			char c = s.charAt(i);
			if (isDigit(c)) {
				count = count * 10 + c - '0'; // DIGIT: one more digit of the held letter's count
			} else {
				append(sb, letter, count); // NEXT LETTER: the held letter's count is complete
				count = 0;
				letter = c; // HOLD: this letter until its count is read
			}
		}
		append(sb, letter, count); // END: the last letter's count is complete
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
		System.out.println("Encoded: " + encode(s1)); // w4a3d1e1x12
		System.out.println("Decoded: " + decode(encode(s1)));

		String s2 = "aaaabbbcc";
		System.out.println("Source: " + s2);
		System.out.println("Encoded: " + encode(s2)); // a4b3c2
		System.out.println("Decoded: " + decode(encode(s2)));

		System.out.println("Empty: [" + encode("") + "] [" + decode("") + "]"); // Empty: [] []
	}
}
