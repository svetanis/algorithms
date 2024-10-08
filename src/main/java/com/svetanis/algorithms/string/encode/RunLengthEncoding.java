package com.svetanis.algorithms.string.encode;

import static java.lang.Character.isDigit;

public final class RunLengthEncoding {

	public static String encode(String s) {
		int count = 1;
		int n = s.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n; ++i) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				++count;
			} else {
				sb.append(count);
				sb.append(s.charAt(i - 1));
				count = 1;
			}
		}
		sb.append(count);
		sb.append(s.charAt(n - 1));
		return sb.toString();
	}

	public static String decode(String s) {
		int count = 0;
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (isDigit(c)) {
				count = count * 10 + c - '0';
			} else {
				append(sb, c, count);
				count = 0;
			}
		}
		return sb.toString();
	}

	private static void append(StringBuilder sb, char prev, int count) {
		for (int j = 0; j < count; j++) {
			sb.append(prev);
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

	}
}
