package com.svetanis.algorithms.math.conversion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 1271. Hexspeak

// Write the number in hexadecimal, turn 0 into O and 1 into I, and it is
// a word only if every character is one of A B C D E F I O -- any digit
// 2..9 left over makes it "ERROR". The two methods differ only in how they
// swap 0 and 1: String.replace on the whole string, or one char at a time.

public final class Hexspeak {
	// Time Complexity: O(log n), one character per hex digit
	// Space Complexity: O(log n)

	private static final Set<Character> LETTERS = new HashSet<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'I', 'O'));

	public static String hexspeakSimple(String s) {
		String hex = Long.toHexString(Long.valueOf(s)).toUpperCase();
		String replaced = hex.replace('0', 'O').replace('1', 'I');
		for (char c : replaced.toCharArray()) {
			if (!LETTERS.contains(c)) {
				return "ERROR";
			}
		}
		return replaced;
	}

	public static String hexspeak(String s) {
		long num = Long.parseLong(s);
		String hex = Long.toHexString(num).toUpperCase();
		char[] a = hex.toCharArray();
		for (int i = 0; i < a.length; i++) {
			char c = a[i];
			if (c == '1') {
				a[i] = 'I';
			} else if (c == '0') {
				a[i] = 'O';
			}
			if (!LETTERS.contains(a[i])) {
				return "ERROR";
			}
		}
		return new String(a);
	}

	public static void main(String[] args) {
		System.out.println(hexspeak("257")); // IOI
		System.out.println(hexspeak("3")); // ERROR
		System.out.println(hexspeakSimple("257")); // IOI
		System.out.println(hexspeakSimple("3")); // ERROR
	}
}