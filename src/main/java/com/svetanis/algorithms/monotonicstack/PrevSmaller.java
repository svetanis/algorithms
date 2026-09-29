package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Deque;

// Previous smaller element: for each element, the nearest smaller element to its left; -1 if
// none.
//
// PrevGreater with every comparison flipped. Two walks, and they put the answer line in
// different places.
//
// Left to right: when a[i] arrives, its answer is already behind it, on the stack. Every
// larger-or-equal value on top is useless from now on: a[i] is closer and at most as large, so
// any later element that would stop there stops at a[i] first. Pop them and record nothing.
// Whatever survives on top is a[i]'s answer, read after the while.
//
// Right to left: the stack holds positions still waiting for a smaller element on their left.
// An arriving a[i] is that element for every larger one on top, so each popped position gets
// a[i] written into its own cell, inside the while. This is NextSmaller with the loop reversed.

public final class PrevSmaller {
	// Time Complexity: O(n) for both, every position is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] prevSmallerLeftToRight(int[] a) {
		int n = a.length;
		int[] smaller = new int[n];
		Deque<Integer> stack = new ArrayDeque<>(); // positions that might still be an answer
		for (int i = 0; i < n; i++) {
			// '>=': an equal value is not smaller, so it is useless too
			while (!stack.isEmpty() && a[stack.peek()] >= a[i]) {
				stack.pop(); // nobody's answer from now on, nothing is recorded
			}
			// the answer is what survived on top, and it is a[i]'s own
			smaller[i] = stack.isEmpty() ? -1 : a[stack.peek()];
			stack.push(i); // a[i] might be a later element's answer
		}
		return smaller;
	}

	public static int[] prevSmallerRightToLeft(int[] a) {
		int n = a.length;
		int[] smaller = new int[n];
		fill(smaller, -1); // stays -1 for a position never popped: nothing smaller precedes it
		Deque<Integer> stack = new ArrayDeque<>(); // positions still waiting for an answer
		for (int i = n - 1; i >= 0; i--) { // right to left
			// strict '<': an equal value does not answer, the position keeps waiting
			while (!stack.isEmpty() && a[i] < a[stack.peek()]) {
				int index = stack.pop(); // a[i] is the nearest smaller element left of index
				smaller[index] = a[i]; // written into the popped position's cell
			}
			stack.push(i); // a[i] waits for a smaller element further left
		}
		return smaller;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 6, 4, 10, 2, 5 };
		print(prevSmallerLeftToRight(a1)); // -1, 1, 1, 4, 1, 2
		print(prevSmallerRightToLeft(a1)); // -1, 1, 1, 4, 1, 2

		int[] a2 = { 1, 3, 0, 2, 5 };
		print(prevSmallerLeftToRight(a2)); // -1, 1, -1, 0, 2
		print(prevSmallerRightToLeft(a2)); // -1, 1, -1, 0, 2

		int[] a3 = { 3, 3, 5 };
		print(prevSmallerLeftToRight(a3)); // -1, -1, 3
		print(prevSmallerRightToLeft(a3)); // -1, -1, 3
	}
}
