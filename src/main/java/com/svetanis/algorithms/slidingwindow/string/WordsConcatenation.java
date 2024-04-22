package com.svetanis.algorithms.slidingwindow.string;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Maps.freqMap;
import static java.util.Arrays.asList;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

// given a string and a list of words, 
// find all the starting indices of substrings
// in the given string that are a concatenation
// of all the given words exactly once
// without any overlapping of words
// all words are of the same length

public final class WordsConcatenation {
	// Time complexity: O(n * m * k)

	public static ImmutableList<Integer> wordConcat(String s, List<String> words) {
		int n = s.length();
		int m = words.size();
		int k = words.iterator().next().length();

		List<Integer> list = newArrayList();
		Map<String, Integer> freq = freqMap(words);
		for (int i = 0; i <= n - m * k; i++) {
			Map<String, Integer> map = newHashMap();
			for (int j = 0; j < m; j++) {
				int next = i + j * k;
				// next word from the string
				String word = s.substring(next, next + k);
				if (!freq.containsKey(word)) {
					break;
				}
				map.put(word, map.getOrDefault(word, 0) + 1);

				// no need to process further if the word
				// has higher frequency than required
				if (map.get(word) > freq.getOrDefault(word, 0)) {
					break;
				}
				// store index if we have found all the words
				if (j + 1 == m) {
					list.add(i);
				}
			}
		}
		return newList(list);
	}

	public static void main(String[] args) {
		String s1 = "catfoxcat";
		String s2 = "catcatfoxfox";
		List<String> words = asList("cat", "fox");
		System.out.println(wordConcat(s1, words)); // 0,3
		System.out.println(wordConcat(s2, words)); // 3
	}
}
