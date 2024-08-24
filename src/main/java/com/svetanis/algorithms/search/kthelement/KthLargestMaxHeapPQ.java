package com.svetanis.algorithms.search.kthelement;

import java.util.PriorityQueue;
import java.util.Queue;

// given an unsorted array of numbers,
// find Kth largest number in it

public final class KthLargestMaxHeapPQ {

	public static int kthLargest(int[] a, int k) {
		// Time Complexity: O(n + k log n)

		Queue<Integer> heap = new PriorityQueue<Integer>((x, y) -> (y - x));
		// 1. insert all the numbers in the max-heap
		for (int element : a) {
			heap.add(element);
		}

		// 2. extract max k - 1 times
		for (int i = 0; i < k - 1; i++) {
			heap.poll();
		}
		// 3. return k-smallest number
		return heap.peek();
	}

	public static void main(String[] args) {
		int[] a = { 12, 3, 5, 7, 19 };
		System.out.println(kthLargest(a, 5)); // 3
		System.out.println(kthLargest(a, 2)); // 12

		int[] a1 = { 10, 2, 4, 6, 8, 1, 3, 5, 7, 10, 128 };
		System.out.println(kthLargest(a1, 4)); // 8
		
	}
}