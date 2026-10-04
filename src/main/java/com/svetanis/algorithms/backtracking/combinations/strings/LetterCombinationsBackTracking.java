package com.svetanis.algorithms.backtracking.combinations.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 17. Letter Combinations of a Phone Number
//
// Given a string of digits 2-9, return every string of letters it could spell on a phone keypad,
// one letter per digit, in any order: "23" gives [ad, ae, af, bd, be, bf, cd, ce, cf].
//
// LC 17 is in this folder three times: the same problem, the same answers in the same order,
// written three ways. They differ only in how the letters chosen so far are carried:
//   LetterCombinationsBackTracking  (this file) one StringBuilder shared by every call: append a
//                                   letter, recurse, then delete it again
//   LetterCombinationsRecursive     a new String each call (prefix + letter): nothing to undo
//   LetterCombinationsPhoneNumber   no recursion: a list of every combination so far, extended
//                                   by every letter of the next digit
// It is BinaryStringsOfLengthN's one choice per position, with the choices at each position read
// from the keypad instead of always being 0 and 1.

public final class LetterCombinationsBackTracking {
	// Time Complexity: O(n * 4^n), at most 4 letters per digit, so at most 4^n strings of n letters
	// Space Complexity: O(n) besides the output, the recursion depth and the StringBuilder

	private final Map<Character, String> map = build();

	private String digits;

	public List<String> combinations(String digits) {
		this.digits = digits;
		List<String> list = new ArrayList<>();
		if (digits.isEmpty()) { // no digits: no combinations, not one empty one
			return list;
		}
		dfs(new StringBuilder(), list);
		return list;
	}

	// index is redundant and derived from sb.length
	private void dfs(StringBuilder sb, List<String> list) {
		if (sb.length() == digits.length()) { // a letter for every digit
			list.add(sb.toString()); // a copy: sb keeps changing
			return;
		}

		char next = digits.charAt(sb.length()); // the digit for the position being filled
		for (char c : map.get(next).toCharArray()) { // the choices for this position
			sb.append(c); // choose
			dfs(sb, list);
			sb.deleteCharAt(sb.length() - 1); // undo, or the next letter lands after this one
		}
	}

	// 0 and 1 have no letters on a keypad; LC 17 never gives them, and here they stand for themselves
	private Map<Character, String> build() {
		Map<Character, String> map = new HashMap<>();
		map.put('0', "0");
		map.put('1', "1");
		map.put('2', "abc");
		map.put('3', "def");
		map.put('4', "ghi");
		map.put('5', "jkl");
		map.put('6', "mno");
		map.put('7', "pqrs");
		map.put('8', "tuv");
		map.put('9', "wxyz");
		return map;
	}

	public static void main(String[] args) {
		LetterCombinationsBackTracking lcb = new LetterCombinationsBackTracking();
		System.out.println(lcb.combinations("23")); // [ad, ae, af, bd, be, bf, cd, ce, cf]
		System.out.println(lcb.combinations("56")); // [jm, jn, jo, km, kn, ko, lm, ln, lo]
		System.out.println(lcb.combinations("")); // []
		System.out.println(lcb.combinations("2")); // [a, b, c]
	}
}
