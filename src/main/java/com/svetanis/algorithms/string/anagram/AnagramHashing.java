package com.svetanis.algorithms.string.anagram;

import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.filterValues;
import static com.google.common.collect.Maps.newHashMap;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.svetanis.java.base.Pair;

public final class AnagramHashing {

	public static boolean areAnagrams(String str1, String str2) {
		// Time complexity: O(n)

		if (str1.length() != str2.length()) {
			return false;
		}
		Map<Character, Integer> map = newHashMap();
		for (int i = 0; i < str1.length(); i++) {
			char c1 = str1.charAt(i);
			char c2 = str2.charAt(i);
			map.put(c1, map.getOrDefault(c1, 0) + 1);
			map.put(c2, map.getOrDefault(c2, 0) - 1);
		}
		Map<Character, Integer> filtered = filterValues(map, v -> v == 0);
		return map.keySet().size() == filtered.keySet().size();
	}

	public static void main(String[] args) {
		ImmutableList<Pair<String, String>> pairs = pairs();
		for (Pair<String, String> pair : pairs) {
			String w1 = pair.getLeft();
			String w2 = pair.getRight();
			System.out.println(areAnagrams(w1, w2));
		}
	}

	private static ImmutableList<Pair<String, String>> pairs() {
		List<Pair<String, String>> list = newArrayList();
		list.add(Pair.build("anagramm", "nagaramm")); // true
		list.add(Pair.build("aaca", "aca")); // false
		list.add(Pair.build("apple", "papel")); // true
		list.add(Pair.build("carrot", "tarroc")); // true
		list.add(Pair.build("hello", "llloh")); // false
		return copyOf(list);
	}
}
