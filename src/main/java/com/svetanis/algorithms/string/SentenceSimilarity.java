package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 734. Sentence Similarity

public final class SentenceSimilarity {
	// Time Complexity: O(n + pairs)

	public static boolean areSimilar(String[] snt1, String[] snt2, List<List<String>> pairs) {
		if (snt1.length != snt2.length) {
			return false;
		}
		Set<String> set = similarPairs(pairs);
		for (int i = 0; i < snt1.length; i++) {
			String left = snt1[i];
			String right = snt2[i];
			String p1 = pair(left, right);
			String p2 = pair(right, left);
			boolean equals = left.equals(right);
			boolean similar = set.contains(p1);
			boolean reverse = set.contains(p2);
			if (!equals && !similar && !reverse) {
				return false;
			}
		}
		return true;
	}

	private static Set<String> similarPairs(List<List<String>> pairs) {
		Set<String> set = new HashSet<>();
		for (List<String> pair : pairs) {
			set.add(pair(pair.get(0), pair.get(1)));
		}
		return set;
	}

	private static String pair(String left, String right) {
		return left + "." + right;
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
