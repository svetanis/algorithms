package com.svetanis.algorithms.string.search;

import java.util.HashMap;
import java.util.Map;

// 290. Word Pattern
//
// Input: a pattern p of letters and a string s of words. s follows p when each letter stands for
// exactly one word and each word is stood for by exactly one letter -- a one-to-one match in both
// directions. Returns true if s follows p. On LeetCode: 1 <= p.length <= 300, p is lowercase
// letters; 1 <= s.length <= 3000, s is lowercase letters and single spaces, with no space at the
// start or end.
//
// The one idea: walk the pattern and the words together with two maps. One entry of map is a
// letter and the word it stands for; one entry of reversed is a word and the letter that stands
// for it. A letter seen before must meet the same word again, and a word seen before must meet
// the same letter again -- two different letters on one word ("a" and "b" both "dog") break the
// second half, which is why one map is not enough.
//
// Time: O(p + s) -- p = p.length, s = s.length; one step per word, hashing a word costs its
// length.
// Space: O(s) -- the words and the two maps.
//
// Siblings: WordPattern keeps the two maps as one Guava BiMap.

public final class WordPatternSubmit {

	public static boolean wordPattern(String p, String s) {
		char[] chars = p.toCharArray();
		String[] words = s.split(" ");
		if (chars.length != words.length) {
			return false; // BASE: one letter per word
		}
		Map<Character, String> map = new HashMap<>();
		Map<String, Character> reversed = new HashMap<>();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			String word = words[i];
			boolean one = map.containsKey(c) && !map.get(c).equals(word); // LETTER: seen with another word
			boolean two = reversed.containsKey(word) && !reversed.get(word).equals(c); // WORD: seen with another letter
			if (one || two) {
				return false; // RETURN: the match is not one-to-one
			} else {
				map.put(c, word); // RECORD: both directions, together
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
