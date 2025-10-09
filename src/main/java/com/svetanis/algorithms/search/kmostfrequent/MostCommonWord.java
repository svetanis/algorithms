package com.svetanis.algorithms.search.kmostfrequent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 819. Most Common Word

public final class MostCommonWord {

	public static String mostCommon(String s, String[] banned) {
		Set<String> set = new HashSet<>(Arrays.asList(banned));
		List<String> words = Arrays.asList(s.toLowerCase()
				.replaceAll("[^a-zA-Z ]", " ").split("\\s+"));
		Map<String, Integer> map = frequencyMap(words, set);
		int max = 0;
		String mostFrequent = null;
		for (String key : map.keySet()) {
			int freq = map.get(key);
			if (freq > max) {
				max = freq;
				mostFrequent = key;
			}
		}
		return mostFrequent;
	}

	private static Map<String, Integer> frequencyMap(List<String> terms, Set<String> set) {
		Map<String, Integer> map = new HashMap<>();
		for (String term : terms) {
			if (!set.contains(term)) {
				map.put(term, map.getOrDefault(term, 0) + 1);
			}
		}
		return map;
	}

	public static void main(String[] args) {
		String[] banned1 = { "hit" };
		String s1 = "Bob hit a ball, the hit BALL flew far after it was hit.";
		System.out.println(mostCommon(s1, banned1)); // ball

		String[] banned2 = { "a" };
		String s2 = "a, a, a, a, b,b,b,c, c";
		System.out.println(mostCommon(s2, banned2)); // b
	}

}
