package com.svetanis.algorithms.string.search;

import java.util.List;
import java.util.Map;

import com.google.common.base.Splitter;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

// 290. Word Pattern
//
// Input: a pattern p of letters and a string s of words. s follows p when each letter stands for
// exactly one word and each word is stood for by exactly one letter -- a one-to-one match in both
// directions. Returns true if s follows p. On LeetCode: 1 <= p.length <= 300, p is lowercase
// letters; 1 <= s.length <= 3000, s is lowercase letters and single spaces, with no space at the
// start or end.
//
// The one idea: walk the pattern and the words together; one entry of the map is a letter and the
// word it stands for. A letter seen before must meet the same word again, and a word seen before
// must meet the same letter again -- two different letters on one word ("a" and "b" both
// "dog") break the second half. A Guava BiMap holds the letter-to-word map and its reverse as
// one object, so inverse() gives the second check without a second map to keep in step.
//
// Time: O(p + s) -- p = p.length, s = s.length; one step per word, hashing a word costs its
// length.
// Space: O(s) -- the words and the map.
//
// Siblings: WordPatternSubmit is the same method with two HashMaps and no Guava (the one to
// submit). This file is kept for the BiMap.

public final class WordPattern {

	public static boolean wordPattern(String p, String s) {
		List<String> words = Splitter.on(" ").splitToList(s);
		if (p.toCharArray().length != words.size()) {
			return false; // BASE: one letter per word
		}
		BiMap<Character, String> map = HashBiMap.create();
		for (int i = 0; i < words.size(); i++) {
			char c = p.charAt(i);
			String word = words.get(i);
			Map<String, Character> inverse = map.inverse();
			boolean one = map.containsKey(c) && !map.get(c).equals(word); // LETTER: seen with another word
			boolean two = inverse.containsKey(word) && !inverse.get(word).equals(c); // WORD: seen with another letter
			if (one || two) {
				return false; // RETURN: the match is not one-to-one
			}
			map.put(c, word); // RECORD: letter and word now stand for each other
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
