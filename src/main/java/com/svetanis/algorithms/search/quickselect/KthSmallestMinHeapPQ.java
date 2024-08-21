package com.svetanis.algorithms.search.quickselect;

import java.util.PriorityQueue;
import java.util.Queue;

// given an unsorted array of numbers,
// find Kth smallest number in it

public final class KthSmallestMinHeapPQ {

	public static int kthSmallest(int[] a, int k) {
		// Time Complexity: O(n + k log n)

		Queue<Integer> pq = new PriorityQueue<Integer>((x, y) -> x - y);
		// 1. insert all the numbers in the min-heap
		for (int element : a) {
			pq.add(element);
		}
		// 2. extract min k - 1 times
		for (int i = 0; i < k - 1; i++) {
			pq.poll();
		}
		// 3. return k-smallest number
		return pq.peek();
	}

	public static void main(String[] args) {
		int[] a = { 12, 3, 5, 7, 19 };
		System.out.println(kthSmallest(a, 2));

		int[] a1 = { 2, 1, 4, 3, 2 };
		System.out.println(kthSmallest(a1, 3));

		int[] a2 = { 1, 5, 12, 2, 11, 5 };
		System.out.println(kthSmallest(a2, 3)); // 5

		int[] a3 = { 1, 5, 12, 2, 11, 5 };
		System.out.println(kthSmallest(a3, 4)); // 5

		int[] a4 = { 5, 12, 11, -1, 12 };
		System.out.println(kthSmallest(a4, 3)); // 11
	}
}