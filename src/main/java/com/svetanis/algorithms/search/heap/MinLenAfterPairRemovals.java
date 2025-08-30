package com.svetanis.algorithms.search.heap;

import static java.util.Arrays.asList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// 2856. Minimum Array Length After Pair Removals

public final class MinLenAfterPairRemovals {
	// Time Complexity: O(n log n)

	public static int minLen(List<Integer> list) {
		Map<Integer, Integer> map = frequencies(list);
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		pq.addAll(map.values());
		int min = list.size();
		while (pq.size() > 1) {
			int first = pq.poll() - 1;
			int second = pq.poll() - 1;
			if (first > 0) {
				pq.offer(first);
			}
			if (second > 0) {
				pq.offer(second);
			}
			min -= 2;
		}
		return min;
	}

	private static Map<Integer, Integer> frequencies(List<Integer> list) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : list) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		return map;
	}

	public static void main(String[] args) {
		System.out.println(minLen(asList(1, 2, 3, 4))); // 0
		System.out.println(minLen(asList(1, 1, 2, 2, 3, 3))); // 0
		System.out.println(minLen(asList(1000000000, 1000000000))); // 2
		System.out.println(minLen(asList(2, 3, 4, 4, 4))); // 1
	}
}
