package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static com.svetanis.algorithms.dp.wordbreak.Dictionary.dictionary;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.Set;

// 139. Word Break
//
// Input: a string s and a list of words (1 <= s.length <= 300, 1 <= words.length <= 1000, each
// word 1 to 20 lowercase letters, all different). Returns true if s can be cut into pieces that
// are each a word of the list; a word can be used any number of times.
//
// The one idea: one call answers "can the part of s from index to the end be built from words?".
// It tries every word that the rest of s starts with, and asks the same question from the index
// just after that word. Reaching the end of s means success. Nothing is remembered, so the same
// index is reached again by every way of cutting the front of s into words, and a string like
// "aaaa...ab" with words "a", "aa", ... takes exponential time.
//
// Time: O(m^n) -- m words tried at each of up to n positions deep, no cache.
// Space: O(n) -- the recursion is up to n deep. s.startsWith(word, index) compares the word with s
// in place, from index on, so no frame copies the rest of s.
//
// Siblings: WordBreakMemoization caches the answer per index. More versions are in
// com.svetanis.algorithms.dp.wordbreak. dfs2 tries every prefix of the rest of s against a set
// of words instead of every word against the rest of s; main does not call it.

public final class WordBreakBacktracking {

	public static boolean wbb(String str, List<String> dict) {
		return dfs(0, str, dict);
	}

	private static boolean dfs(int index, String s, List<String> dict) {
		if (index == s.length()) { // BASE: the whole string is cut into words
			return true;
		}
		boolean result = false;
		for (String word : dict) {
			// a zero-length word leaves `index` where it was, so the same state
			// recurses forever
			if (word.isEmpty()) { // SKIP: an empty word makes no progress
				continue;
			}
			if (s.startsWith(word, index)) { // TAKE: this word fits at index, compared in place
				result = result || dfs(index + word.length(), s, dict); // WALK: on from after it
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
		System.out.println(wbb("aab", asList("a", "c"))); // false
		System.out.println(wbb("aab", asList("a", "aa", "b"))); // true
		System.out.println(wbb("iamsuperlady", dictionary())); // true
		// an empty dictionary word is skipped, so it cannot recurse forever
		System.out.println(wbb("aab", asList("a", "", "b"))); // true
	}
}
