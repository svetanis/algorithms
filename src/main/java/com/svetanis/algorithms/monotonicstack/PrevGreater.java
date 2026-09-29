package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Deque;

// Previous greater element: for each element, the nearest larger element to its left; -1 if
// none.
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

public final class PrevGreater {
	// Time Complexity: O(n) for both, every position is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] prevGreaterLeftToRight(int[] a) {
		int n = a.length;
		int[] greater = new int[n];
		Deque<Integer> stack = new ArrayDeque<>(); // positions that might still be an answer
		for (int i = 0; i < n; i++) {
			// '<=': an equal value is not greater, so it is useless too
			while (!stack.isEmpty() && a[stack.peek()] <= a[i]) {
				stack.pop(); // nobody's answer from now on, nothing is recorded
			}
			// the answer is what survived on top, and it is a[i]'s own
			greater[i] = stack.isEmpty() ? -1 : a[stack.peek()];
			stack.push(i); // a[i] might be a later element's answer
		}
		return greater;
	}

	public static int[] prevGreaterRightToLeft(int[] a) {
		int n = a.length;
		int[] greater = new int[n];
		fill(greater, -1); // stays -1 for a position never popped: nothing larger precedes it
		Deque<Integer> stack = new ArrayDeque<>(); // positions still waiting for an answer
		for (int i = n - 1; i >= 0; i--) { // right to left
			// strict '>': an equal value does not answer, the position keeps waiting
			while (!stack.isEmpty() && a[i] > a[stack.peek()]) {
				int index = stack.pop(); // a[i] is the nearest larger element left of index
				greater[index] = a[i]; // written into the popped position's cell
			}
			stack.push(i); // a[i] waits for a larger element further left
		}
		return greater;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 4, 2, 20, 40, 12, 30 };
		print(prevGreaterLeftToRight(a1)); // -1, 10, 4, -1, -1, 40, 40
		print(prevGreaterRightToLeft(a1)); // -1, 10, 4, -1, -1, 40, 40

		int[] a2 = { 10, 20, 30, 40 };
		print(prevGreaterLeftToRight(a2)); // -1, -1, -1, -1
		print(prevGreaterRightToLeft(a2)); // -1, -1, -1, -1

		int[] a3 = { 40, 30, 20, 10 };
		print(prevGreaterLeftToRight(a3)); // -1, 40, 30, 20
		print(prevGreaterRightToLeft(a3)); // -1, 40, 30, 20

		int[] a4 = { 5, 5, 3 };
		print(prevGreaterLeftToRight(a4)); // -1, -1, 5
		print(prevGreaterRightToLeft(a4)); // -1, -1, 5
	}
}
