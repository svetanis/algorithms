package com.svetanis.algorithms.slidingwindow.string.anagram;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

// 438. Find All Anagrams in a String

// Given a string and a pattern, find all anagrams 
// of the pattern in the given string.

public final class StringAnagrams {
	// Time complexity: O(n)

	public static List<Integer> anagrams(String s, String p) {
		int n = s.length();
		int left = 0;
		int matched = 0;
		List<Integer> list = new ArrayList<>();
		Map<Character, Integer> map = frequencies(p);
		for (int right = 0; right < n; right++) {
			char end = s.charAt(right);
			if (map.containsKey(end)) {
				map.put(end, map.get(end) - 1);
				if (map.get(end) == 0) {
					matched++;
				}
			}
			if (matched == map.size()) {
				list.add(left);
			}
			if (right >= p.length() - 1) {
				char start = s.charAt(left++);
				if (map.containsKey(start)) {
					if (map.get(start) == 0) {
						matched--;
					}
					map.put(start, map.get(start) + 1);
				}
			}
		}
		return list;
	}

	private static Map<Character, Integer> frequencies(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		ImmutableList<Pair<String, String>> pairs = pairs();
		for (Pair<String, String> pair : pairs) {
			print(anagrams(pair.getLeft(), pair.getRight()));
		}
	}

	private static ImmutableList<Pair<String, String>> pairs() {
		List<Pair<String, String>> list = newArrayList();
		list.add(Pair.build("ppqp", "pq")); // 1, 2
		list.add(Pair.build("abbcabc", "abc")); // 2, 3, 4
		list.add(Pair.build("cbaebabacd", "abc")); // 0, 6
		list.add(Pair.build("abab", "ab")); // 0, 1, 2
		list.add(Pair.build("BACDGABCDA", "ABCD")); // 0, 5, 6
		list.add(Pair.build("AAABABAA", "AABA")); // 0, 1, 4
		return newList(list);
	}

}
