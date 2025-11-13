package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 1002. Find Common Characters

public final class CommonCharacters {
	// Time Complexity: O(n * m)
	// Space Complexity: O(26)

	public static List<String> commonChars(String[] words) {
		int[] freq = new int[26];
		Arrays.fill(freq, Integer.MAX_VALUE);
		for (String word : words) {
			int[] currWordFreq = new int[26];
			for (int i = 0; i < word.length(); i++) {
				currWordFreq[word.charAt(i) - 'a']++;
			}
			for (int i = 0; i < 26; i++) {
				freq[i] = Math.min(freq[i], currWordFreq[i]);
			}
		}
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			list.addAll(Collections.nCopies(freq[i], Character.toString('a' + i)));
		}
		return list;
	}

	public static void main(String[] args) {
		String[] words1 = { "bella", "label", "roller" };
		System.out.println(commonChars(words1)); // e, l, l

		String[] words2 = { "cool", "lock", "cook" };
		System.out.println(commonChars(words2)); // c, o
	}
}
