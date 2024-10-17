package com.svetanis.algorithms.backtracking.additionalstates;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 291. Word Pattern
// given a pattern and a string s,
// find if s follows the same pattern.
// Here follow means a full match, 
// such that there is a bijection between 
// a letter in pattern and a non-empty word in s. 
// Specifically:

// A string s is considered to match a pattern 
// if we can associate each character in pattern 
// with a non-empty string in s such that the 
// concatenation of the strings, in the order they 
// appear in pattern, exactly makes up s. 
// The mapping must be bijective, which means two 
// conditions must be met: first, no two different 
// characters in pattern can map to the same substring in s, 
// and second, each character must map to exactly one substring 
// (and not multiple different substrings at different instances).

public final class WordPatternBacktracking {
	// Time Complexity: O(n^m)
	// Space Complexity: O(m + n^2)

	public static boolean wordPattern(String p, String s) {
		Set<String> set = new HashSet<>();
		Map<Character, String> map = new HashMap<>();
		return dfs(p, 0, s, 0, set, map);
	}

	private static boolean dfs(String p, int index, String s, int start, 
			Set<String> set, Map<Character, String> map) {
		int m = p.length();
		int n = s.length();
		if (index == m && start == n) {
			return true;
		}
		if (start == n || index == m || n - start < m - index) {
			return false;
		}
		char c = p.charAt(index);
		for (int end = start + 1; end <= n; end++) {
			String word = s.substring(start, end);
			if (map.getOrDefault(c, "").equals(word)) {
				if (dfs(p, index + 1, s, end, set, map)) {
					return true;
				}
			} else if (!map.containsKey(c) && !set.contains(word)) {
				map.put(c, word);
				set.add(word);
				if (dfs(p, index + 1, s, end, set, map)) {
					return true;
				}
				map.remove(c);
				set.remove(word);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(wordPattern("aba", "catdogcat")); // true

		System.out.println(wordPattern("abab", "redblueredblue")); // true
	}
}
