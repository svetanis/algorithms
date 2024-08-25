package com.svetanis.algorithms.search.kthelement;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.PriorityQueue;
import java.util.Queue;

// given an array, find the sum of all numbers
// between the k1'th and k2'th smallest elements

public final class SumOfElementsMinHeap {

	public static int sum(int[] a, int k1, int k2) {
		// Time Complexity: O(n * log n)
		// Space Complexity: O(n)
		
		if (k1 == k2) {
			return 0;
		}
		Queue<Integer> pq = new PriorityQueue<>((x, y) -> x - y);
		// 1. insert all the numbers in the min-heap
		for (int element : a) {
			pq.add(element);
		}
		// 2. extract min k times
		int min = min(k1, k2);
		for (int i = 0; i < min; i++) {
			pq.poll();
		}
		// 3. extract numbers in between and add them up
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