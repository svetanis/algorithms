package com.svetanis.algorithms.string;

import java.util.HashMap;
import java.util.Map;

// 2506. Count Pairs Of Similar Strings

public final class CountPairsOfSimilarStrings {
	// Time Complexity: O(n*k)
	// Space Complexity: O(n)

	public static int similarPairs(String[] words) {
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (String word : words) {
			int bitmask = 0;
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				bitmask |= 1 << (c - 'a');
			}
			count += map.getOrDefault(bitmask, 0);
			map.merge(bitmask, 1, Integer::sum);
		}
		return count;
	}

	public static void main(String[] args) {
		String[] w1 = { "aba", "aabb", "abcd", "bac", "aabc" };
		System.out.println(similarPairs(w1)); // 2
		String[] w2 = { "aabb", "ab", "ba" };
		System.out.println(similarPairs(w2)); // 3
		String[] w3 = { "nba", "cba", "dba" };
		System.out.println(similarPairs(w3)); // 0
	}
}
