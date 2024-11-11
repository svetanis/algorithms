package com.svetanis.algorithms.string.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 791. Custom Sort String

public final class CustomSortHashMap {
	// Time Complexity: O(n + m)
	// Space Complexity: O(n)

	public static String customSort(String order, String s) {
		StringBuilder sb = new StringBuilder();
		Map<Character, Integer> map = frequency(s);
		for (char c : order.toCharArray()) {
			int f = map.getOrDefault(c, 0);
			if (f > 0) {
				sb.append(concat(c, f));
				map.put(c, 0);
			}
		}
		for (char c : map.keySet()) {
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
