package com.svetanis.algorithms.string.anagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.svetanis.java.base.utils.Print;

// 49. Group Anagrams
// com.svetanis.algorithms.string.anagram.GroupAnagramsMultimap
// com.svetanis.algorithms.string.anagram.GroupAnagrams49

public final class GroupAnagrams49Simple {
	// Time Complexity: O(m * n log n)
	// Space Complexity: O(m * n)

	public static List<List<String>> group(String[] words) {
		Map<String, List<String>> map = new HashMap<>();
		for (String word : words) {
			int[] chars = freq(word);
			String key = key(chars);
			map.computeIfAbsent(key, v -> new ArrayList<>()).add(word);
		}
		return new ArrayList<>(map.values());
	}

	private static String key(int[] chars) {
		StringBuilder sb = new StringBuilder();
		for (int count : chars) {
			sb.append("#");
			sb.append(count);
		}
		return sb.toString();
	}

	private static int[] freq(String s) {
		int[] chars = new int[26];
		for (char c : s.toCharArray()) {
			chars[c - 'a']++;
		}
		return chars;
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