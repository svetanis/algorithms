package com.svetanis.algorithms.search.kthelement;

import java.util.PriorityQueue;
import java.util.Queue;

// Kth smallest element in an unsorted array (no LeetCode number; the mirror of LC 215)

// given an unsorted array of numbers,
// find Kth smallest number in it
// 1 <= k <= a.length; the answer is the number at position k if the array
// were sorted from smallest to biggest. Duplicates count separately.

// Put every number in a min-heap and take the top off k - 1 times; the top
// is then the kth smallest. Correct, and the weaker answer: it holds all n
// numbers where KthSmallestMaxHeapPQ holds k.

// Time Complexity: O(n log n + k log n) -- this INSERTS one at a time.
// O(n + ...) is the heapify bound, which needs new PriorityQueue<>(list).
// As written this is strictly worse than KthSmallestMaxHeapPQ's O(n log k),
// and it holds O(n) space where the max-heap of k holds O(k).
// Space: O(n) -- every number sits in the heap

// Siblings, the kth smallest three ways:
//   KthSmallestMaxHeapPQ             -- max-heap of k, O(n log k)
//   KthSmallestMinHeapPQ             -- this file: min-heap of all n, polled k - 1 times
//   KthSmallestQuickSelectRandomized -- quickselect, three groups, O(n) average
// Each mirrors an LC 215 file: KthLargestMinHeapPQ, KthLargestMaxHeapPQ,
// KthLargestQuickSelectRandomized.

public final class KthSmallestMinHeapPQ {

	public static int kthSmallest(int[] a, int k) {
		Queue<Integer> pq = new PriorityQueue<Integer>(Integer::compare); // HEAP: min-heap, the smallest on top
		// 1. insert all the numbers in the min-heap
		for (int element : a) {
			pq.add(element); // ADD every number
		}
		// 2. extract min k - 1 times
		for (int i = 0; i < k - 1; i++) {
			pq.poll(); // DROP the smallest still in the heap
		}
		// 3. return k-smallest number
		return pq.peek(); // REPORT: k - 1 smaller ones are gone
	}

	public static void main(String[] args) {
		int[] a = { 12, 3, 5, 7, 19 };
		System.out.println(kthSmallest(a, 2)); // 5

		int[] a1 = { 2, 1, 4, 3, 2 };
		System.out.println(kthSmallest(a1, 3)); // 2

		int[] a2 = { 1, 5, 12, 2, 11, 5 };
		System.out.println(kthSmallest(a2, 3)); // 5

		int[] a3 = { 1, 5, 12, 2, 11, 5 };
		System.out.println(kthSmallest(a3, 4)); // 5

		int[] a4 = { 5, 12, 11, -1, 12 };
		System.out.println(kthSmallest(a4, 3)); // 11
	}
}