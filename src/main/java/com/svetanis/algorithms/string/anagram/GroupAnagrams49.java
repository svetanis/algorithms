package com.svetanis.algorithms.string.anagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.svetanis.java.base.utils.Print;

// 49. Group Anagrams
// com.svetanis.algorithms.string.anagram.GroupAnagramsMultimap

public final class GroupAnagrams49 {
	// Time Complexity: O(m * n log n)
	// Space Complexity: O(m * n)

	public static List<List<String>> group(String[] words) {
		Map<String, List<String>> map = asMap(words);
		return new ArrayList<>(map.values());
	}

	private static Map<String, List<String>> asMap(String[] words) {
		Map<String, List<String>> map = new HashMap<>();
		for (String word : words) {
			String sorted = sort(word);
			map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(word);
		}
		return map;
	}

	private static String sort(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return String.valueOf(chars);
	}

	public static void main(String[] args) {
		String[] a1 = { "eat", "tea", "tan", "ate", "nat", "bat" };
		Print.print(group(a1)); // [eat, tea, ate] [bat] [tan, nat]
		String[] a2 = { "" };
		Print.print(group(a2)); // []
		String[] a3 = { "a" };
		Print.print(group(a3)); // [a]
	}
}