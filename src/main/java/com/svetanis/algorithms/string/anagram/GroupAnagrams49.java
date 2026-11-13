package com.svetanis.algorithms.string.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 49. Group Anagrams
//
// Input: an array of words, lowercase letters, possibly empty. Returns: the words grouped so
// that two words share a group exactly when they are anagrams -- the same letters, the same
// number of times.
//
// The one idea: give every word a KEY that is equal for exactly the words of one group. Here
// the key is the word's letters sorted: "eat", "tea", "ate" all become "aet". Then group by
// the key in a map, key -> the words with that key.
//
// Two methods:
//   group       -- LeetCode's version: the groups, and the words inside them, in any order.
//   groupSorted -- the words inside each group sorted, and the groups ordered by their first
//                  word. The order is a separate pass AFTER grouping; the key plays no part
//                  in it. Ordering by the key instead (a TreeMap) is a different order: the
//                  key "abt" of [bat] comes before "aet" of [ate, eat, tea], but ate < bat.
//
// Siblings:
//   GroupAnagrams49Simple  -- the same grouping with a different key: the 26 letter counts
//   GroupAnagramsMultimap  -- the same grouping with a Multimap, keeping groups of two or more

public final class GroupAnagrams49 {
	// Time Complexity: O(m * n log n) for m words of length up to n: each key is a sort of
	// the word; the map work is O(n) per word for hashing the key
	// groupSorted adds the sorts of the words and of the groups: O(m log m) comparisons of
	// words, each O(n)
	// Space Complexity: O(m * n), the keys and the lists hold every word once

	public static List<List<String>> group(String[] words) {
		Map<String, List<String>> map = asMap(words); // GROUP: key -> the words with that key
		return new ArrayList<>(map.values()); // READ: every list, in any order
	}

	public static List<List<String>> groupSorted(String[] words) {
		List<List<String>> groups = group(words); // GROUP: who is together, in any order
		for (List<String> g : groups) {
			Collections.sort(g); // ORDER: the words inside each group
		}
		groups.sort(Comparator.comparing(GroupAnagrams49::firstWord)); // ORDER: the groups by their first word
		return groups; // RETURN
	}

	private static String firstWord(List<String> group) {
		return group.get(0); // a group is never empty: it starts with the word that made it
	}

	private static Map<String, List<String>> asMap(String[] words) {
		Map<String, List<String>> map = new HashMap<>();
		for (String word : words) { // WALK: word arrives
			String sorted = sort(word); // KEY: the word's letters, sorted
			map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(word); // GROUP: word joins its key's list
		}
		return map;
	}

	private static String sort(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return String.valueOf(chars);
	}

	public static void main(String[] args) {
		String[] a1 = { "eat", "tea", "tan", "ate", "nat", "bat" };
		System.out.println(group(a1)); // [eat, tea, ate] [bat] [tan, nat], in any order
		System.out.println(groupSorted(a1)); // [[ate, eat, tea], [bat], [nat, tan]]
		String[] a2 = { "" };
		System.out.println(group(a2)); // [[]] -- one group holding the empty word
		String[] a3 = { "a" };
		System.out.println(group(a3)); // [[a]]
	}
}
