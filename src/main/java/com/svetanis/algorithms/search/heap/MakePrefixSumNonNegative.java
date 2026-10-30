package com.svetanis.algorithms.search.heap;

import java.util.PriorityQueue;

// 2599. Make the Prefix Sum Non-negative
//
// One operation takes any element of nums and moves it to the end. Returns the fewest operations
// after which no prefix sum is negative. The input is chosen so that this is always possible (the
// total is not negative), and the elements are moved, never changed.
//
// Walk left to right with the running sum. When it goes negative, some negative number seen so far
// has to go to the end; moving the MOST negative one lifts the running sum the most for the same
// one operation. A min-heap of the negatives seen so far names it. A moved number lands at the end,
// where the full total -- not negative -- already covers it.
//
// Time: O(n log n) -- every number is pushed at most once and polled at most once
// Space: O(n) for the heap

public final class MakePrefixSumNonNegative {

	public static int prefSum(int[] a) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // MIN-HEAP: the most negative number seen so far on top
		long sum = 0;                                      // SUM: a long, the prefix can pass the int range
		int count = 0;
		for (int num : a) {
			sum += num;
			if (num < 0) {
				pq.offer(num);                             // PUSH: a candidate to move to the end
			}
			while (sum < 0) {
				sum -= pq.poll();                          // MOVE: the most negative one goes to the end
				count++;
			}
		}
		return count;                                      // REPORT: the moves made
	}

	public static void main(String[] args) {
		int[] a = { 2, -3, -8, 4, -6, 1 };
		System.out.println(prefSum(a)); // 2
	}
}
