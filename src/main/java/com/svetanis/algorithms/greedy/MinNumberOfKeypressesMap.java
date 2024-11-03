package com.svetanis.algorithms.greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

// 2268 - Minimum Number of Keypresses

public final class MinNumberOfKeypressesMap {
	// Time Complexity: O(n)

	public static int minNumKeypresses(String s) {
		Map<Character, Integer> map = frequency(s);
		Map<Character, Integer> sorted = sortByValue(map);
		int total = 0;
		int count = 0;
		int keypress = 1;
		for (char c : sorted.keySet()) {
			total += keypress * sorted.get(c);
			count++;
			// every 9th char requires additional keypress
			if (count % 9 == 0) {
				keypress++;
			}
		}
		return total;
	}

	private static Map<Character, Integer> sortByValue(Map<Character, Integer> map) {
		Map<Character, Integer> sorted = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, 
						(e1, e2) -> e1, LinkedHashMap<Character, Integer>::new));
		return sorted;
	}

	private static Map<Character, Integer> frequency(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(minNumKeypresses("hello")); // 5
	}
}
