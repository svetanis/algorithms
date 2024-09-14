package com.svetanis.algorithms.slidingwindow.queue;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import com.google.common.collect.ImmutableList;

// Given an array and an integer k, 
// find the maximum for each and 
// every contiguous subarray of size k.

public final class SlidingWindowMaxDeque2 {

	private static ImmutableList<Integer> subArrayMax(int[] a, int w) {
		// Time complexity: O(n);
		// Auxiliary Space: O(k)

		List<Integer> list = newArrayList();
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < a.length; ++i) {
			// remove the elements
			// which are out of this window
			while (!dq.isEmpty() && i - w + 1 > dq.peekFirst()) {
				dq.pollFirst();
			}
			// remove elements that are less than the
			// current element since they are not useful
			while (!dq.isEmpty() && a[dq.getLast()] <= a[i]) {
				dq.pollLast();
			}
			// add current element's index to the deque
			dq.addLast(i);

			// we've hit the size w, add the current max
			// to the result
			if (i >= w - 1) {
				list.add(a[dq.peekFirst()]);
			}
		}
		return newList(list);
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