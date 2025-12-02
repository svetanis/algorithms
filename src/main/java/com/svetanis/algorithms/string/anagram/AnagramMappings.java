package com.svetanis.algorithms.string.anagram;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.svetanis.java.base.utils.Print;

// 760. Find Anagram Mappings

public final class AnagramMappings {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int[] anagramMappings(int[] a1, int[] a2) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a2.length; i++) {
			map.put(a2[i], i);
		}
		int[] mapping = new int[a1.length];
		for (int i = 0; i < a1.length; i++) {
			mapping[i] = map.get(a1[i]);
		}
		return mapping;
	}

	public static int[] mappings(int[] a1, int[] a2) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < a2.length; i++) {
			map.computeIfAbsent(a2[i], k -> new HashSet<>()).add(i);
		}
		int[] mapping = new int[a1.length];
		for (int i = 0; i < a1.length; i++) {
			Set<Integer> set = map.get(a1[i]);
			int index = set.iterator().next();
			mapping[i] = index;
			set.remove(index);
		}
		return mapping;
	}

	public static void main(String[] args) {
		int[] a1 = { 12, 28, 46, 32, 50 };
		int[] a2 = { 50, 12, 32, 46, 28 };
		Print.print(mappings(a1, a2)); // 1 4 3 2 0
	}
}
