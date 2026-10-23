package com.svetanis.algorithms.slidingwindow.string;

import static java.util.Arrays.asList;

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
// Returns the indices in increasing order. The list of words must not be
// empty; an empty list throws.

// The one idea: a match starting at i is m words of length k laid end to end,
// so try every start i and read the words at i, i + k, i + 2k, ... Stop at the
// first word that is not in the list, or that appears more times than the
// list holds it; reaching the m-th word means every word was used exactly once.

// Siblings:
//   WordsConcatenationSubmit -- splits s into k lanes by start position and
//     runs one sliding window of whole words along each lane, so no word is
//     read twice from the same start; O(n * k) instead of O(n * m * k), and
//     the indices come out lane by lane rather than in increasing order.

// Time: O(n * m * k) -- n starts, up to m words each, each word copied in k steps.
// Space: O(m * k + n) -- two maps of at most m words of k letters, plus the
//   indices returned.

public final class WordsConcatenation {

	public static List<Integer> wordConcat(String s, List<String> words) {
		int n = s.length();
		int m = words.size();
		int k = words.iterator().next().length();
		List<Integer> list = new ArrayList<>();
		Map<String, Integer> freq = freqMap(words);
		for (int i = 0; i <= n - m * k; i++) { // START: every place m words could begin
			Map<String, Integer> map = new HashMap<>();
			for (int j = 0; j < m; j++) {
				int next = i + j * k;
				String word = s.substring(next, next + k); // READ: the j-th word from i
				if (!freq.containsKey(word)) {
					break; // STOP: not one of the words
				}
				map.put(word, map.getOrDefault(word, 0) + 1);
				if (map.get(word) > freq.getOrDefault(word, 0)) {
					break; // STOP: used more times than the list holds it
				}
				if (j + 1 == m) {
					list.add(i); // RECORD: all m words used exactly once
				}
			}
		}
		return list;
	}

	// how many times each word appears in the list
	private static Map<String, Integer> freqMap(List<String> words) {
		Map<String, Integer> map = new LinkedHashMap<>();
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		String s1 = "catfoxcat";
		String s2 = "catcatfoxfox";
		List<String> words = asList("cat", "fox");
		System.out.println(wordConcat(s1, words)); // 0,3
		System.out.println(wordConcat(s2, words)); // 3
	}
}
