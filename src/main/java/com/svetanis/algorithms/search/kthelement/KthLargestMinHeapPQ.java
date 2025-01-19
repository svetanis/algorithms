package com.svetanis.algorithms.search.kthelement;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 215. Kth Largest Element in an Array

// given an unsorted array of numbers,
// find Kth largest number in it

public final class KthLargestMinHeapPQ {
	// Time Complexity: O(n log k)

	public static int kthLargest(List<Integer> list, int k) {
		Queue<Integer> pq = priorityQueue(list, k);
		// for the remaining numbers of the array,
		// if the number from the array is larger
		// than the top (smallest) number of the heap,
		// remove the top number from the heap
		// and add the number from the array
		for (int i = k; i < list.size(); i++) {
			int curr = list.get(i);
			if (curr > pq.peek()) {
				pq.poll();
				pq.add(curr);
			}
		}
		return pq.peek();
	}

	private static Queue<Integer> priorityQueue(List<Integer> list, int k) {
		Queue<Integer> pq = new PriorityQueue<Integer>(k, (x, y) -> x - y);
		// put first k numbers in the min heap
		for (int i = 0; i < k; i++) {
			pq.add(list.get(i));
		}
		return pq;
	}

	public static int kthLargestSingleLoop(List<Integer> list, int k) {
		Queue<Integer> pq = new PriorityQueue<Integer>(k, (x, y) -> x - y);
		for (int element : list) {
			if (pq.size() < k || element > pq.peek()) {
				if (pq.size() == k) {
					pq.poll();
				}
				pq.add(element);
			}
		}
		return pq.peek();
	}

	public static void main(String[] args) {
		System.out.println(kthLargest(asList(12, 3, 5, 7, 19), 5)); // 3
		System.out.println(kthLargest(asList(12, 3, 5, 7, 19), 2)); // 12
		System.out.println(kthLargest(asList(10, 2, 4, 6, 8, 1, 3, 5, 7, 10, 128), 4)); // 8
		System.out.println(kthLargest(asList(3, 2, 1, 5, 6, 4), 2)); // 5
		System.out.println(kthLargest(asList(3, 2, 3, 1, 2, 4, 5, 5, 6), 4)); // 4
	}
}