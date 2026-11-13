package com.svetanis.algorithms.string;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 720. Longest Word in Dictionary
//
// Input: an array of words. A word is buildable if it is one letter long, or it is one letter
// longer than a buildable word in the array that it starts with (its prefix of length - 1).
// Returns the longest buildable word; among equally long ones, the smallest in alphabetical
// order; "" if there is none. On LeetCode: 1 <= words.length <= 1000, 1 <= words[i].length <= 30,
// lowercase letters. The method sorts the caller's array by length.
//
// The one idea: a word can only be built from a shorter word, so handle the words from the
// shortest to the longest. The set holds the buildable words found so far. A word of length 1 is
// buildable; a longer word is buildable when its prefix without the last letter is already in
// the set -- and that prefix, being shorter, has already been judged. Keep the best buildable
// word: longer wins, and at equal length the alphabetically smaller one.
//
// Time: O(n log n + S) -- the sort, plus a prefix copy and a hash for each word, S the total
// number of letters.
// Space: O(S) -- the set of buildable words.
//
// Siblings: the data-structures trie package has LongestWord, which solves it with a trie.

public final class LongestWordInDictionary {

	public static String longestWord(String[] words) {
		Arrays.sort(words, comparingInt(a -> a.length())); // SORT: shortest first
		String s = "";
		int max = 0;
		Set<String> set = new HashSet<>(); // set: the buildable words so far
		for (String word : words) {
			int len = word.length();
			if (len == 1) {
				set.add(word); // BASE: one letter is always buildable
			} else if (set.contains(word.substring(0, len - 1))) {
				set.add(word); // BUILD: the word minus its last letter is buildable
			}
			if (set.contains(word)) { // BEST: compare with the best so far
				if (max < len) {
					max = len;
					s = word;
				} else if (max == len && s.compareTo(word) > 0) { // TIE: the smaller word
					s = word;
				}
			}
		}
		return s;
	}

	public static void main(String[] args) {
		String[] a = { "w", "wo", "wor", "worl", "world" };
		System.out.println(longestWord(a)); // world
		String[] a2 = { "a", "banana", "app", "appl", "ap", "apply", "apple" };
		System.out.println(longestWord(a2)); // apple
	}
}
