package com.svetanis.algorithms.search.topk;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// The k Smallest Numbers, from a Heap of Every Number
//
// Takes k and a list of n numbers, 1 <= k <= n. Returns the k smallest, each copy of a
// repeated number counted on its own, sorted ascending.
//
// Put every number in a min-heap; each poll then hands back the smallest one left, so k polls
// give the k smallest in order. Simple, but the heap holds all n numbers. The bounded way to
// the same answer is a MAX-heap capped at k, the mirror of TopKLargestMinHeap.
//
// Siblings -- four ways to take the k largest or smallest in this package:
//   TopKLargestMinHeap      the k LARGEST with a min-heap capped at k; O(n log k), heap order
//   TopKLargestTreeSet      the same cap with a TreeSet of (value, index); O(n log k), ascending
//   TopKLargestQuickSelect  partition until the kth largest is in place; O(n) average,
//                           reorders the input, returns its first k positions unordered
//   TopKSmallestHeapAll     every number into a min-heap, then k polls             (this file)

public final class TopKSmallestHeapAll {
	// Time Complexity: O(n log n + k log n) -- n adds one by one, each may climb the whole
	// heap, then k polls
	// Space Complexity: O(n), the heap holds every number

	public static List<Integer> topK(int k, List<Integer> list) {
		Queue<Integer> pq = new PriorityQueue<>(); // ORDER: smallest on top
		for (int element : list) {
			pq.add(element); // PUSH every number
		}
		List<Integer> sorted = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			sorted.add(pq.poll()); // POLL the smallest left, so the list comes out ascending
		}
		return sorted; // REPORT
	}

	public static void main(String[] args) {
		List<Integer> list = asList(3, 1, 2, 10, 33, 100, 20);
		System.out.println(topK(3, list));
	}
}