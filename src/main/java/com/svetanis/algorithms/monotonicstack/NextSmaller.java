package com.svetanis.algorithms.monotonicstack;

import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Next smaller element: for each element, the first smaller element to its right; -1 if none.
//
// NextGreater with the comparison flipped: the stack's values never fall from bottom to top,
// and an arriving element answers every larger one on top.
//
// Input: an array of integers a. Returns: an array of the same length whose cell i holds the
// first value strictly smaller than a[i] among a[i+1..n-1], or -1 when there is none.
//
// Siblings:
//   twopointers.closest.NextSmaller -- the same scan with (value, position) entries
//   NextGreater                     -- the comparison flipped
//   PrevSmaller                     -- the nearest smaller element on the LEFT

public final class NextSmaller {
	// Time Complexity: O(n), every position is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] nextSmaller(int[] a) {
		int n = a.length;
		int[] smaller = new int[n];
		fill(smaller, -1); // DEFAULT: stays -1 for an element nothing smaller follows
		Deque<Integer> stack = new ArrayDeque<>(); // WAITING: holds positions, never values
		for (int i = 0; i < n; i++) { // WALK: a[i] arrives
			while (!stack.isEmpty() && a[i] < a[stack.peek()]) { // STOP: '<' instead of '>'
				int index = stack.pop(); // POP: a[i] is the first element below a[index]
				smaller[index] = a[i]; // ANSWER: written into the popped position's cell
			}
			stack.push(i); // PUSH: a[i] has no answer yet
		}
		return smaller; // RETURN: -1 where nothing smaller follows
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 1, 3, 4, 6, 2 };
		System.out.println(Arrays.toString(nextSmaller(a1))); // 1, -1, 2, 2, 2, -1

		int[] a2 = { 1, 3, 3, 2, 5 };
		System.out.println(Arrays.toString(nextSmaller(a2))); // -1, 2, 2, -1, -1

		int[] a3 = { 4, 2, 1, 5, 3 };
		System.out.println(Arrays.toString(nextSmaller(a3))); // 2, 1, -1, 3, -1

		int[] a4 = { 1, 2, 3, 4, 5 };
		System.out.println(Arrays.toString(nextSmaller(a4))); // -1, -1, -1, -1, -1

		int[] a5 = { 5, 4, 3, 2, 1 };
		System.out.println(Arrays.toString(nextSmaller(a5))); // 4, 3, 2, 1, -1

		int[] a6 = { 1, 3, 5, 4, 2 };
		System.out.println(Arrays.toString(nextSmaller(a6))); // -1, 2, 4, 2, -1

		int[] a7 = { 6, 4, 2 };
		System.out.println(Arrays.toString(nextSmaller(a7))); // 4, 2, -1
	}
}
