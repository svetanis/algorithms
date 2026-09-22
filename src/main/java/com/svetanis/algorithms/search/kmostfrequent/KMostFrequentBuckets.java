package com.svetanis.algorithms.search.kmostfrequent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 347. Top K Frequent Elements

// the O(n) answer -- no heap and no sort

// self-contained: no java-base, no Guava -- submittable to LeetCode as it stands

public final class KMostFrequentBuckets {
	// Time Complexity: O(n)

	public static int[] topKFrequent(int[] a, int k) {
		Map<Integer, Integer> map = frequencyMap(a);
		List<Integer>[] buckets = buckets(map, a.length);
		return topK(buckets, k);
	}

	private static Map<Integer, Integer> frequencyMap(int[] a) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int element : a) {
			int freq = map.getOrDefault(element, 0);
			map.put(element, freq + 1);
		}
		return map;
	}

	// the COUNT is the index, never the value -- so negative input needs no
	// offset and the value range in the constraints never enters into it.
	// A count runs from 1 to a.length, hence length + 1 slots.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static List<Integer>[] buckets(Map<Integer, Integer> map, int size) {
		List<Integer>[] buckets = new List[size + 1];
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int count = entry.getValue();
			// allocated on demand: at most one slot per DISTINCT value is ever used
			if (buckets[count] == null) {
				buckets[count] = new ArrayList<>();
			}
			// two values can share a count, so a slot is a list
			buckets[count].add(entry.getKey());
		}
		return buckets;
	}

	// walk from the highest count downwards, stopping the moment k are collected
	private static int[] topK(List<Integer>[] buckets, int k) {
		int[] answer = new int[k];
		int index = 0;
		for (int i = buckets.length - 1; i >= 0 && index < k; i--) {
			if (buckets[i] == null) {
				continue;
			}
			for (int j = 0; j < buckets[i].size() && index < k; j++) {
				answer[index++] = buckets[i].get(j);
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 1, 2, 2, 3 };
		System.out.println(java.util.Arrays.toString(topKFrequent(a1, 2))); // 1, 2

		int[] a2 = { 1 };
		System.out.println(java.util.Arrays.toString(topKFrequent(a2, 1))); // 1

		int[] a3 = { 1, 2, 1, 2, 1, 2, 3, 1, 3, 2 };
		System.out.println(java.util.Arrays.toString(topKFrequent(a3, 2))); // 1, 2
	}
}
