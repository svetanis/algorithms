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
//   LetterCombinationsBackTracking  one StringBuilder shared by every call: append a letter,
//                                   recurse, then delete it again
//   LetterCombinationsRecursive     a new String each call (prefix + letter): nothing to undo
//   LetterCombinationsPhoneNumber   (this file) no recursion: a list of every combination so
//                                   far, extended by every letter of the next digit
// The loop here is SubsetsCopyAndAdd's: start from one empty string, and for each digit build
// the next list from the current one. After digit i the list holds every combination of the
// first i digits.

public final class LetterCombinationsPhoneNumber {
	// Time Complexity: O(n * 4^n), at most 4 letters per digit, so at most 4^n strings of n letters
	// Space Complexity: O(n * 4^n), the current and the next list

	private final static Map<Character, String> DIGITS = build();

	public static List<String> combinations(String digits) {
		List<String> combinations = new ArrayList<>();
		if (digits.length() == 0) { // no digits: no combinations, not one empty one
			return combinations;
		}
		combinations.add(""); // the one combination of no digits, the one every other grows from
		for (char digit : digits.toCharArray()) {
			String letters = DIGITS.get(digit);
			List<String> next = new ArrayList<>(); // every combination one digit longer
			for (String combination : combinations) {
				for (char letter : letters.toCharArray()) {
					next.add(combination + letter);
				}
			}
			combinations = next; // the shorter ones are no longer answers
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
