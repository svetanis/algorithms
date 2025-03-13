package com.svetanis.algorithms.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 244. Shortest Word Distance II

public final class ShortestWordDistanceII {
	// Time Complexity: O(n)

	private Map<String, List<Integer>> map = new HashMap<>();

	public ShortestWordDistanceII(String[] dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			map.computeIfAbsent(dictionary[i], k -> new ArrayList<>()).add(i);
		}
	}

	public int shortestDist(String w1, String w2) {
		List<Integer> indices1 = map.get(w1);
		List<Integer> indices2 = map.get(w2);
		int min = Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		while (i < indices1.size() && j < indices2.size()) {
			int index1 = indices1.get(i);
			int index2 = indices2.get(j);
			int diff = Math.abs(index1 - index2);
			min = Math.min(min, diff);
			if (index1 <= index2) {
				i++;
			} else {
				j++;
			}
		}
		return min;
	}

	public static void main(String[] args) {
		String[] dictionary = { "the", "quick", "brown", "fox", "quick" };
		ShortestWordDistanceII swd = new ShortestWordDistanceII(dictionary);
		System.out.println(swd.shortestDist("brown", "quick")); // 1

		String[] dictionary1 = { "practice", "makes", "perfect", "coding", "makes" };
		ShortestWordDistanceII swd1 = new ShortestWordDistanceII(dictionary1);
		System.out.println(swd1.shortestDist("coding", "practice")); // 3
		System.out.println(swd1.shortestDist("makes", "coding")); // 1
	}
}
