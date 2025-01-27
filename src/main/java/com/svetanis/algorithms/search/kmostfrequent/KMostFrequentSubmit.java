package com.svetanis.algorithms.search.kmostfrequent;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.svetanis.java.base.utils.Print;

// 347. Top K Frequent Elements

// given an unsorted array of numbers,
// find the top k frequently occurring numbers in it

public final class KMostFrequentSubmit {
	// Time Complexity: O(n + n * log k)

	public static int[] kMostFrequent(int[] a, int k) {
		Map<Integer, Long> map = frequencyMap(a);
		Queue<Map.Entry<Integer, Long>> pq = priorityQueue(map, k);
		return pq.stream().mapToInt(Map.Entry::getKey).toArray();
	}

	private static Map<Integer, Long> frequencyMap(int[] a) {
		Map<Integer, Long> map = new HashMap<>();
		for (int element : a) {
			long freq = map.getOrDefault(element, 0L);
			map.put(element, freq + 1);
		}
		return map;
	}

	private static Queue<Map.Entry<Integer, Long>> priorityQueue(Map<Integer, Long> map, int k) {
		// go through all numbers of the map and push them in the min heap
		// which will have top k frequent numbers. If the heap size is
		// more than k, remove the smallest top entry
		Comparator<Map.Entry<Integer, Long>> c = Comparator.comparingLong(Map.Entry::getValue);
		Queue<Map.Entry<Integer, Long>> pq = new PriorityQueue<>(c);
		for (Map.Entry<Integer, Long> entry : map.entrySet()) {
			pq.offer(entry);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq;
	}

	public static void main(String[] args) {
		int k = 2;
		int[] a1 = { 1, 3, 5, 12, 11, 12, 11 };
		Print.print(kMostFrequent(a1, k));

		int[] a2 = { 5, 12, 11, 3, 11 };
		Print.print(kMostFrequent(a2, k));
	}
}
