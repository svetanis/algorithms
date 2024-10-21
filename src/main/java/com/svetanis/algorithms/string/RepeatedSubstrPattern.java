package com.svetanis.algorithms.string;

// 459. Repeated Substring Pattern

public final class RepeatedSubstrPattern {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static boolean rsp(String s) {
		String concat = s.concat(s);
		if (concat.indexOf(s, 1) < s.length()) {
			return true;
		}
		return false;
	}

	public static boolean rsp2(String s) {
		String concat = s.concat(s);
		String substr = concat.substring(1, concat.length() - 1);
		if (substr.contains(s)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(rsp2("abab")); // true
		System.out.println(rsp2("aba")); // false
		System.out.println(rsp2("abcabcabcabc")); // true
	}
}
