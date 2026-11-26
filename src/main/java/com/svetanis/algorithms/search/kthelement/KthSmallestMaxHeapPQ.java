package com.svetanis.algorithms.search.kthelement;

import static java.util.Comparator.reverseOrder;

import java.util.PriorityQueue;
import java.util.Queue;

// Kth smallest element in an unsorted array (no LeetCode number; the mirror of LC 215)

// given an unsorted array of numbers,
// find Kth smallest number in it
// 1 <= k <= a.length; the answer is the number at position k if the array
// were sorted from smallest to biggest. Duplicates count separately.

// Keep the k smallest seen so far in a MAX-heap: its top is the largest of
// them, so after the whole array it is the kth smallest. A newcomer matters
// only if it is smaller than the top; then the top leaves and it goes in.
// LC 215's min-heap with every comparison turned round.

// Two methods, the same heap:
//   kthSmallest           -- seeds the first k numbers, then runs the
//                            smaller-than-the-top test over the rest.
//   kthSmallestSingleLoop -- one loop with no seeding: the branch carries
//                            both cases, room left or a smaller newcomer.
// They differ when k > a.length: kthSmallest reads past the end and throws;
// kthSmallestSingleLoop returns the largest number in the array.

// Time: O(n log k) -- each of n numbers costs at most one poll and one add
//   on a heap of k
// Space: O(k)

// Siblings, the kth smallest three ways:
//   KthSmallestMaxHeapPQ             -- this file: max-heap of k
//   KthSmallestMinHeapPQ             -- min-heap of all n, polled k - 1 times
//   KthSmallestQuickSelectRandomized -- quickselect, three groups, O(n) average
// Each mirrors an LC 215 file: KthLargestMinHeapPQ, KthLargestMaxHeapPQ,
// KthLargestQuickSelectRandomized.

public final class KthSmallestMaxHeapPQ {

	public static int kthSmallest(int[] a, int k) {
		Queue<Integer> pq = priorityQueue(a, k); // SEED: the first k numbers

		// for the remaining numbers of the array,
		// if the number from the array is smaller
		// than the top (largest) number of the heap,
		// remove the top number from the heap
		// and add the number from the array
		for (int i = k; i < a.length; i++) {
			if (a[i] < pq.peek()) { // TEST: smaller than the largest of the k smallest?
				pq.poll(); // EVICT the top -- no longer among the k smallest
				pq.add(a[i]); // KEEP the newcomer
			}
		}
		return pq.peek(); // REPORT: the largest of the k smallest
	}

	private static Queue<Integer> priorityQueue(int[] a, int k){
		Queue<Integer> pq = new PriorityQueue<>(k, reverseOrder()); // HEAP: max-heap, the top is the largest kept
		// put first k numbers in the max heap
		for (int i = 0; i < k; i++) {
			pq.add(a[i]); // SEED: k > a.length reads past the end here
		}
		return pq;
	}

	public static int kthSmallestSingleLoop(int[] a, int k) {
		Queue<Integer> pq = new PriorityQueue<>(k, reverseOrder()); // HEAP: max-heap, the top is the largest kept
		for (int element : a) {
			if (pq.size() < k || element < pq.peek()) { // TEST: room left, or smaller than the top (equal is not)
				if (pq.size() == k) {
					pq.poll(); // EVICT the top to make room
				}
				pq.add(element); // KEEP the newcomer
			}
		}
		return pq.peek(); // REPORT: the largest of the k smallest
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