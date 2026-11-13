package com.svetanis.algorithms.string.anagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 49. Group Anagrams
// com.svetanis.algorithms.string.anagram.GroupAnagramsMultimap
// com.svetanis.algorithms.string.anagram.GroupAnagrams49
//
// Input: an array of words, lowercase letters, possibly empty words. Returns: the words
// grouped so that two words share a group exactly when they are anagrams -- the same letters,
// the same number of times. The groups, and the words inside them, come in any order.
//
// The one idea: give every word a KEY that is equal for exactly the words of one group. Here
// the key is the word's 26 letter counts written out, "#1#0#0...": "eat", "tea", "ate" all
// count one a, one e, one t. Then group by the key in a map, key -> the words with that key.
// Counting is one pass over the word, so no sort is needed.
//
// Siblings:
//   GroupAnagrams49       -- the same grouping with a different key: the word's letters sorted
//   GroupAnagramsMultimap -- the same grouping by sorted key, printing only the groups of two
//                            or more, each joined by spaces
//   SortAnagramsMultimap  -- one list with each group's words next to each other
//   CountAnagramsMultimap -- for each word, how many other words are its anagrams

public final class GroupAnagrams49Simple {
	// Time Complexity: O(m * (n + 26)) for m words of length up to n: counting a word is one
	// pass over it, and its key is built from, and hashed over, its 26 counts
	// Space Complexity: O(m), one list entry per word, and at most one key per word, each a
	// fixed 26 counts long -- a key does not grow with the word, beyond the digits of a count

	public static List<List<String>> group(String[] words) {
		Map<String, List<String>> map = new HashMap<>();
		for (String word : words) { // WALK: word arrives
			int[] chars = freq(word); // KEY: the word's 26 letter counts
			String key = key(chars); // KEY: the counts written out as one string
			map.computeIfAbsent(key, v -> new ArrayList<>()).add(word); // GROUP: word joins its key's list
		}
		return new ArrayList<>(map.values()); // READ: every list, in any order
	}

	private static String key(int[] chars) {
		StringBuilder sb = new StringBuilder();
		for (int count : chars) {
			sb.append("#"); // SEPARATE: without it, counts 1,11 and 11,1 would both read "111"
			sb.append(count);
		}
		return sb.toString();
	}

	private static int[] freq(String s) {
		int[] chars = new int[26];
		for (char c : s.toCharArray()) {
			chars[c - 'a']++; // COUNT: one more of this letter
		}
		return chars;
	}

	public static void main(String[] args) {
		String[] a1 = { "eat", "tea", "tan", "ate", "nat", "bat" };
		System.out.println(group(a1)); // [eat, tea, ate] [bat] [tan, nat], in any order
		String[] a2 = { "" };
		System.out.println(group(a2)); // [[]] -- one group holding the empty word
		String[] a3 = { "a" };
		System.out.println(group(a3)); // [[a]]
	}
}