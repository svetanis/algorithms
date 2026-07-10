package com.svetanis.algorithms.math.conversion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 1271. Hexspeak

public final class Hexspeak {

	public static String hexspeakSimple(String s) {
		String hex = Long.toHexString(Long.valueOf(s)).toUpperCase();
		String replaced = hex.replace('0', 'O').replace('1', 'I');
		Set<Character> set = new HashSet<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'I', 'O'));
		for (char c : replaced.toCharArray()) {
			if (!set.contains(c)) {
				return "ERROR";
			}
		}
		return replaced;
	}

	public static String hexspeak(String s) {
		long num = Long.parseLong(s);
		String hex = Long.toHexString(num).toUpperCase();
		char[] a = hex.toCharArray();
		Set<Character> set = new HashSet<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'I', 'O'));
		for (int i = 0; i < a.length; i++) {
			char c = a[i];
			if (c == '1') {
				a[i] = 'I';
			} else if (c == '0') {
				a[i] = 'O';
			}
			if (!set.contains(a[i])) {
				return "ERROR";
			}
		}
		return new String(a);
	}

	public static void main(String[] args) {
		System.out.println(hexspeak("257")); // IOI
		System.out.println(hexspeak("3")); // error
	}
}