package com.svetanis.algorithms.search.topk;

import static java.util.Comparator.comparingInt;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

// Maximum Distinct Elements
//
// Takes an array of numbers and k, 0 <= k <= n. Remove exactly k numbers so that as many
// numbers as possible are distinct, where distinct means the number appears exactly once in
// what is left. Returns that largest count.
//
// A number with c copies costs c - 1 removals to become distinct, so spend the removals on the
// cheapest ones first: a min-heap on the copy count, numbers that already appear once counted
// straight away and kept out of the heap. When the removals run out part-way through a number,
// that number stays repeated. If removals are still left once every number is distinct, each
// one has to delete a distinct number, so the count drops by one per removal.
//
// Siblings: none in this package. LC 1481 (data-structures' hashmap/MinimizeUniqueIntsKRemovals)
// is a different question: it counts distinct VALUES and makes that number as small as possible.

public final class MaxDistinctElements {

	public static int maxDistinct(int[] a, int k) {
		// Time Complexity: O(n + d log d) -- one counting pass, then each of the d different
		// numbers enters the heap once and leaves it at most once
		// Space Complexity: O(d), the map and the heap

		int count = 0;
		Map<Integer, Integer> map = frequencyMap(a); // COUNT copies of each number
		Comparator<Map.Entry<Integer, Integer>> c = comparingInt(e -> e.getValue()); // ORDER: fewest copies first
		Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(c);
		// insert all numbers with frequency greater than 1 into min heap
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1) {
				count++; // DISTINCT already, costs nothing
			} else {
				pq.add(entry); // PUSH: a repeated number, to be bought later
			}
		}
		// remove the least frequent numbers
		// first from min-heap, while removals are left
		while (k > 0 && !pq.isEmpty()) {
			Map.Entry<Integer, Integer> entry = pq.poll(); // POLL the cheapest repeated number
			// remove all of the occurrences except 1
			k -= entry.getValue() - 1; // SPEND c - 1 removals
			// k went negative: not enough removals left to
			// make this number distinct
			if (k >= 0) {
				count++; // DISTINCT now, paid in full
			}
		}
		// if k > 0, remove some distinct numbers
		if (k > 0) {
			count -= k; // LEFTOVER removals each delete a distinct number
		}
		return count; // REPORT
	}

	private static Map<Integer, Integer> frequencyMap(int[] a) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			int freq = map.getOrDefault(a[i], 0);
			map.put(a[i], freq + 1); // COUNT: one more copy
		}
		return map;
	}

	public static void main(String[] args) {
		int[] a1 = { 7, 3, 5, 8, 5, 3, 3 };
		System.out.println(maxDistinct(a1, 2)); // 3
		int[] a2 = { 3, 5, 12, 11, 12 };
		System.out.println(maxDistinct(a2, 3)); // 2
		int[] a3 = { 1, 2, 3, 3, 3, 4, 4, 5, 5, 5 };
		System.out.println(maxDistinct(a3, 2)); // 3
	}
}