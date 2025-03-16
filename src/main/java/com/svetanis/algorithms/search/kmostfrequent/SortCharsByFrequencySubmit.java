package com.svetanis.algorithms.search.kmostfrequent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 451. Sort Characters By Frequency

public final class SortCharsByFrequencySubmit {
	// Time Complexity: O(n)

	public static String sort(String s) {
		int n = s.length();
		Map<Character, Integer> fm = frequencyMap(s);
		List<Character>[] buckets = buckets(n, fm);
		return sortedByFrequency(n, buckets);
	}

	private static List<Character>[] buckets(int n, Map<Character, Integer> map) {
		List<Character>[] buckets = new List[n + 1];
		for (char c : map.keySet()) {
			int freq = map.get(c);
			if (buckets[freq] == null) {
				buckets[freq] = new ArrayList<>();
			}
			buckets[freq].add(c);
		}
		return buckets;
	}

	private static Map<Character, Integer> frequencyMap(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		return map;
	}

	private static String sortedByFrequency(int n, List<Character>[] buckets) {
		// build a string, appending the most occurring chars first
		StringBuilder sb = new StringBuilder();
		for (int i = n; i > 0; i--) {
			if (buckets[i] != null) {
				for (char c : buckets[i]) {
					for (int j = 0; j < i; j++) {
						sb.append(c);
					}
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String s1 = "tree"; // eert
		String s2 = "cccaaa"; // aaaccc
		String s3 = "Aabb"; // bbAa

		System.out.println(sort(s1));
		System.out.println(sort(s2));
		System.out.println(sort(s3));
	}
}
