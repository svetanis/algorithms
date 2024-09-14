package com.svetanis.algorithms.slidingwindow.queue;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.utils.Arrays.max;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;
import java.util.PriorityQueue;

// Sliding Window Maximum

public final class SlidingWindowMaxPriorityQueue {

	public static List<Integer> maxSlidingWindow(int[] a, int w) {
		// Time complexity: O(n log n)

		int n = a.length;
		if (w > n) {
			return newArrayList(max(a));
		}
		List<Integer> list = newArrayList();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < w; ++i) {
			pq.offer(i);
		}
		for (int i = w; i < n; ++i) {
			list.add(a[pq.peek()]);
			while (!pq.isEmpty() && i - w + 1 > pq.peek()) {
				pq.poll();
			}
			pq.offer(i);
		}
		// max element of last window
		list.add(a[pq.peek()]);
		return list;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, -1, -3, 5, 3, 6, 7 };
		print(maxSlidingWindow(a1, 3)); // 3 3 5 5 6 7

		int[] a2 = { 6, 0, -6 };
		print(maxSlidingWindow(a2, 2)); // 6 0

		int[] a3 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		print(maxSlidingWindow(a3, 1)); // 10 9 8 7 6 5 4 3 2 1
	}
}
