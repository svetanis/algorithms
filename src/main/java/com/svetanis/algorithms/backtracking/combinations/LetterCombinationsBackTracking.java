package com.svetanis.algorithms.backtracking.combinations;

import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 17. Letter Combinations of a Phone Number

// given a phone number that contains digits 2-9
// find all possible letter combinations the 
// phone number could translate to

public final class LetterCombinationsBackTracking {
	// Time Complexity: O(n * 4^n)
	// Space Complexity: O(n * 4^n)

	private final static Map<Character, String> DIGITS = build();

	public static List<String> combinations(String s) {
		List<String> list = new ArrayList<>();
		if (s.isEmpty()) {
			return list;
		}
		StringBuilder sb = new StringBuilder();
		dfs(s, sb, list);
		return newList(list);
	}

	// index is redundant and derived from list.size()
	private static void dfs(String s, StringBuilder sb, List<String> list) {
		if (sb.length() == s.length()) {
			list.add(sb.toString());
			return;
		}

		char nextDigit = s.charAt(sb.length());
		for (char c : DIGITS.get(nextDigit).toCharArray()) {
			sb.append(c);
			dfs(s, sb, list);
			sb.deleteCharAt(sb.length() - 1);
		}
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
		printLines(combinations("23"));
		printLines(combinations("56"));
		printLines(combinations(""));
	}
}
