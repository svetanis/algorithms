package com.svetanis.algorithms.search.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

// 1675. Minimize Deviation in Array
//
// nums holds n positive integers. Any number of times: an even number may be divided by 2, an odd
// number may be multiplied by 2. The deviation is max - min. Returns the smallest deviation
// reachable.
//
// An odd number x can only ever be x or 2x, and an even number only itself and its halvings. So
// start every odd number at 2x: from there every number can only be halved. Now the
// only move that can shrink max - min is halving the current max -- halving anything else can only
// widen the gap. So keep the numbers in a max-heap, halve the top while it is even, and track the
// min as it falls. When the top is odd it cannot go lower, and neither can the deviation.
//
// Time: O(n log M log n), M the largest value -- each number is halved at most log M times, each
//   halving a heap poll and push
// Space: O(n) for the heap

public final class MinimizeDeviation {

	public static int minDeviation(int[] a) {
		int min = Integer.MAX_VALUE;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // MAX-HEAP: the largest number on top
		for (int num : a) {
			num = num % 2 == 1 ? num * 2 : num;    // RAISE: an odd number starts at its doubled value
			pq.offer(num);                         // PUSH
			min = Math.min(min, num);              // MIN: the smallest number held
		}
		int deviation = pq.peek() - min;
		while (!pq.isEmpty() && pq.peek() % 2 == 0) { // STOP: an odd max cannot go lower
			int top = pq.poll() / 2;               // HALVE: the max, the only move that can shrink the gap
			pq.offer(top);                         // PUSH: it goes back, maybe no longer the max
			min = Math.min(min, top);              // MIN: the halved number may be the new smallest
			deviation = Math.min(deviation, pq.peek() - min); // RECORD: the gap now
		}
		return deviation;                          // REPORT
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3, 4 };
		System.out.println(minDeviation(a1)); // 1
		int[] a2 = { 4, 1, 5, 20, 3 };
		System.out.println(minDeviation(a2)); // 3
		int[] a3 = { 2, 10, 8 };
		System.out.println(minDeviation(a3)); // 3
	}
}
