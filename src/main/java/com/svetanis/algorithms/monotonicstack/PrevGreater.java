package com.svetanis.algorithms.monotonicstack;

import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Previous greater element: for each element, the nearest larger element to its left; -1 if
// none.
//
// Input: an array of integers a. Returns: an array of the same length whose cell i holds the
// nearest value strictly larger than a[i] among a[0..i-1], or -1 when there is none.
//
// Two walks, and they put the answer line in different places.
//
// Left to right: when a[i] arrives, its answer is already behind it, on the stack. Every
// smaller-or-equal value on top is useless from now on: a[i] is closer and at least as large,
// so any later element that would stop there stops at a[i] first. Pop them and record nothing.
// Whatever survives on top is a[i]'s answer, read after the while.
//
// Right to left: the stack holds positions still waiting for a larger element on their left.
// An arriving a[i] is that element for every smaller one on top, so each popped position gets
// a[i] written into its own cell, inside the while. This is NextGreater with the loop reversed.
//
// Siblings:
//   twopointers.closest.PrevGreater -- the right-to-left walk with (value, position) entries
//   PrevSmaller                     -- every comparison flipped
//   NextGreater                     -- the nearest larger element on the RIGHT
//   OnlineStockSpan901PrevGreater   -- the left-to-right walk over a stream of prices

public final class PrevGreater {
	// Time Complexity: O(n) for both, every position is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] prevGreaterLeftToRight(int[] a) {
		int n = a.length;
		int[] greater = new int[n];
		Deque<Integer> stack = new ArrayDeque<>(); // CANDIDATES: positions that might still be an answer
		for (int i = 0; i < n; i++) { // WALK: a[i] arrives
			// '<=': an equal value is not greater, so it is useless too
			while (!stack.isEmpty() && a[stack.peek()] <= a[i]) { // DROP: the top is not larger, and a[i] is closer
				stack.pop(); // POP: nobody's answer from now on, nothing is recorded
			}
			// the answer is what survived on top, and it is a[i]'s own
			greater[i] = stack.isEmpty() ? -1 : a[stack.peek()]; // ANSWER: read after the while
			stack.push(i); // PUSH: a[i] might be a later element's answer
		}
		return greater; // RETURN: -1 where nothing larger precedes
	}

	public static int[] prevGreaterRightToLeft(int[] a) {
		int n = a.length;
		int[] greater = new int[n];
		fill(greater, -1); // DEFAULT: stays -1 for a position never popped: nothing larger precedes it
		Deque<Integer> stack = new ArrayDeque<>(); // WAITING: positions still waiting for an answer
		for (int i = n - 1; i >= 0; i--) { // WALK: right to left
			// strict '>': an equal value does not answer, the position keeps waiting
			while (!stack.isEmpty() && a[i] > a[stack.peek()]) { // STOP: strictly larger; an equal value waits on
				int index = stack.pop(); // POP: a[i] is the nearest larger element left of index
				greater[index] = a[i]; // ANSWER: written into the popped position's cell
			}
			stack.push(i); // PUSH: a[i] waits for a larger element further left
		}
		return greater; // RETURN: -1 where nothing larger precedes
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 4, 2, 20, 40, 12, 30 };
		System.out.println(Arrays.toString(prevGreaterLeftToRight(a1))); // -1, 10, 4, -1, -1, 40, 40
		System.out.println(Arrays.toString(prevGreaterRightToLeft(a1))); // -1, 10, 4, -1, -1, 40, 40

		int[] a2 = { 10, 20, 30, 40 };
		System.out.println(Arrays.toString(prevGreaterLeftToRight(a2))); // -1, -1, -1, -1
		System.out.println(Arrays.toString(prevGreaterRightToLeft(a2))); // -1, -1, -1, -1

		int[] a3 = { 40, 30, 20, 10 };
		System.out.println(Arrays.toString(prevGreaterLeftToRight(a3))); // -1, 40, 30, 20
		System.out.println(Arrays.toString(prevGreaterRightToLeft(a3))); // -1, 40, 30, 20

		int[] a4 = { 5, 5, 3 };
		System.out.println(Arrays.toString(prevGreaterLeftToRight(a4))); // -1, -1, 5
		System.out.println(Arrays.toString(prevGreaterRightToLeft(a4))); // -1, -1, 5
	}
}
