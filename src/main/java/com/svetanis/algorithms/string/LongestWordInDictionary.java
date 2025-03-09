package com.svetanis.algorithms.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 720. Longest Word in Dictionary

public final class LongestWordInDictionary {
	// Time Complexity: O(n log n)

	public static String longestWord(String[] words) {
		Arrays.sort(words, (a, b) -> a.length() - b.length());
		String s = "";
		int max = 0;
		Set<String> set = new HashSet<>();
		for (String word : words) {
			int len = word.length();
			if (len == 1) {
				set.add(word);
			} else if (set.contains(word.substring(0, len - 1))) {
				set.add(word);
			}
			if (set.contains(word)) {
				if (max < len) {
					max = len;
					s = word;
				} else if (max == len && s.compareTo(word) > 0) {
					s = word;
				}
			}
		}
		return s;
	}

	public static void main(String[] args) {
		String[] a = { "w", "wo", "wor", "worl", "world" };
		System.out.println(longestWord(a)); // world
		String[] a2 = { "a", "banana", "app", "appl", "ap", "apply", "apple" };
		System.out.println(longestWord(a2)); // apple
	}
}
