package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 734. Sentence Similarity
//
// Input: two sentences as word arrays, and a list of similar word pairs. Returns true when the
// sentences have the same length and, at every position, the two words are equal or form a
// listed pair (in either order). Similarity is not transitive: if a~b and b~c are listed, a and c
// are still not similar. On LeetCode: 1 <= sentence1.length, sentence2.length <= 1000,
// 1 <= word length <= 20, 0 <= similarPairs.length <= 1000, words are English letters.
//
// The one idea: a position passes if the words are the same, or if the pair "left.right" is in
// the set of listed pairs. One entry in the set is one listed pair written as one string, in the
// order it was listed; so each position looks the pair up in both orders. Because only direct
// pairs count, there is nothing to chain, and one pass over the positions is enough.
//
// Time: O(n + p) -- one set entry per listed pair (p), then constant work per position (n);
// words are at most 20 letters, so comparing and hashing them is constant.
// Space: O(p) -- the set of pairs.
//
// Siblings: SentenceSimilaritySimple stores each pair in both directions in a map of sets, so a
// position needs one lookup instead of two.

public final class SentenceSimilarity {

	public static boolean areSimilar(String[] snt1, String[] snt2, List<List<String>> pairs) {
		if (snt1.length != snt2.length) { // BASE: different lengths never match
			return false;
		}
		Set<String> set = similarPairs(pairs);
		for (int i = 0; i < snt1.length; i++) { // WALK: position by position
			String left = snt1[i];
			String right = snt2[i];
			String p1 = pair(left, right);
			String p2 = pair(right, left);
			boolean equals = left.equals(right);
			boolean similar = set.contains(p1); // LOOKUP: the pair as listed
			boolean reverse = set.contains(p2); // LOOKUP: the pair listed the other way round
			if (!equals && !similar && !reverse) { // RETURN: one failing position fails the sentence
				return false;
			}
		}
		return true;
	}

	private static Set<String> similarPairs(List<List<String>> pairs) {
		Set<String> set = new HashSet<>();
		for (List<String> pair : pairs) {
			set.add(pair(pair.get(0), pair.get(1))); // RECORD: one pair, one string
		}
		return set;
	}

	private static String pair(String left, String right) {
		return left + "." + right; // words are letters only, so "." cannot be part of a word
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
