package com.svetanis.algorithms.search.topk;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// The k Largest Numbers, with a Min-Heap of Size k
//
// Takes an array of n numbers and k, 1 <= k <= n. Returns the k largest, each copy of a
// repeated number counted on its own, in heap order rather than sorted.
//
// "The k largest" is a LARGEST question, so the heap is a MIN-heap: its top is the smallest of
// the k kept so far, the one to throw out when something bigger arrives. Every number after the
// first k either is no bigger than the top and is ignored, or replaces the top. topK fills the
// heap first and then compares; topKSingleLoop does both in one loop.
//
// Siblings -- four ways to take the k largest or smallest in this package:
//   TopKLargestMinHeap      a min-heap capped at k; O(n log k), heap order          (this file)
//   TopKLargestTreeSet      the same cap with a TreeSet, each number tagged by its index so
//                           equal numbers stay apart; O(n log k), ascending
//   TopKLargestQuickSelect  partition until the kth largest is in place; O(n) average,
//                           reorders the input, returns its first k positions unordered
//   TopKSmallestHeapAll     the k SMALLEST: every number into a min-heap, then k polls;
//                           O(n log n), ascending

public final class TopKLargestMinHeap {
	// Space Complexity: O(k), the heap

	public static List<Integer> topK(int a[], int k) {
		// Time Complexity: O(n log k) -- each number costs at most one poll and one add on a
		// heap of k

		Queue<Integer> pq = priorityQueue(a, k); // KEEP the first k

		// go through the remaining numbers of the array,
		// if the number from the array is bigger than
		// the top (smallest) number of the min-heap
		// remove the top number from heap and add
		// the number from the array
		for (int i = k; i < a.length; i++) {
			if (a[i] > pq.peek()) { // BEATS the smallest kept
				pq.poll(); // EVICT the smallest kept
				pq.add(a[i]); // KEEP the newcomer
			}
		}
		return new ArrayList<>(pq); // REPORT in heap order
	}

	private static Queue<Integer> priorityQueue(int[] a, int k) {
		Queue<Integer> pq = new PriorityQueue<Integer>(k, Integer::compare); // ORDER: smallest on top
		// put first k numbers in the min heap
		for (int i = 0; i < k; i++) {
			pq.add(a[i]); // PUSH
		}
		return pq;
	}

	public static Queue<Integer> topKSingleLoop(int a[], int k) {
		// Time Complexity: O(n log k)

		Queue<Integer> pq = new PriorityQueue<Integer>(Integer::compare); // ORDER: smallest on top
		for (int item : a) {
			if (pq.size() < k || item > pq.peek()) { // FILL, or BEATS the smallest kept
				if (pq.size() == k) {
					pq.poll(); // EVICT the smallest kept
				}
				pq.offer(item); // KEEP
			}
		}
		return pq; // REPORT in heap order
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 2, 4, 6, 8, 1, 3, 5, 7, 10, 128 };
		System.out.println(topK(a1, 4));

		int[] a2 = { 3, 1, 5, 12, 2, 11 };
		System.out.println(topK(a2, 3));

		int[] a3 = { 5, 12, 11, -1, 12 };
		System.out.println(topK(a3, 3));
	}
}