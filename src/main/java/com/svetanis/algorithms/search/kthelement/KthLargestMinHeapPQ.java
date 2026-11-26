package com.svetanis.algorithms.search.kthelement;

import java.util.PriorityQueue;
import java.util.Queue;

// 215. Kth Largest Element in an Array

// Given an integer array nums and an integer k (1 <= k <= nums.length),
// return the kth largest element -- the one at position k if the array
// were sorted from biggest to smallest. Duplicates count separately:
// in {3,2,3,1,2,4,5,5,6} the 4th largest is 4.

// Keep the k largest seen so far in a min-heap: its top is the smallest
// of them, so after the whole array it is the kth largest. A newcomer
// matters only if it beats the top; then the top leaves and it goes in.

// Two methods, the same heap:
//   kthLargest           -- seeds the first k numbers, then runs the
//                           beat-the-top test over the rest.
//   kthLargestSingleLoop -- one loop with no seeding: the branch carries
//                           both cases, room left or a newcomer that
//                           beats the top.
// They differ when k > nums.length, which LeetCode never sends:
// kthLargest reads past the end and throws; kthLargestSingleLoop returns
// the smallest number in the array.

// Time: O(n log k) -- each of n numbers costs at most one poll and one
//   add on a heap of k
// Space: O(k)

// Siblings:
//   KthLargestMaxHeapPQ -- a max-heap of every number, polled k - 1 times;
//     O(n) space, and the weaker answer.
//   KthLargestQuickSelectIterative, KthLargestQuickSelectRandomized --
//     quickselect, O(n) on average and no heap at all.
//   KthLargestStream (703) -- the same min-heap kept between calls.
//   KthSmallestMaxHeapPQ -- the mirror for the kth smallest: a max-heap of k,
//     with every comparison turned round.

public final class KthLargestMinHeapPQ {

	public static int kthLargest(int[] a, int k) {
		Queue<Integer> pq = priorityQueue(a, k); // SEED: the first k numbers
		for (int i = k; i < a.length; i++) {
			int curr = a[i];
			if (curr > pq.peek()) { // TEST: beats the smallest of the k largest?
				pq.poll(); // EVICT the top -- no longer among the k largest
				pq.add(curr); // KEEP the newcomer
			}
		}
		return pq.peek(); // REPORT: the smallest of the k largest
	}

	private static Queue<Integer> priorityQueue(int[] a, int k) {
		// k here is the INITIAL CAPACITY, not a bound -- the heap grows on demand.
		// What holds it at k is this loop plus the poll-before-add in kthLargest.
		// A capacity of 0 throws IllegalArgumentException, so k >= 1 is a precondition.
		Queue<Integer> pq = new PriorityQueue<Integer>(k, Integer::compare);
		for (int i = 0; i < k; i++) {
			pq.add(a[i]); // SEED: k > a.length reads past the end here
		}
		return pq;
	}

	public static int kthLargestSingleLoop(int[] a, int k) {
		Queue<Integer> pq = new PriorityQueue<Integer>(k, Integer::compare);
		for (int element : a) {
			if (pq.size() < k || element > pq.peek()) { // TEST: room left, or beats the top (equal does not)
				if (pq.size() == k) {
					pq.poll(); // EVICT the top to make room
				}
				pq.add(element); // KEEP the newcomer
			}
		}
		return pq.peek(); // REPORT: the smallest of the k largest
	}

	public static void main(String[] args) {
		System.out.println(kthLargest(new int[] { 12, 3, 5, 7, 19 }, 5)); // 3
		System.out.println(kthLargest(new int[] { 12, 3, 5, 7, 19 }, 2)); // 12
		System.out.println(kthLargest(new int[] { 10, 2, 4, 6, 8, 1, 3, 5, 7, 10, 128 }, 4)); // 8
		System.out.println(kthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 2)); // 5
		System.out.println(kthLargest(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4)); // 4

		System.out.println(kthLargestSingleLoop(new int[] { 3, 2, 1, 5, 6, 4 }, 2)); // 5
		System.out.println(kthLargestSingleLoop(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4)); // 4
	}
}
