package com.svetanis.algorithms.search.topk;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.PriorityQueue;
import java.util.Queue;

// Sum of the Numbers Between the k1-th and k2-th Smallest
//
// Takes an array of n numbers and two ranks k1, k2 in 1..n, in either order. Returns the sum of
// the numbers whose rank in sorted order lies strictly between k1 and k2. Every copy of a
// repeated number has its own rank. Equal or neighbouring ranks give 0.
//
// Call the smaller rank low and the larger high. Put every number in a min-heap, poll away the
// low smallest (ranks 1 .. low), and the next high - low - 1 polls are exactly the ranks
// strictly between, from low + 1 up to high - 1.
//
// Siblings -- two answers to this problem in this package:
//   SumOfElementsMinHeap  every number into a min-heap                              (this file)
//   SumOfElementsMaxHeap  a max-heap capped at high - 1 numbers, keeping only the smallest;
//                         O(n log high) time, O(high) space

public final class SumOfElementsMinHeap {

	public static int sum(int[] a, int k1, int k2) {
		// Time Complexity: O(n log n) -- n inserts into a heap of up to n numbers, then at most
		// high polls
		// Space Complexity: O(n), the heap holds every number

		if (k1 == k2) {
			return 0;
		}
		Queue<Integer> pq = new PriorityQueue<>(Integer::compare); // ORDER: smallest on top
		// 1. insert all the numbers in the min-heap
		for (int element : a) {
			pq.add(element); // PUSH every number
		}
		// 2. extract min k times
		int min = min(k1, k2); // LOW rank: k1 and k2 may come in either order
		for (int i = 0; i < min; i++) {
			pq.poll(); // SKIP ranks 1 .. low
		}
		// 3. extract numbers in between and add them up
		int sum = 0;
		int diff = abs(k2 - k1);
		for (int i = 0; i < diff - 1; i++) {
			sum += pq.poll(); // ADD ranks low + 1 up to high - 1
		}
		return sum; // REPORT
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 12, 5, 15, 11 };
		System.out.println(sum(a1, 3, 6));
		int[] a2 = { 3, 5, 8, 7 };
		System.out.println(sum(a2, 1, 4));
	}
}