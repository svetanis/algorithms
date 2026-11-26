package com.svetanis.algorithms.search.kthelement;

import static java.util.Arrays.asList;
import static java.util.Comparator.reverseOrder;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 215. Kth Largest Element in an Array

// given an unsorted array of numbers,
// find Kth largest number in it
// The input is a List<Integer> and 1 <= k <= its size; the answer is the
// number at position k if the list were sorted from biggest to smallest.
// Duplicates count separately.

// Put every number in a max-heap and take the top off k - 1 times; the top
// is then the kth largest. Correct, and the weaker answer: it holds all n
// numbers where KthLargestMinHeapPQ holds k.

// Time Complexity: O(n log n + k log n) -- this INSERTS one at a time.
// O(n + ...) is the heapify bound, which needs new PriorityQueue<>(list).
// As written this is strictly worse than KthLargestMinHeapPQ's O(n log k),
// and it holds O(n) space where the min-heap holds O(k).
// Space: O(n) -- every number sits in the heap

// Siblings, LC 215 five ways:
//   KthLargestMinHeapPQ.kthLargest           -- min-heap of k, seeded with the first k, O(n log k)
//   KthLargestMinHeapPQ.kthLargestSingleLoop -- the same heap in one loop, no seeding
//   KthLargestMaxHeapPQ                      -- this file: max-heap of all n, polled k - 1 times
//   KthLargestQuickSelectRandomized          -- quickselect, recursive, three groups, O(n) average
//   KthLargestQuickSelectIterative           -- quickselect in a loop, two groups
// The kth smallest is the mirror: KthSmallestMinHeapPQ is this file with the heap turned round.

public final class KthLargestMaxHeapPQ {

	public static int kthLargest(List<Integer> list, int k) {
		Queue<Integer> pq = new PriorityQueue<Integer>(reverseOrder()); // HEAP: max-heap, the largest on top
		// 1. insert all the numbers in the max-heap
		for (int element : list) {
			pq.add(element); // ADD every number
		}
		// 2. extract max k - 1 times
		for (int i = 0; i < k - 1; i++) {
			pq.poll(); // DROP the largest still in the heap
		}
		// 3. the top is now the kth largest
		// k = 0 skips the extract loop and returns the LARGEST element --
		// a plausible wrong answer rather than a failure. k >= 1 is a precondition.
		return pq.peek(); // REPORT: k - 1 larger ones are gone
	}

	public static void main(String[] args) {
		System.out.println(kthLargest(asList(12, 3, 5, 7, 19), 5)); // 3
		System.out.println(kthLargest(asList(12, 3, 5, 7, 19), 2)); // 12
		System.out.println(kthLargest(asList(10, 2, 4, 6, 8, 1, 3, 5, 7, 10, 128), 4)); // 8
		System.out.println(kthLargest(asList(3, 2, 1, 5, 6, 4), 2)); // 5
		System.out.println(kthLargest(asList(3, 2, 3, 1, 2, 4, 5, 5, 6), 4)); // 4
	}
}