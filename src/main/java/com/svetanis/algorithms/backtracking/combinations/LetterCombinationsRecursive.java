package com.svetanis.algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 17. Letter Combinations of a Phone Number

public final class LetterCombinationsRecursive {

	private final Map<Character, String> map = build();

	private String digits;

	public List<String> combinations(String digits) {
		this.digits = digits;
		List<String> list = new ArrayList<>();
		if (digits.isEmpty()) {
			return list;
		}
		dfs(0, "", list);
		return list;
	}

	private void dfs(int index, String s, List<String> list) {
		int n = digits.length();
		if (s.length() > n) {
			return;
		} else if (s.length() == n) {
			list.add(s);
			return;
		}
		for (int i = index; i < n; ++i) {
			String letters = map.get(digits.charAt(i));
			for (char letter : letters.toCharArray()) {
				dfs(i + 1, s + letter, list);
			}
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
		LetterCombinationsRecursive lc = new LetterCombinationsRecursive();
		System.out.println(lc.combinations("23"));
		System.out.println(lc.combinations("56"));
		System.out.println(lc.combinations(""));
		System.out.println(lc.combinations("2"));
	}
}
