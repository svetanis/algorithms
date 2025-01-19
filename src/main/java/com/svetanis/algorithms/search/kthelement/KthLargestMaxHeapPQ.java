package com.svetanis.algorithms.search.kthelement;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 215. Kth Largest Element in an Array

// given an unsorted array of numbers,
// find Kth largest number in it

public final class KthLargestMaxHeapPQ {
	// Time Complexity: O(n + k log n)

	public static int kthLargest(List<Integer> list, int k) {
		Queue<Integer> pq = new PriorityQueue<Integer>((x, y) -> (y - x));
		// 1. insert all the numbers in the max-heap
		for (int element : list) {
			pq.add(element);
		}
		// 2. extract max k - 1 times
		for (int i = 0; i < k - 1; i++) {
			pq.poll();
		}
		// 3. return k-smallest number
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