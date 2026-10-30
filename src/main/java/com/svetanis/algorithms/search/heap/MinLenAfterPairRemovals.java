package com.svetanis.algorithms.search.heap;

import static java.util.Arrays.asList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// 2856. Minimum Array Length After Pair Removals
//
// nums is sorted in non-decreasing order. One operation picks i < j with nums[i] < nums[j] and
// removes both. Returns the smallest length the array can be brought down to.
//
// Because the array is sorted, any two DIFFERENT values can be removed together -- the smaller one
// always sits at the smaller index. So only the count of each value matters. Pair the two most
// frequent values each turn: the value that could be left over at the end is the most frequent
// one, and spending it as early as possible leaves the fewest. A max-heap of counts names them.
//
// Time: O(n log d), d the count of distinct values -- at most n / 2 turns on a heap of d counts
// Space: O(d) for the counts and the heap
//
// Siblings, same problem:
//   todo/binaryinvariant/MinLenAfterPairRemovalsBinary -- O(log n): only a value filling more than
//     half the list can be left over, and it must sit in the middle; two binary searches count it

public final class MinLenAfterPairRemovals {

	public static int minLen(List<Integer> list) {
		Map<Integer, Integer> map = frequencies(list);
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // MAX-HEAP: the most frequent value's count on top
		pq.addAll(map.values());                   // PUSH: one count per distinct value
		int min = list.size();
		while (pq.size() > 1) {
			int first = pq.poll() - 1;             // PAIR: one of the most frequent value ...
			int second = pq.poll() - 1;            // PAIR: ... with one of the next most frequent
			if (first > 0) {
				pq.offer(first);                   // PUSH: what is left of the first value
			}
			if (second > 0) {
				pq.offer(second);                  // PUSH: what is left of the second value
			}
			min -= 2;                              // REMOVE: the pair leaves the array
		}
		return min;                                // REPORT: at most one distinct value is left
	}

	private static Map<Integer, Integer> frequencies(List<Integer> list) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : list) {
			map.put(num, map.getOrDefault(num, 0) + 1); // COUNT: occurrences of each value
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
