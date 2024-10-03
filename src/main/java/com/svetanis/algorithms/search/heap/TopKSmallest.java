package com.svetanis.algorithms.search.heap;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// given a list of numbers
// return top 3 smallest number

public final class TopKSmallest {
	// Time Complexity: O(k log n)

	public static List<Integer> topK(int k, List<Integer> list) {
		Queue<Integer> pq = new PriorityQueue<>();
		for (int element : list) {
			pq.add(element);
		}
		List<Integer> sorted = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			sorted.add(pq.poll());
		}
		return sorted;
	}

	public static void main(String[] args) {
		List<Integer> list = asList(3, 1, 2, 10, 33, 100, 20);
		System.out.println(topK(3, list));
	}
}