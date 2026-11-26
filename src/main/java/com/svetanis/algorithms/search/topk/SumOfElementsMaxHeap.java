package com.svetanis.algorithms.search.topk;

import static java.lang.Math.abs;
import static java.lang.Math.max;
import static java.util.Comparator.reverseOrder;

import java.util.PriorityQueue;
import java.util.Queue;

// Sum of the Numbers Between the k1-th and k2-th Smallest
//
// Takes an array of n numbers and two ranks k1, k2 in 1..n, in either order. Returns the sum of
// the numbers whose rank in sorted order lies strictly between k1 and k2. Every copy of a
// repeated number has its own rank. Equal or neighbouring ranks give 0.
//
// Call the larger rank high and the smaller low. Keep the high - 1 smallest numbers in a
// max-heap of that size: once it is full, a number smaller than the top replaces the top. At
// the end the heap holds ranks 1 .. high - 1 with rank high - 1 on top, so polling it
// high - low - 1 times takes exactly the ranks strictly between, from high - 1 down to low + 1.
//
// Siblings -- two answers to this problem in this package:
//   SumOfElementsMinHeap  every number into a min-heap, poll away the low smallest, then add up
//                         the next high - low - 1; O(n log n) time, O(n) space
//   SumOfElementsMaxHeap  a max-heap capped at high - 1 numbers                      (this file)

public final class SumOfElementsMaxHeap {

	public static int sum(int[] a, int k1, int k2) {
		// Time Complexity: O(n log high) -- each number is offered once to a heap that never
		// holds more than high - 1 numbers
		// Space Complexity: O(high), the heap

		if (k1 == k2) {
			return 0;
		}
		int high = max(k1, k2); // HIGH rank: k1 and k2 may come in either order
		Queue<Integer> pq = new PriorityQueue<>(reverseOrder()); // ORDER: largest on top
		for (int i = 0; i < a.length; i++) {
			if (i < high - 1) {
				pq.add(a[i]); // PUSH: fill the heap to high - 1
			} else if (a[i] < pq.peek()) {
				pq.poll(); // EVICT the largest of the high - 1 smallest so far
				pq.add(a[i]); // KEEP the smaller newcomer
			}
		}
		int sum = 0;
		int diff = abs(k2 - k1);
		for (int i = 0; i < diff - 1; i++) {
			sum += pq.poll(); // ADD ranks high - 1 down to low + 1, largest first
		}
		return sum; // REPORT
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 12, 5, 15, 11 };
		System.out.println(sum(a1, 3, 6));
		int[] a2 = { 3, 5, 8, 7 };
		System.out.println(sum(a2, 1, 4));
		System.out.println(sum(a1, 6, 3)); // 23, the same ranks given high first
	}
}