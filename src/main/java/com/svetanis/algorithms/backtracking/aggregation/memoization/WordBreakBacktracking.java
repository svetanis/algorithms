package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.startsWith;

import java.util.List;
import java.util.Set;

// given a string and a list of words
// determine if the string can be 
// constructed from concatenating 
// words from the list of words
// a word can be used multiple times

// see more examples in com.svetanis.algorithms.dp.wordbreak

public final class WordBreakBacktracking {

	public static boolean wbb(String str, List<String> dict) {
		return dfs(0, str, dict);
	}

	private static boolean dfs(int index, String s, List<String> dict) {
		// Time Complexity: O(m^n)
		if (index == s.length()) {
			return true;
		}
		boolean result = false;
		for (String word : dict) {
			String prefix = s.substring(index);
			if (startsWith(prefix, word)) {
				result = result || dfs(index + word.length(), s, dict);
			}
		}
		return result;
	}

	private static boolean dfs2(int index, String s, Set<String> dict) {
		if (index == s.length()) {
			return true;
		}
		boolean result = false;
		for (int i = index; i < s.length(); i++) {
			String prefix = s.substring(index, i + 1);
			if (dict.contains(prefix)) {
				result = result || dfs2(index + prefix.length(), s, dict);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(wbb("aab", asList("a", "c")));
		System.out.println(wbb("aab", asList("a", "aa", "b")));
		System.out.println(wbb("iamsuperlady", dictionary()));
	}
}
