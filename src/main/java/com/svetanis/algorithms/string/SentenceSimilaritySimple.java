package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 734. Sentence Similarity
//
// Input: two sentences as word arrays, and a list of similar word pairs. Returns true when the
// sentences have the same length and, at every position, the two words are equal or form a
// listed pair (in either order). Similarity is not transitive: if a~b and b~c are listed, a and c
// are still not similar. On LeetCode: 1 <= sentence1.length, sentence2.length <= 1000,
// 1 <= word length <= 20, 0 <= similarPairs.length <= 1000, words are English letters.
//
// The one idea: for each word, keep the set of words listed as similar to it. One entry of the map
// is a word and everything it is directly paired with; each pair is stored twice, once under each
// word, so a position needs only one lookup: equal, or the right word is in the left word's set.
// Only direct pairs count, so nothing is chained.
//
// Time: O(n + p) -- two set insertions per listed pair (p), then constant work per position (n).
// Space: O(p) -- the map of sets holds each pair twice.
//
// Siblings: SentenceSimilarity stores each pair once as one string and looks every position up in
// both orders.

public final class SentenceSimilaritySimple {

	public static boolean areSimilar(String[] snt1, String[] snt2, List<List<String>> pairs) {
		if (snt1.length != snt2.length) { // BASE: different lengths never match
			return false;
		}
		Map<String, Set<String>> map = similarPairs(pairs);
		for (int i = 0; i < snt1.length; i++) { // WALK: position by position
			String w1 = snt1[i];
			String w2 = snt2[i];
			if (w1.equals(w2)) { // SKIP: equal words always match
				continue;
			}
			if (!map.containsKey(w1) || !map.get(w1).contains(w2)) { // LOOKUP: is w2 listed under w1
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
			map.computeIfAbsent(a, k -> new HashSet<>()).add(b); // RECORD: b under a
			map.computeIfAbsent(b, k -> new HashSet<>()).add(a); // RECORD: a under b
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

		String[] s5 = { "I", "love", "leetcode" };
		String[] s6 = { "I", "love", "onepiece" };
		List<List<String>> pairs3 = new ArrayList<>();
		pairs3.add(Arrays.asList("manga", "onepiece"));
		pairs3.add(Arrays.asList("platform", "anime"));
		pairs3.add(Arrays.asList("leetcode", "platform"));
		pairs3.add(Arrays.asList("anime", "manga"));
		System.out.println(areSimilar(s5, s6, pairs3)); // false
	}
}
