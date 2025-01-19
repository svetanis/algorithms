package com.svetanis.algorithms.search.kthelement;

import java.util.PriorityQueue;
import java.util.Queue;

// 215. Kth Largest Element in an Array

public final class KthLargestMinHeapPQSubmit {
	// Time Complexity: O(n log k)

	public static int kthLargest(int[] a, int k) {
		Queue<Integer> pq = priorityQueue(a, k);
		for (int i = k; i < a.length; i++) {
			int curr = a[i];
			if (curr > pq.peek()) {
				pq.poll();
				pq.add(curr);
			}
		}
		return pq.peek();
	}

	private static Queue<Integer> priorityQueue(int[] a, int k) {
		Queue<Integer> pq = new PriorityQueue<>((x, y) -> x - y);
		// put first k numbers in the min heap
		for (int i = 0; i < Math.min(k, a.length); i++) {
			pq.add(a[i]);
		}
		return pq;
	}

	public static int kthLargestSingleLoop(int[] a, int k) {
		Queue<Integer> pq = new PriorityQueue<Integer>((x, y) -> x - y);
		for (int element : a) {
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
		int[] a1 = { 3, 2, 1, 5, 6, 4 };
		System.out.println(kthLargest(a1, 2)); // 5
		int[] a2 = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
		System.out.println(kthLargest(a2, 4)); // 4
	}
}