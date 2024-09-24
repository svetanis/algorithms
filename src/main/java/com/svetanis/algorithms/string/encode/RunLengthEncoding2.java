package com.svetanis.algorithms.string.encode;

import static java.lang.Character.isDigit;

public final class RunLengthEncoding2 {

	public static String encode(String s) {
		int count = 1;
		int n = s.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n; ++i) {
			if (s.charAt(i) == s.charAt(i - 1)) {
				++count;
			} else {
				sb.append(s.charAt(i - 1));
				sb.append(count);
				count = 1;
			}
		}
		sb.append(s.charAt(n - 1));
		sb.append(count);
		return sb.toString();
	}

	public static String decode(String s) {
		int count = 0;
		char prev = s.charAt(0);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < s.length(); i++) {
			char curr = s.charAt(i);
			if (isDigit(curr)) {
				count = count * 10 + curr - '0';
			} else {
				append(sb, prev, count);
				count = 0;
				prev = curr;
			}
		}
		append(sb, prev, count);
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
		System.out.println("Encoded: " + encode(s1)); //w4a3d1e1x12
		System.out.println("Decoded: " + decode(encode(s1)));

		String s2 = "aaaabbbcc";
		System.out.println("Source: " + s2);
		System.out.println("Encoded: " + encode(s2)); // a4b3c2
		System.out.println("Decoded: " + decode(encode(s2)));

	}
}
