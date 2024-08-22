package com.svetanis.algorithms.search.quickselect;

import java.util.PriorityQueue;
import java.util.Queue;

// given an unsorted array of numbers,
// find Kth smallest number in it

public final class KthSmallestMaxHeapPQ {

	public static int kthSmallest(int[] a, int k) {
		// Time Complexity: O(n log k)

		Queue<Integer> pq = priorityQueue(a, k);

		// for the remaining numbers of the array,
		// if the number from the array is smaller
		// than the top (largest) number of the heap,
		// remove the top number from the heap
		// and add the number from the array
		for (int i = k; i < a.length; i++) {
			if (a[i] < pq.peek()) {
				pq.poll();
				pq.add(a[i]);
			}
		}
		return pq.peek();
	}
	
	private static Queue<Integer> priorityQueue(int[] a, int k){
		Queue<Integer> pq = new PriorityQueue<>(k, (x, y) -> (y - x));
		// put first k numbers in the max heap
		for (int i = 0; i < k; i++) {
			pq.add(a[i]);
		}
		return pq;
	}

	public static int kthSmallestSingleLoop(int[] a, int k) {
		// Time Complexity: O(n log k)

		Queue<Integer> pq = new PriorityQueue<>(k, (x, y) -> (y - x));
		for (int element : a) {
			if (pq.size() < k || element < pq.peek()) {
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