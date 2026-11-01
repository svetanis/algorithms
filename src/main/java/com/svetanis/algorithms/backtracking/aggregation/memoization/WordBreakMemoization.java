package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.startsWith;

import java.util.List;
import java.util.Set;

// 139. Word Break
//
// Input: a string s and a list of words (1 <= s.length <= 300, 1 <= words.length <= 1000, each
// word 1 to 20 lowercase letters, all different). Returns true if s can be cut into pieces that
// are each a word of the list; a word can be used any number of times. The code trims the rest of
// s before comparing, which changes nothing for lowercase letters.
//
// The one idea: one call answers "can the part of s from index to the end be built from words?".
// One cell of memo holds that answer for one index -- null while it has not been asked, then true
// or false. The answer depends only on index, not on how the front of s was cut, so every later
// route to the same index reads the cell instead of searching again. Index n (the end of s) is
// the success case, which is why the memo has n + 1 cells.
//
// Time: O(n^2 * m) -- n indexes, each tries m words, and each try copies and compares up to n
// letters of the rest of s.
// Space: O(n^2) -- the memo is O(n), but each of up to n frames on the stack holds its own copy of
// the rest of s (the prefix variable).
//
// Siblings: WordBreakBacktracking is the same recursion with no memo, so it is exponential. More
// versions are in com.svetanis.algorithms.dp.wordbreak. dfs2 tries every prefix of the rest of s
// against a set of words instead of every word against the rest of s; main does not call it.

public final class WordBreakMemoization {

	public static boolean wbm(String str, List<String> dict) {
		// n + 1: index reaches str.length(), which is the success state
		Boolean[] memo = new Boolean[str.length() + 1];
		return dfs(0, str, memo, dict);
	}

	private static boolean dfs(int index, String s, Boolean[] memo, List<String> dict) {
		// Time Complexity: O(n^2 * m)
		// Space Complexity: O(n^2) -- see the header

		// base case BEFORE the memo lookup: the lookup reads memo[index],
		// so it must not run at an index the base case exists to stop at
		if (index == s.length()) { // BASE: the whole string is cut into words
			return true;
		}
		if (memo[index] != null) { // LOOKUP: this index was answered before
			return memo[index];
		}

		boolean result = false;
		for (String word : dict) {
			// a zero-length word leaves `index` where it was, so the same state
			// recurses forever. the memo does not stop it: memo[index] is written
			// after the loop, and the loop never returns
			if (word.isEmpty()) { // SKIP: an empty word makes no progress
				continue;
			}
			String prefix = s.substring(index).trim();
			if (startsWith(prefix, word)) { // TAKE: this word fits at index
				result = result || dfs(index + word.length(), s, memo, dict); // WALK: on from after it
			}
		}
		memo[index] = result; // RECORD: the answer for this index
		return result;
	}

	private static boolean dfs2(int index, String s, Boolean[] memo, Set<String> dict) {
		// same ordering as dfs -- base case first
		if (index == s.length()) {
			return true;
		}
		if (memo[index] != null) {
			return memo[index];
		}

		boolean result = false;
		for (int i = index; i < s.length(); i++) {
			String prefix = s.substring(index, i + 1);
			if (dict.contains(prefix)) {
				result = result || dfs2(index + prefix.length(), s, memo, dict);
			}
		}
		memo[index] = result;
		return result;
	}

	public static void main(String[] args) {
		System.out.println(wbm("aab", asList("a", "c"))); // false
		System.out.println(wbm("aab", asList("a", "aa", "b"))); // true
		System.out.println(wbm("iamsuperlady", dictionary())); // true
		// an empty dictionary word is skipped, so it cannot recurse forever
		System.out.println(wbm("aab", asList("a", "", "b"))); // true
	}
}
