package com.svetanis.algorithms.backtracking.combinatorial;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Maps.newMap;
import static com.svetanis.java.base.utils.Print.printLines;

import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

// given a phone number that contains digits 2-9
// find all possible letter combinations the 
// phone number could translate to

public final class LetterCombinationsBackTracking {
	// Time Complexity: O(n * 4^n)
	// Space Complexity: O(n * 4^n)

	private final static ImmutableMap<Character, String> DIGITS = build();

	public static ImmutableList<String> combinations(String s) {
		List<String> result = newArrayList();
		List<Character> list = newArrayList();
		dfs(s, list, result);
		return newList(result);
	}

	// index is redundant and derived from list.size()
	private static void dfs(String s, List<Character> list, List<String> result) {
		if (list.size() == s.length()) {
			String joined = Joiner.on("").join(list);
			result.add(joined);
			return;
		}

		char nextDigit = s.charAt(list.size());
		for (char c : DIGITS.get(nextDigit).toCharArray()) {
			list.add(c);
			dfs(s, list, result);
			list.remove(list.size() - 1);
		}
	}

	private static ImmutableMap<Character, String> build() {
		Map<Character, String> map = newHashMap();
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
		return newMap(map);
	}

	public static void main(String[] args) {
		printLines(combinations("23"));
		printLines(combinations("56"));
	}
}
