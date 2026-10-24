package com.svetanis.algorithms.greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

// 2268. Minimum Number of Keypresses
//
// Input: s -- a string of lowercase English letters. A keypad has 9 buttons; each of the 26 letters
// is put on exactly one button, a button holds at most 3 letters, and the j-th letter on a button
// takes j presses to type. The letters may be placed freely. Returns the fewest presses that type
// s. On LeetCode: 1 <= s.length <= 10^5, s holds lowercase English letters only.
//
// The one idea: the placement makes 26 slots -- 9 that cost 1 press (the first letter on each
// button), 9 that cost 2 and 8 that cost 3. A letter's total is how often it appears times the
// cost of its slot. Give the cheapest slots to the most frequent letters: if a more frequent letter
// sat in a dearer slot than a less frequent one, swapping the two could not raise the total. So
// rank the letters by count, largest first; the first 9 cost 1 press each, the next 9 cost 2, the
// rest cost 3. The map is rebuilt as a LinkedHashMap in order of falling count, so walking its keys
// visits the letters in rank order; letters that never appear are not in it and cost nothing.
//
// Time: O(n) -- one pass to count; sorting at most 26 entries is constant work.
// Space: O(1) -- at most 26 entries in each map.
//
// Siblings: MinNumberOfKeypresses counts in an int[26], sorts the array and reads it from the end;
// the cost rule is the same.

public final class MinNumberOfKeypressesMap {

	public static int minNumKeypresses(String s) {
		Map<Character, Integer> map = frequency(s);
		Map<Character, Integer> sorted = sortByValue(map); // SORT: most frequent first
		int total = 0;
		int count = 0;
		int keypress = 1;
		for (char c : sorted.keySet()) { // RANK: letters in order of falling count
			total += keypress * sorted.get(c); // COST: its count times its presses
			count++;
			if (count % 9 == 0) { // NEXT slot on every button costs one more press
				keypress++;
			}
		}
		return total;
	}

	private static Map<Character, Integer> sortByValue(Map<Character, Integer> map) {
		Map<Character, Integer> sorted = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // ORDER: largest count first
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(e1, e2) -> e1, LinkedHashMap<Character, Integer>::new)); // KEEP that order
		return sorted;
	}

	private static Map<Character, Integer> frequency(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1); // COUNT each letter
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(minNumKeypresses("hello")); // 5
	}
}
