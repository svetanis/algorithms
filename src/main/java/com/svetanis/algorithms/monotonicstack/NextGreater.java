package com.svetanis.algorithms.monotonicstack;

import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Next greater element: for each element, the first larger element to its right; -1 if none.
//
// Scan left to right. The stack holds the positions still waiting for a larger element, and
// their values never rise from bottom to top. An arriving element answers every smaller one
// on top: it is the first larger element each of them has met.
//
// Input: an array of integers a. Returns: an array of the same length whose cell i holds the
// first value strictly larger than a[i] among a[i+1..n-1], or -1 when there is none.
//
// Siblings:
//   twopointers.closest.NextGreater -- the same scan with (value, position) entries
//   NextGreater496                  -- 496: two arrays of distinct values; the answers are
//                                      stored by value in a map, then looked up per query
//   NextGreaterCircular, NextGreaterCircularSubmit -- 503: the array wraps around
//   NextSmaller                     -- the comparison flipped
//   PrevGreater                     -- the nearest larger element on the LEFT

public final class NextGreater {
	// Time Complexity: O(n), every position is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] nextGreater(int[] a) {
		int n = a.length;
		int[] greater = new int[n];
		fill(greater, -1); // DEFAULT: stays -1 for an element nothing larger follows
		Deque<Integer> stack = new ArrayDeque<>(); // WAITING: holds positions, never values
		for (int i = 0; i < n; i++) { // WALK: a[i] arrives
			while (!stack.isEmpty() && a[i] > a[stack.peek()]) { // STOP: strictly larger; an equal value waits on
				int index = stack.pop(); // POP: a[i] is the first element to beat a[index]
				greater[index] = a[i]; // ANSWER: written into the popped position's cell
			}
			stack.push(i); // PUSH: a[i] has no answer yet
		}
		return greater; // RETURN: -1 where nothing larger follows
	}

	public static void main(String[] args) {
		int[] a1 = { 11, 13, 21, 3 };
		System.out.println(Arrays.toString(nextGreater(a1))); // 13, 21, -1, -1

		int[] a2 = { 4, 5, 2, 25 };
		System.out.println(Arrays.toString(nextGreater(a2))); // 5, 25, 25, -1

		int[] a3 = { 13, 7, 6, 12 };
		System.out.println(Arrays.toString(nextGreater(a3))); // -1, 12, 12, -1

		int[] a4 = { 4, 5, 2, 10 };
		System.out.println(Arrays.toString(nextGreater(a4))); // 5, 10, 10, -1

		int[] a5 = { 3, 2, 1 };
		System.out.println(Arrays.toString(nextGreater(a5))); // -1, -1, -1

		int[] a6 = { 5, 2, 4, 6, 1 };
		System.out.println(Arrays.toString(nextGreater(a6))); // 6, 4, 6, -1, -1
	}
}
