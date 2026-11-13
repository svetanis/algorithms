package com.svetanis.algorithms.string.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 791. Custom Sort String
//
// Input: strings order and s. order has no repeated letter and gives a ranking of letters. Returns
// any permutation of s in which, if x comes before y in order, every x comes before every y;
// letters that are not in order may sit anywhere. This file puts them last. On LeetCode:
// 1 <= order.length <= 26, 1 <= s.length <= 200, both are lowercase English letters, and order
// has no repeated letter.
//
// The one idea: count how many times each letter occurs in s. Then walk order from its first
// letter to its last, and for each letter write it out as many times as s has it, and set its
// count to 0. Whatever count is still above 0 belongs to a letter that order does not mention,
// and those are written at the end. Each letter of s is written exactly once, in the order's
// ranking.
//
// Time: O(n + m) for n = s.length and m = order.length -- one count pass over s, one walk over
// order, and at most 26 entries in the leftover loop.
// Space: O(n) -- the counts (at most 26 entries) and the output.
//
// Siblings: CustomSortSubmit does the same with an int[26] instead of a HashMap.

public final class CustomSortHashMap {

	public static String customSort(String order, String s) {
		StringBuilder sb = new StringBuilder();
		Map<Character, Integer> map = frequency(s); // COUNT: letter -> times it occurs in s
		for (char c : order.toCharArray()) { // WALK: the order's ranking
			int f = map.getOrDefault(c, 0);
			if (f > 0) {
				sb.append(concat(c, f)); // WRITE: all copies of c
				map.put(c, 0); // USED: so the leftover loop skips it
			}
		}
		for (char c : map.keySet()) { // LEFTOVER: letters order does not mention, in any order
			int f = map.get(c);
			if (f > 0) {
				sb.append(concat(c, f));
			}
		}
		return sb.toString();
	}

	private static String concat(char c, int f) {
		return IntStream.range(0, f).mapToObj(index -> "" + c).collect(Collectors.joining());
	}

	private static Map<Character, Integer> frequency(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(customSort("cba", "abcd")); // cbad
		System.out.println(customSort("bcafg", "abcd")); // bcad
	}
}
