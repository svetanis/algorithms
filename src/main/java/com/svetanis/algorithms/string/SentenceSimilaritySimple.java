package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 734. Sentence Similarity

public final class SentenceSimilaritySimple {
	// Time Complexity: O(n + pairs)

	public static boolean areSimilar(String[] snt1, String[] snt2, List<List<String>> pairs) {
		if (snt1.length != snt2.length) {
			return false;
		}
		Map<String, Set<String>> map = similarPairs(pairs);
		for (int i = 0; i < snt1.length; i++) {
			String w1 = snt1[i];
			String w2 = snt2[i];
			if (w1.equals(w2)) {
				continue;
			}
			if (!map.containsKey(w1) || !map.get(w1).contains(w2)) {
				return false;
			}
		}
		return true;
	}

	private static Map<String, Set<String>> similarPairs(List<List<String>> pairs) {
		Map<String, Set<String>> map = new HashMap<>();
		for (List<String> pair : pairs) {
			String a = pair.get(0);
			String b = pair.get(1);
			map.computeIfAbsent(a, k -> new HashSet<>()).add(b);
			map.computeIfAbsent(b, k -> new HashSet<>()).add(a);
		}
		return map;
	}

	public static void main(String[] args) {
		String[] s1 = { "I", "love", "to", "code" };
		String[] s2 = { "I", "adore", "to", "program" };
		List<List<String>> pairs = new ArrayList<>();
		pairs.add(Arrays.asList("love", "adore"));
		pairs.add(Arrays.asList("code", "program"));
		System.out.println(areSimilar(s1, s2, pairs)); // true

		String[] s3 = { "great", "acting", "skills" };
		String[] s4 = { "fine", "drama", "talent" };
		List<List<String>> pairs2 = new ArrayList<>();
		pairs2.add(Arrays.asList("great", "fine"));
		pairs2.add(Arrays.asList("drama", "acting"));
		pairs2.add(Arrays.asList("skills", "talent"));
		System.out.println(areSimilar(s3, s4, pairs2)); // true
	}
}
