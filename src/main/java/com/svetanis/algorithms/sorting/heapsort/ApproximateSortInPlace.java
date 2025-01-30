package com.svetanis.algorithms.sorting.heapsort;

import static com.svetanis.java.base.utils.Print.print;

import java.util.PriorityQueue;
import java.util.Queue;

public final class ApproximateSortInPlace {
	// Time Complexity: O(n log k)
	// Auxiliary Space: O(k)

	public static void sort(int[] a, int k) {
		int n = a.length;
		Queue<Integer> queue = init(a, k);
		for (int i = k, j = 0; j < n; i++, j++) {
			a[j] = queue.poll();
			if (i < n) {
				queue.offer(a[i]);
			}
		}
	}

	private static Queue<Integer> init(int[] a, int k) {
		Queue<Integer> queue = new PriorityQueue<>();
		// push first k elements into queue
		for (int i = 0; i < k; ++i) {
			queue.offer(a[i]);
		}
		return queue;
	}

	public static void main(String[] args) {
		int[] a = { 2, 6, 3, 12, 56, 8 };
		sort(a, 3);
		print(a);
	}

}
