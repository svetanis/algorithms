package com.svetanis.algorithms.search.quickselect;

import java.util.PriorityQueue;
import java.util.Queue;

// given an unsorted array of numbers,
// find Kth largest number in it

public final class KthLargestMinHeapPQ {

	public static int kthLargest(int[] a, int k) {
		// Time Complexity: O(n log k)

		Queue<Integer> pq = new PriorityQueue<Integer>(k, (x, y) -> x - y);
		// put first k numbers in the min heap
		for (int i = 0; i < k; i++) {
			pq.add(a[i]);
		}
		// for the remaining numbers of the array,
		// if the number from the array is larger
		// than the top (smallest) number of the heap,
		// remove the top number from the heap
		// and add the number from the array
		for (int i = k; i < a.length; i++) {
			if (a[i] > pq.peek()) {
				pq.poll();
				pq.add(a[i]);
			}
		}
		return pq.peek();
	}

	public static int kthLargestSingleLoop(int[] a, int k) {
		// Time Complexity: O(n log k)

		Queue<Integer> pq = new PriorityQueue<Integer>(k, (x, y) -> x - y);
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
		int[] a = { 12, 3, 5, 7, 19 };
		System.out.println(kthLargest(a, 5)); // 3
		System.out.println(kthLargest(a, 2)); // 12

		int[] a1 = { 10, 2, 4, 6, 8, 1, 3, 5, 7, 10, 128 };
		System.out.println(kthLargest(a1, 4)); // 8
	}
}