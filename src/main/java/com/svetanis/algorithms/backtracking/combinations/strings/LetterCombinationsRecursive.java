package com.svetanis.algorithms.backtracking.combinations.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 17. Letter Combinations of a Phone Number
//
// Input: a string of digits. Returns every string of letters it could spell on a phone keypad, one
// letter per digit, in any order: "23" gives [ad, ae, af, bd, be, bf, cd, ce, cf]; no digits give
// []. On LeetCode: 0 <= digits.length <= 4, each digit '2' to '9'.
//
// The one idea: BinaryStringsOfLengthN's one choice per position, with the choices at each position
// read from the keypad instead of always being 0 and 1. One call holds the letters chosen so far
// (prefix) and the digit it decides (index). Each call passes a new String (prefix + letter), so
// the caller's prefix never changes and there is nothing to undo.
//
// Time: O(n * 4^n) -- at most 4 letters per digit, so at most 4^n strings of n letters.
// Space: O(n^2) besides the output -- n levels, each holding its own prefix.
//
// Siblings: LC 17 is in this folder three times, the same answers in the same order; they differ
// only in how the letters chosen so far are carried:
//   LetterCombinationsBackTracking  one StringBuilder shared by every call: append a letter,
//                                   recurse, then delete it again
//   LetterCombinationsRecursive     (this file) a new String each call: nothing to undo
//   LetterCombinationsPhoneNumber   no recursion: a list of every combination so far, extended
//                                   by every letter of the next digit

public final class LetterCombinationsRecursive {
	private final Map<Character, String> map = build();

	private String digits;

	public List<String> combinations(String digits) {
		this.digits = digits;
		List<String> combinations = new ArrayList<>();
		if (digits.isEmpty()) { // BASE: no digits, no combinations, not one empty one
			return combinations;
		}
		dfs(0, "", combinations);
		return combinations;
	}

	// index: the digit whose letter is chosen next
	private void dfs(int index, String prefix, List<String> combinations) {
		if (index == digits.length()) { // BASE: a letter for every digit
			combinations.add(prefix); // RECORD
			return;
		}
		for (char letter : map.get(digits.charAt(index)).toCharArray()) { // WALK: this digit's letters
			dfs(index + 1, prefix + letter, combinations); // CHOOSE: a new String
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
		LetterCombinationsRecursive lc = new LetterCombinationsRecursive();
		System.out.println(lc.combinations("23")); // [ad, ae, af, bd, be, bf, cd, ce, cf]
		System.out.println(lc.combinations("56")); // [jm, jn, jo, km, kn, ko, lm, ln, lo]
		System.out.println(lc.combinations("")); // []
		System.out.println(lc.combinations("2")); // [a, b, c]
	}
}
