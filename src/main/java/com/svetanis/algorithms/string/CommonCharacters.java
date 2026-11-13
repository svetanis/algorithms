package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 1002. Find Common Characters
//
// Input: an array of words. Returns every character that appears in all of the words, as many
// times as it appears in all of them -- a letter that appears 3 times in every word is returned 3
// times -- in any order (this file returns them a to z). On LeetCode: 1 <= words.length <= 100,
// 1 <= words[i].length <= 100, lowercase letters.
//
// The one idea: for one letter, the number of copies that every word can supply is the smallest
// count of that letter over the words: a word with only 1 'l' cannot give a second 'l'. So keep
// one number per letter, the smallest count seen so far, start it at "infinity", and lower it
// word by word. What is left is how many copies of each letter all the words share.
//
// Time: O(total number of characters + 26 * number of words) -- count each word, then compare 26 letters
// Space: O(1) -- two arrays of 26 counts, besides the answer

public final class CommonCharacters {

	public static List<String> commonChars(String[] words) {
		int[] freq = new int[26]; // SMALLEST: fewest copies of each letter in any word so far
		Arrays.fill(freq, Integer.MAX_VALUE);
		for (String word : words) {
			int[] currWordFreq = new int[26]; // COUNT: letters of this word
			for (int i = 0; i < word.length(); i++) {
				currWordFreq[word.charAt(i) - 'a']++;
			}
			for (int i = 0; i < 26; i++) {
				freq[i] = Math.min(freq[i], currWordFreq[i]); // LOWER: a word cannot give more than it has
			}
		}
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			list.addAll(Collections.nCopies(freq[i], Character.toString('a' + i))); // EMIT: freq[i] copies of the letter
		}
		return list;
	}

	public static void main(String[] args) {
		String[] words1 = { "bella", "label", "roller" };
		System.out.println(commonChars(words1)); // [e, l, l]

		String[] words2 = { "cool", "lock", "cook" };
		System.out.println(commonChars(words2)); // [c, o]
	}
}
