package com.svetanis.algorithms.slidingwindow.queue;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Arrays.max;
import static com.svetanis.java.base.utils.Print.print;

import java.util.List;
import java.util.PriorityQueue;

import com.google.common.collect.ImmutableList;

// Sliding Window Maximum

public final class SlidingWindowMaxPriorityQueue {

	public static ImmutableList<Integer> maxSlidingWindow(int[] a, int w) {
		// Time complexity: O(n log n)

		int n = a.length;
		if (w > n) {
			return newList(max(a));
		}
		List<Integer> list = newArrayList();
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for (int i = 0; i < w; ++i) {
			pq.offer(new Pair(a[i], i));
		}
		for (int i = w; i < n; ++i) {
			Pair p = pq.peek();
			list.add(p.value);
			while (!pq.isEmpty() && p.index <= i - w) {
				pq.poll();
				p = pq.peek();
			}
			pq.offer(new Pair(a[i], i));
		}
		// max element of last window
		list.add(pq.peek().value);
		return newList(list);
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, -1, -3, 5, 3, 6, 7 };
		print(maxSlidingWindow(a1, 3)); // 3 3 5 5 6 7

		int[] a2 = { 6, 0, -6 };
		print(maxSlidingWindow(a2, 2)); // 6 0

		int[] a3 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		print(maxSlidingWindow(a3, 1)); // 10 9 8 7 6 5 4 3 2 1
	}
	
	private static class Pair implements Comparable<Pair>{
		private int value;
		private int index;
		
		Pair(int value, int index){
			this.value = value;
			this.index = index;
		}
		
		@Override
		public int compareTo(Pair other) {
			return other.value - this.value;
		}
	}
}
