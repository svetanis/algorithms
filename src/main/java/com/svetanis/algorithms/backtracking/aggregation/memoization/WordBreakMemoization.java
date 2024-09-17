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

public final class WordBreakMemoization {

	public static boolean wbm(String str, List<String> dict) {
		Boolean[] memo = new Boolean[str.length()];
		return dfs(0, str, memo, dict);
	}

	private static boolean dfs(int index, String s, Boolean[] memo, List<String> dict) {
		// Time Complexity: O(n^2 * m)
		// Space Complexity: O(n)
		
		if (memo[index] != null) {
			return memo[index];
		}
		if (index == s.length()) {
			return true;
		}

		boolean result = false;
		for (String word : dict) {
			String prefix = s.substring(index).trim();
			if (startsWith(prefix, word)) {
				result = result || dfs(index + word.length(), s, memo, dict);
			}
		}
		memo[index] = result;
		return result;
	}

	private static boolean dfs2(int index, String s, Boolean[] memo, Set<String> dict) {
		if (memo[index] != null) {
			return memo[index];
		}
		if (index == s.length()) {
			return true;
		}

		boolean result = false;
		for (int i = index; i < s.length(); i++) {
			String prefix = s.substring(index, i + 1);
			if (dict.contains(prefix)) {
				result = result || dfs2(index + prefix.length(), s, memo, dict);
			}
			memo[i] = result;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(wbm("aab", asList("a", "c")));
		System.out.println(wbm("aab", asList("a", "aa", "b")));
		System.out.println(wbm("iamsuperlady", dictionary()));
	}
}
