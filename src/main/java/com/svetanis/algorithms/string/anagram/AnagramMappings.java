package com.svetanis.algorithms.string.anagram;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 760. Find Anagram Mappings
//
// Input: two int arrays a1 and a2, where a2 holds the same values as a1, the same number of
// times, in another order. Values may repeat. Returns: mapping, where mapping[i] is an index
// in a2 holding the value a1[i]. Any such index is accepted. A value of a1 missing from a2
// breaks that promise and throws.
//
// The one idea: one pass over a2 records where each value sits, value -> index; then each
// a1[i] looks its value up.
//
// Two methods, differing only when a value repeats:
//   anagramMappings -- keeps the LAST index of each value, so every copy of a repeated value
//                      maps to that one index. LeetCode accepts this.
//   mappings        -- keeps ALL indices of each value and hands each one out once, so the
//                      answer uses every index of a2 exactly once, a true one-to-one pairing.
//
// Siblings: AnagramCounting, AnagramSorting, AnagramHashing and AnagramSubStr ask WHETHER two
// strings are anagrams; here the arrays are promised to be, and the question is where each
// value went.

public final class AnagramMappings {
	// anagramMappings:
	// Time Complexity: O(n), one pass to fill the map, one pass to read it
	// Space Complexity: O(n), the map, plus the answer
	// mappings:
	// Time Complexity: O(n) when values do not repeat; up to O(n^2) when one value repeats d
	// times. set.iterator() starts at the first bucket of the set's table and steps over every
	// empty one, and the removed indices leave the front of the table empty -- the d lookups
	// of one value step over 0 + 1 + ... + (d - 1) empty buckets.
	// Space Complexity: O(n), one set entry per index of a2, plus the answer

	public static int[] anagramMappings(int[] a1, int[] a2) {
		Map<Integer, Integer> map = new HashMap<>(); // TABLE: value -> an index in a2
		for (int i = 0; i < a2.length; i++) { // WALK: a2
			map.put(a2[i], i); // RECORD: a repeated value keeps its last index
		}
		int[] mapping = new int[a1.length];
		for (int i = 0; i < a1.length; i++) { // WALK: a1
			mapping[i] = map.get(a1[i]); // LOOKUP: where a1[i]'s value sits in a2
		}
		return mapping; // RETURN
	}

	public static int[] mappings(int[] a1, int[] a2) {
		Map<Integer, Set<Integer>> map = new HashMap<>(); // TABLE: value -> every index in a2
		for (int i = 0; i < a2.length; i++) { // WALK: a2
			map.computeIfAbsent(a2[i], k -> new HashSet<>()).add(i); // RECORD: this index joins its value's set
		}
		int[] mapping = new int[a1.length];
		for (int i = 0; i < a1.length; i++) { // WALK: a1
			Set<Integer> set = map.get(a1[i]); // LOOKUP: the indices still free for this value
			int index = set.iterator().next(); // TAKE: any one of them
			mapping[i] = index;
			set.remove(index); // USE UP: the next copy of the value gets another index
		}
		return mapping; // RETURN
	}

	public static void main(String[] args) {
		int[] a1 = { 12, 28, 46, 32, 50 };
		int[] a2 = { 50, 12, 32, 46, 28 };
		System.out.println(Arrays.toString(mappings(a1, a2))); // [1, 4, 3, 2, 0]
	}
}
