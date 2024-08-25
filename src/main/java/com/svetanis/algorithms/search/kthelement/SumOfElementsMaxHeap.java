package com.svetanis.algorithms.search.kthelement;

import static java.lang.Math.abs;

import java.util.PriorityQueue;
import java.util.Queue;

// given an array, find the sum of all numbers
// between the k1'th and k2'th smallest elements

public final class SumOfElementsMaxHeap {

	public static int sum(int[] a, int k1, int k2) {
		// Time Complexity: O(n * log n)
		// Space Complexity: O(n)

		if (k1 == k2) {
			return 0;
		}
		Queue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
		for (int i = 0; i < a.length; i++) {
			if (i < k2 - 1) {
				pq.add(a[i]);
			} else if (a[i] < pq.peek()) {
				pq.poll();
				pq.add(a[i]);
			}
		}
		int sum = 0;
		int diff = abs(k2 - k1);
		for (int i = 0; i < diff - 1; i++) {
			sum += pq.poll();
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 12, 5, 15, 11 };
		System.out.println(sum(a1, 3, 6));
		int[] a2 = { 3, 5, 8, 7 };
		System.out.println(sum(a2, 1, 4));
	}
}