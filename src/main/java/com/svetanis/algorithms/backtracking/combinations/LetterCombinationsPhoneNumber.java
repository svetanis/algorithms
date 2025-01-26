package com.svetanis.algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 17. Letter Combinations of a Phone Number

// given a phone number that contains digits 2-9
// find all possible letter combinations the 
// phone number could translate to

public final class LetterCombinationsPhoneNumber {
	// Time Complexity: O(4^n)
	// Space Complexity: O(4^n)

	private final static Map<Character, String> DIGITS = build();

	public static List<String> combinations(String s) {
		List<String> result = new ArrayList<>();
		if (s.length() == 0) {
			return result;
		}
		result.add("");
		for (char digit : s.toCharArray()) {
			String letters = DIGITS.get(digit);
			List<String> list = new ArrayList<>();
			for (String combination : result) {
				for (char letter : letters.toCharArray()) {
					list.add(combination + letter);
				}
			}
			result = list;
		}
		return result;
	}

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
		// [jm, jn, jo, km, kn, ko, lm, ln, lo]
		System.out.println(combinations("56"));
		// [ad, ae, af, bd, be, bf, cd, ce, cf]
		System.out.println(combinations("23"));
		System.out.println(combinations(""));
		System.out.println(combinations("2")); // [a, b, c]
	}
}
