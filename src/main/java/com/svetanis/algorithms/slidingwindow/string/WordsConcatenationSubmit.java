package com.svetanis.algorithms.slidingwindow.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// 30. Substring with Concatenation of All Words

// given a string and a list of words, 
// find all the starting indices of substrings
// in the given string that are a concatenation
// of all the given words exactly once
// without any overlapping of words
// all words are of the same length

public final class WordsConcatenationSubmit {
	// Time complexity: O(m * k)
	// Space complexity: O(n * k)

	public static List<Integer> wordConcat(String s, String[] words) {
		int n = s.length();
		int m = words.length;
		int k = words[0].length();
		List<Integer> list = new ArrayList<>();
		Map<String, Integer> freq = freqMap(words);
		for (int i = 0; i < k; i++) {
			int left = i;
			int right = i;
			int total = 0;
			Map<String, Integer> map = new HashMap<>();
			while (right + k <= n) {
				String word = s.substring(right, right + k);
				right += k;
				if (!freq.containsKey(word)) {
					map.clear();
					left = right;
					total = 0;
					continue;
				}
				map.put(word, map.getOrDefault(word, 0) + 1);
				total++;
				while (map.get(word) > freq.get(word)) {
					String removed = s.substring(left, left + k);
					left += k;
					map.put(removed, map.getOrDefault(removed, 0) - 1);
					total--;
				}
				if (total == m) {
					list.add(left);
				}
			}
		}
		return list;
	}

	private static Map<String, Integer> freqMap(String[] words) {
		Map<String, Integer> map = new LinkedHashMap<>();
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		String[] w1 = { "foo", "bar" };
		System.out.println(wordConcat("barfoothefoobarman", w1)); // 0,9
		String[] w2 = { "word", "good", "best", "word" };
		System.out.println(wordConcat("wordgoodgoodgoodbestword", w2)); // []
		String[] w3 = { "bar", "foo", "the" };
		System.out.println(wordConcat("barfoofoobarthefoobarman", w3)); // 6,9,12
	}
}
