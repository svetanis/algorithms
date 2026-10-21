package com.svetanis.algorithms.slidingwindow.queue;

import java.util.ArrayDeque;
import java.util.Deque;

// 239. Sliding Window Maximum
//
// An array a and a window size k, 1 <= k <= a.length. Returns the maximum of every window of
// k neighbours, from left to right, as an int[] of a.length - k + 1 numbers.
//
// A maximum cannot be taken out when it leaves the window, so keep every number that could
// still become one: an older number <= the newcomer never can, because the newcomer is at least
// as big and stays in the window longer. The deque holds indexes; their values decrease from
// front to back, so the front is the window's maximum, and the front leaves once it slides out.
//
// Two methods, the same four jobs in the same order. They differ in where each answer goes:
//   subArrayMax       keeps a second counter, j, and writes result[j++]
//   maxSlidingWindow  computes the slot: the window ending at right starts at right - k + 1,
//                     and that start is its slot, so the first whole window (right = k - 1)
//                     goes to slot 0. It also DROPs with an if: only one index leaves per step.
//
// Siblings, same problem:
//   SlidingWindowMaxDeque          the same deque, split into a loop that builds the first
//                                  window and a loop that records the PREVIOUS window;
//                                  returns a List
//   SlidingWindowMaxPriorityQueue  a max-heap of (value, index) instead of the deque; O(n log n)

public final class SlidingWindowMaxSubmit {
	// Time complexity: O(n), each index joins the deque once and leaves it at most once
	// Auxiliary Space: O(k), the deque holds indexes of one window;
	// the result holds n - k + 1 numbers

	private static int[] subArrayMax(int[] a, int k) {
		int n = a.length;
		int[] result = new int[n - k + 1];
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = 0, j = 0; i < a.length; ++i) {
			// DROP: the index which is out of this window, a[i - k + 1 .. i]
			while (!dq.isEmpty() && i - k + 1 > dq.peekFirst()) {
				dq.pollFirst();
			}
			// THROW: older elements that are <= the current element,
			// they can never be a maximum again
			while (!dq.isEmpty() && a[dq.getLast()] <= a[i]) {
				dq.pollLast();
			}
			// JOIN: the current element's index
			dq.addLast(i);

			// RECORD: once the window has reached size k,
			// its max is the front
			if (i >= k - 1) {
				result[j++] = a[dq.peekFirst()];
			}
		}
		return result;
	}

	public static int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		int[] output = new int[n - k + 1]; // one maximum per window
		Deque<Integer> dq = new ArrayDeque<>(); // indexes, values going down from front to back
		for (int right = 0; right < n; right++) { // right is the index entering
			if (!dq.isEmpty() && dq.peekFirst() < right - k + 1) { // the window starts at right - k + 1
				dq.pollFirst(); // DROP: the front slid out
			}
			while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[right]) {
				dq.pollLast(); // THROW: never the maximum again
			}
			dq.offerLast(right); // JOIN: always, it may be the maximum later
			if (right >= k - 1) { // the first whole window ends at k - 1
				output[right - k + 1] = nums[dq.peekFirst()]; // RECORD: the front is the maximum
			}
		}
		return output;
	}

	private static void print(int[] a) {
		StringBuilder sb = new StringBuilder();
		for (int value : a) {
			sb.append(value).append(' ');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		int[] a = { 1, 3, -1, -3, 5, 3, 6, 7 };
		print(subArrayMax(a, 3)); // 3 3 5 5 6 7
		print(maxSlidingWindow(a, 3)); // 3 3 5 5 6 7

		int[] a1 = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
		print(subArrayMax(a1, 3)); // 3 3 4 5 5 5 6
		print(maxSlidingWindow(a1, 3)); // 3 3 4 5 5 5 6

		int[] a2 = { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 };
		print(subArrayMax(a2, 4)); // 10 10 10 15 15 90 90
		print(maxSlidingWindow(a2, 4)); // 10 10 10 15 15 90 90

		int[] a3 = { 6, 0, -6 };
		print(subArrayMax(a3, 2)); // 6 0
		print(maxSlidingWindow(a3, 2)); // 6 0
	}
}