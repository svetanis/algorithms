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
// The one idea: no recursion. combinations holds every combination of the digits seen so far, one
// string each. The loop is SubsetsCopyAndAdd's: start from one empty string, and for each digit
// build the next list from the current one, extending every string by every letter of that digit.
// After digit i the list holds every combination of the first i digits.
//
// Time: O(n * 4^n) -- at most 4 letters per digit, so at most 4^n strings of n letters.
// Space: O(n * 4^n) -- the current and the next list.
//
// Siblings: LC 17 is in this folder three times, the same answers in the same order; they differ
// only in how the letters chosen so far are carried:
//   LetterCombinationsBackTracking  one StringBuilder shared by every call: append a letter,
//                                   recurse, then delete it again
//   LetterCombinationsRecursive     a new String each call (prefix + letter): nothing to undo
//   LetterCombinationsPhoneNumber   (this file) no recursion: extend the list digit by digit

public final class LetterCombinationsPhoneNumber {
	private final static Map<Character, String> DIGITS = build();

	public static List<String> combinations(String digits) {
		List<String> combinations = new ArrayList<>();
		if (digits.length() == 0) { // BASE: no digits, no combinations, not one empty one
			return combinations;
		}
		combinations.add(""); // BASE: the one combination of no digits
		for (char digit : digits.toCharArray()) { // WALK: one digit per turn
			String letters = DIGITS.get(digit);
			List<String> next = new ArrayList<>(); // NEXT: every combination one digit longer
			for (String combination : combinations) {
				for (char letter : letters.toCharArray()) {
					next.add(combination + letter); // EXTEND by this letter
				}
			}
			combinations = next; // SWAP: the shorter ones are no longer answers
		}
		return combinations;
	}

	// 0 and 1 have no letters on a keypad; LC 17 never gives them, and here they stand for themselves
	private static Map<Character, String> build() {
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
		System.out.println(combinations("56")); // [jm, jn, jo, km, kn, ko, lm, ln, lo]
		System.out.println(combinations("23")); // [ad, ae, af, bd, be, bf, cd, ce, cf]
		System.out.println(combinations("")); // []
		System.out.println(combinations("2")); // [a, b, c]
	}
}
