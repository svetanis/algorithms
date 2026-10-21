package com.svetanis.algorithms.slidingwindow.queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// 239. Sliding Window Maximum
//
// An array a and a window size w. Returns the maximum of every window of w neighbours, from
// left to right: a.length - w + 1 numbers. The problem promises 1 <= w <= a.length; for
// w > a.length this method returns one number, the maximum of the whole array.
//
// A max-heap of (value, index) keeps the largest value on top. A heap cannot take out an
// arbitrary index, so a number that slid out of the window stays inside until it reaches the
// top; only the top is ever read, and stale tops are popped before the next window is read,
// so a stale number lower down does no harm.
//
// Siblings, same problem:
//   SlidingWindowMaxSubmit  a monotonic deque of indexes, one loop, returns int[]; O(n),
//                           the version to prefer
//   SlidingWindowMaxDeque   the same deque, recording the previous window; O(n)

public final class SlidingWindowMaxPriorityQueue {
	// Time complexity: O(n log n), each index is pushed once and popped at most once,
	// and each heap operation costs log of the heap's size
	// Space complexity: O(n), stale numbers stay in the heap until they reach the top;
	// on an increasing array none is ever popped

	public static List<Integer> maxSlidingWindow(int[] a, int w) {
		int n = a.length;
		if (w > n) {
			return List.of(Arrays.stream(a).max().getAsInt());
		}
		List<Integer> list = new ArrayList<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for (int i = 0; i < w; ++i) {
			pq.offer(new Pair(a[i], i)); // BUILD: PUSH the first window
		}
		for (int i = w; i < n; ++i) {
			Pair p = pq.peek();
			list.add(p.value); // RECORD: the top is the previous window's max, a[i - w .. i - 1]
			while (!pq.isEmpty() && p.index <= i - w) {
				pq.poll(); // DROP: POP tops that slid out, until the top is inside the new window
				p = pq.peek();
			}
			pq.offer(new Pair(a[i], i)); // PUSH the newcomer
		}
		// RECORD: max element of last window
		list.add(pq.peek().value);
		return list;
	}

	private static void print(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (int value : list) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, -1, -3, 5, 3, 6, 7 };
		print(maxSlidingWindow(a1, 3)); // 3 3 5 5 6 7

		int[] a2 = { 6, 0, -6 };
		print(maxSlidingWindow(a2, 2)); // 6 0

		int[] a3 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		print(maxSlidingWindow(a3, 1)); // 10 9 8 7 6 5 4 3 2 1
	}

	private static class Pair implements Comparable<Pair> {
		private int value;
		private int index;

		Pair(int value, int index) {
			this.value = value;
			this.index = index;
		}

		@Override
		public int compareTo(Pair other) {
			// the larger value first, without the overflow a subtraction would risk
			return Integer.compare(other.value, this.value);
		}
	}
}
