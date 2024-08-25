package com.svetanis.algorithms.search.topk;

import static com.google.common.collect.Maps.newHashMap;
import static com.svetanis.java.base.collect.Maps.newMap;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableMap;

// given an array of numbers and a number k
// remove k numbers from the array such that 
// we are left with max distinct numbers

public final class MaxDistinctElements {

	public static int maxDistinct(int[] a, int k) {
		// Time Complexity: O(n * log n + k * log n)

		int count = 0;
		Map<Integer, Integer> map = frequencyMap(a);
		Comparator<Map.Entry<Integer, Integer>> c = (e1, e2) -> e1.getValue() - e2.getValue();
		Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(c);
		// insert all numbers with frequency greater than 1 into min heap
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				count++;
			} else {
				pq.add(entry);
			}
		}
		// remove the least frequent numbers
		// first from min-heap
		while (!pq.isEmpty()) {
			Map.Entry<Integer, Integer> entry = pq.poll();
			// remove all of the occurrences except 1
			k -= entry.getValue() - 1;
			if (k == 1) {
				count++;
			}
		}
		// if k > 0, remove some distinct numbers
		if (k > 0) {
			count -= k;
		}
		return count;
	}

	private static ImmutableMap<Integer, Integer> frequencyMap(int[] a) {
		Map<Integer, Integer> map = newHashMap();
		for (int i = 0; i < a.length; i++) {
			int freq = map.getOrDefault(a[i], 0);
			map.put(a[i], freq);
		}
		return newMap(map);
	}

	public static void main(String[] args) {
		int[] a1 = { 7, 3, 5, 8, 5, 3, 3 };
		System.out.println(maxDistinct(a1, 2));
		int[] a2 = { 3, 5, 12, 11, 12 };
		System.out.println(maxDistinct(a2, 3));
		int[] a3 = { 1, 2, 3, 3, 3, 4, 4, 5, 5, 5 };
		System.out.println(maxDistinct(a3, 2));
	}
}