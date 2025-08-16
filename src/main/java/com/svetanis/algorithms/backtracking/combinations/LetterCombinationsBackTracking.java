package com.svetanis.algorithms.backtracking.combinations;

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

	private final Map<Character, String> map = build();

	private String digits;

	public List<String> combinations(String digits) {
		this.digits = digits;
		List<String> list = new ArrayList<>();
		if (digits.isEmpty()) {
			return list;
		}
		dfs(new StringBuilder(), list);
		return list;
	}

	// index is redundant and derived from sb.length
	private void dfs(StringBuilder sb, List<String> list) {
		if (sb.length() == digits.length()) {
			list.add(sb.toString());
			return;
		}

		char next = digits.charAt(sb.length());
		for (char c : map.get(next).toCharArray()) {
			sb.append(c);
			dfs(sb, list);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

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
		System.out.println(lcb.combinations("23"));
		System.out.println(lcb.combinations("56"));
		System.out.println(lcb.combinations(""));
		System.out.println(lcb.combinations("2"));
	}
}
