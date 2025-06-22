package com.svetanis.algorithms.string.search;

// 459. Repeated Substring Pattern

public final class RepeatedSubstrPatternBruteForce {

	public static boolean rsp(String s) {
		for (int i = 0; i < s.length(); i++) {
			String prefix = s.substring(0, i);
			if (concat(prefix, s)) {
				return true;
			}
		}
		return false;
	}

	private static boolean concat(String prefix, String s) {
		if (prefix.length() == 0) {
			return false;
		}
		if (s.length() % prefix.length() != 0) {
			return false;
		}
		int n = s.length() / prefix.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(prefix);
		}
		return s.equals(sb.toString());
	}

	public static void main(String[] args) {
		System.out.println(rsp("abab")); // true
		System.out.println(rsp("aba")); // false
		System.out.println(rsp("abcabcabcabc")); // true
	}
}
