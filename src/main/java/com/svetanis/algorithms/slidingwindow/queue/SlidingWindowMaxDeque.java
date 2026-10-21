package com.svetanis.algorithms.slidingwindow.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// 239. Sliding Window Maximum
//
// An array a and a window size w, 1 <= w <= a.length. Returns the maximum of every window of
// w neighbours, from left to right: a.length - w + 1 numbers.
//
// A maximum cannot be taken out when it leaves the window, so keep every number that could
// still become one: an older number <= the newcomer never can, because the newcomer is at least
// as big and stays in the window longer. The deque holds indexes; their values decrease from
// front to back, so the front is the window's maximum, and the front leaves once it slides out.
//
// This version records the PREVIOUS window at the top of each step, so it needs a loop that
// builds the first window before, and one more record after the loop.
//
// Siblings, same problem:
//   SlidingWindowMaxSubmit         the same deque in one loop, recording the current window;
//                                  returns int[]; the version to prefer
//   SlidingWindowMaxPriorityQueue  a max-heap of (value, index) instead of the deque; O(n log n)

public final class SlidingWindowMaxDeque {

	private static List<Integer> subArrayMax(int[] a, int w) {
		// Time complexity: O(n), each index joins the deque once and leaves it at most once
		// Auxiliary Space: O(w), the deque holds indexes of one window;
		// the returned list holds n - w + 1 numbers

		List<Integer> list = new ArrayList<>();
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < w; ++i) {
			windowMax(dq, a, i); // BUILD: the first window
		}

		for (int i = w; i < a.length; ++i) {
			// RECORD: max element of prev window, a[i - w .. i - 1]
			list.add(a[dq.peekFirst()]);

			// DROP: the index which is out of this window
			while (!dq.isEmpty() && i - w + 1 > dq.peekFirst()) {
				dq.pollFirst();
			}
			windowMax(dq, a, i);
		}
		// RECORD: max element of last window
		list.add(a[dq.peekFirst()]);
		return list;
	}

	private static void windowMax(Deque<Integer> dq, int[] a, int i) {
		// THROW: older elements that are <= the current element,
		// they can never be a maximum again
		while (!dq.isEmpty() && a[dq.getLast()] <= a[i]) {
			dq.pollLast();
		}
		// JOIN: the current element's index
		dq.addLast(i);
	}

	private static void print(List<Integer> list) {
		StringBuilder sb = new StringBuilder();
		for (int value : list) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, -1, -3, 5, 3, 6, 7 };
		print(subArrayMax(a, 3)); // 3 3 5 5 6 7

		int[] a1 = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
		print(subArrayMax(a1, 3)); // 3 3 4 5 5 5 6

		int[] a2 = { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 };
		print(subArrayMax(a2, 4)); // 10 10 10 15 15 90 90

		int[] a3 = { 6, 0, -6 };
		print(subArrayMax(a3, 2)); // 6 0
	}
}