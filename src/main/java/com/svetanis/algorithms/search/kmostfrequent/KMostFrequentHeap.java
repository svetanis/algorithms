package com.svetanis.algorithms.search.kmostfrequent;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.collect.Maps.newMap;
import static java.util.Comparator.comparingInt;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

// 347. Top K Frequent Elements

// given an unsorted array of numbers,
// find the top k frequently occurring numbers in it

// the heap holds the value alone; KMostFrequentEntryHeap holds the whole Map.Entry

public final class KMostFrequentHeap {
	// Time Complexity: O(n + n * log k)

	public static ImmutableList<Integer> kMostFrequent(List<Integer> terms, int k) {
		Map<Integer, Integer> map = frequencyMap(terms);
		Queue<Integer> pq = priorityQueue(map, k);
		return kMostFrequent(pq);
	}

	// polling a min-heap keyed on the count yields LEAST frequent first, so this
	// list runs from least to most frequent. LC 347 accepts any order.
	private static ImmutableList<Integer> kMostFrequent(Queue<Integer> pq) {
		List<Integer> list = newArrayList();
		while (!pq.isEmpty()) {
			list.add(pq.poll());
		}
		return newList(list);
	}

	private static Queue<Integer> priorityQueue(Map<Integer, Integer> map, int k) {
		// go through all numbers of the map and push them in the min heap
		// which will have top k frequent numbers. If the heap size is
		// more than k, remove the smallest top entry
		// the heap holds the NUMBER alone; the comparator reads its count from the map.
		// The sibling KMostFrequentEntryHeap carries the whole Map.Entry instead.
		// Ordered by ASCENDING count, so the top is the one being thrown away.
		Comparator<Integer> c = comparingInt(x -> map.get(x));
		Queue<Integer> pq = new PriorityQueue<>(c);
		for (int key : map.keySet()) {
			pq.add(key);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return pq;
	}

	private static ImmutableMap<Integer, Integer> frequencyMap(List<Integer> list) {
		Map<Integer, Integer> map = newHashMap();
		for (int element : list) {
			int freq = map.getOrDefault(element, 0);
			map.put(element, freq + 1);
		}
		return newMap(map);
	}

	public static void main(String[] args) {
		int k = 2;
		List<Integer> list1 = newArrayList(1, 3, 5, 12, 11, 12, 11);
		System.out.println(kMostFrequent(list1, k));

		List<Integer> list2 = newArrayList(5, 12, 11, 3, 11);
		System.out.println(kMostFrequent(list2, k));
	}
}
