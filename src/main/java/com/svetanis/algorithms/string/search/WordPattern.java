package com.svetanis.algorithms.string.search;

import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

// 290. Word Pattern
// given a pattern and a string s,
// find if s follows the same pattern.
// Here follow means a full match, 
// such that there is a bijection between 
// a letter in pattern and a non-empty word in s. 
// Specifically:

// Each letter in pattern maps to exactly one unique word in s.
// Each unique word in s maps to exactly one letter in pattern.
// No two letters map to the same word, 
// and no two words map to the same letter.

public final class WordPattern {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static boolean wordPattern(String p, String s) {
		List<String> words = Splitter.on(" ").splitToList(s);
		if (p.toCharArray().length != words.size()) {
			return false;
		}
		BiMap<Character, String> map = HashBiMap.create();
		for (int i = 0; i < words.size(); i++) {
			char c = p.charAt(i);
			String word = words.get(i);
			Map<String, Character> inverse = map.inverse();
			boolean one = map.containsKey(c) && !map.get(c).equals(word);
			boolean two = inverse.containsKey(word) && !inverse.get(word).equals(c);
			if (one || two) {
				return false;
			}
			map.put(c, word);
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(wordPattern("abba", "dog cat cat dog")); // true
		System.out.println(wordPattern("abba", "dog cat cat fish")); // false
		System.out.println(wordPattern("aaaa", "dog cat cat dog")); // false
		System.out.println(wordPattern("abba", "dog dog dog dog")); // false

	}
}
