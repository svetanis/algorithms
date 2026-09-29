package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Deque;

// Next smaller element: for each element, the first smaller element to its right; -1 if none.
//
// NextGreater with the comparison flipped: the stack's values never fall from bottom to top,
// and an arriving element answers every larger one on top.

public final class NextSmaller {
	// Time Complexity: O(n), every position is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] nextSmaller(int[] a) {
		int n = a.length;
		int[] smaller = new int[n];
		fill(smaller, -1); // stays -1 for an element nothing smaller follows
		Deque<Integer> stack = new ArrayDeque<>(); // holds positions, never values
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && a[i] < a[stack.peek()]) { // '<' instead of '>'
				int index = stack.pop(); // a[i] is the first element below a[index]
				smaller[index] = a[i];
			}
			stack.push(i);
		}
		return smaller;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 1, 3, 4, 6, 2 };
		print(nextSmaller(a1)); // 1, -1, 2, 2, 2, -1

		int[] a2 = { 1, 3, 3, 2, 5 };
		print(nextSmaller(a2)); // -1, 2, 2, -1, -1

		int[] a3 = { 4, 2, 1, 5, 3 };
		print(nextSmaller(a3)); // 2, 1, -1, 3, -1

		int[] a4 = { 1, 2, 3, 4, 5 };
		print(nextSmaller(a4)); // -1, -1, -1, -1, -1

		int[] a5 = { 5, 4, 3, 2, 1 };
		print(nextSmaller(a5)); // 4, 3, 2, 1, -1

		int[] a6 = { 1, 3, 5, 4, 2 };
		print(nextSmaller(a6)); // -1, 2, 4, 2, -1

		int[] a7 = { 6, 4, 2 };
		print(nextSmaller(a7)); // 4, 2, -1
	}
}
