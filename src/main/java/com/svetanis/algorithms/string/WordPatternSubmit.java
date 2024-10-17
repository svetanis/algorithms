package com.svetanis.algorithms.string;

import java.util.HashMap;
import java.util.Map;

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

public final class WordPatternSubmit {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static boolean wordPattern(String p, String s) {
		char[] chars = p.toCharArray();
		String[] words = s.split(" ");
		if (chars.length != words.length) {
			return false;
		}
		Map<Character, String> map = new HashMap<>();
		Map<String, Character> reversed = new HashMap<>();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			String word = words[i];
			boolean one = map.containsKey(c) && !map.get(c).equals(word);
			boolean two = reversed.containsKey(word) && !reversed.get(word).equals(c);
			if (one || two) {
				return false;
			} else {
				map.put(c, word);
				reversed.put(word, c);
			}
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
