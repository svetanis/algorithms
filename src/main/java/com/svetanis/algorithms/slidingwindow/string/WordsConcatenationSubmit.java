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
// The indices come out lane by lane (all starts i, i + k, i + 2k, ... for
// i = 0, then for i = 1, ...), not in increasing order. The array of words
// must not be empty; an empty array throws.

// The one idea: every word has length k, so a match starting at position p
// lines its words up at p, p + k, p + 2k, ... Split the starts into k lanes by
// p mod k; inside one lane, s is just a row of words, and a match is a window
// of exactly m consecutive words with each word used as many times as the
// list holds it. That is a sliding window over words: add the next word; a
// word not in the list breaks every window holding it, so the window restarts
// after it; a word used too often makes the window lose words from the left
// until it is back to its allowed count. When the window holds m words, its
// left end is a match.

// Siblings:
//   WordsConcatenation -- tries every start and reads up to m words from it;
//     simpler, O(n * m * k), and returns the indices in increasing order.

// Time: O(n * k) -- k lanes, each reading about n / k words once on the right
//   and dropping each at most once on the left, every word copied and hashed
//   in k steps.
// Space: O(m * k + n) -- two maps of at most m words of k letters, plus the
//   indices returned.

public final class WordsConcatenationSubmit {

	public static List<Integer> wordConcat(String s, String[] words) {
		int n = s.length();
		int m = words.length;
		int k = words[0].length();
		List<Integer> list = new ArrayList<>();
		Map<String, Integer> freq = freqMap(words);
		for (int i = 0; i < k; i++) { // START: lane i holds the starts i, i + k, i + 2k, ...
			int left = i;
			int right = i;
			int total = 0;
			Map<String, Integer> map = new HashMap<>();
			while (right + k <= n) { // STOP: no whole word left in this lane
				String word = s.substring(right, right + k);
				right += k;
				if (!freq.containsKey(word)) {
					map.clear(); // RESET: no window can hold this word, start after it
					left = right;
					total = 0;
					continue;
				}
				map.put(word, map.getOrDefault(word, 0) + 1); // ADD
				total++;
				while (map.get(word) > freq.get(word)) { // SHRINK: while word is used too often
					String removed = s.substring(left, left + k);
					left += k;
					map.put(removed, map.getOrDefault(removed, 0) - 1);
					total--;
				}
				if (total == m) {
					list.add(left); // RECORD: m words, none used too often, so each exactly once
				}
			}
		}
		return list;
	}

	// how many times each word appears in the array
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
