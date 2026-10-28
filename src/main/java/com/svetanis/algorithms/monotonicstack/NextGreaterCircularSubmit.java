package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 503. Next Greater Element II
//
// The array is circular: after the last element comes the first again. For each element,
// return the first larger element going around; -1 if none.
//
// Walk the array twice, i from 0 to 2n - 1, reading a[i % n]. The first lap is the plain
// next-greater pass; the second lap only answers the positions still waiting and pushes
// nothing, since every position already has its place on the stack.
//
// Input: an array of integers a. Returns: an array of the same length whose cell i holds the
// first value strictly larger than a[i] going round the circle, or -1 when there is none.
//
// Siblings:
//   NextGreaterCircular -- the same answer over a List, as two separate loops
//   NextGreater         -- the plain pass, with no wrap-around

public final class NextGreaterCircularSubmit {
	// Time Complexity: O(n), 2n steps, every position pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] nextGreater(int[] a) {
		int n = a.length;
		int[] greater = new int[n];
		Arrays.fill(greater, -1); // DEFAULT: -1 where nothing larger comes, even going round
		Deque<Integer> stack = new ArrayDeque<>(); // WAITING: positions, values never rising to the top
		for (int i = 0; i < 2 * n; i++) { // WALK: two laps, reading a[i % n]
			while (!stack.isEmpty() && a[stack.peek()] < a[i % n]) { // STOP: strictly larger; an equal value waits on
				int index = stack.pop(); // POP: a[i % n] is the first larger one after it, going round
				greater[index] = a[i % n]; // ANSWER: written into the popped position's cell
			}
			if (i < n) { // WRAP: first lap only
				stack.push(i); // PUSH: i waits for a larger value
			}
		}
		return greater; // RETURN: -1 where nothing larger comes
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 1 };
		System.out.println(Arrays.toString(nextGreater(a1))); // 2, -1, 2
		// 2, 4, 2, 2, 2, 4, -1, 2
		int[] a2 = { 1, 2, 1, 1, 1, 2, 4, 1 };
		System.out.println(Arrays.toString(nextGreater(a2)));
		// 5, 6, 8, 9, -1, 2, 4, 2, 4
		int[] a3 = { 4, 5, 6, 8, 9, 1, 2, 1, 2 };
		System.out.println(Arrays.toString(nextGreater(a3)));
		// 6, 3, 6, 8, 4, 8, -1, 8, 3, 8, -1, 8
		int[] a4 = { 5, 2, 3, 6, 1, 4, 8, 7, 2, 3, 8, 6 };
		System.out.println(Arrays.toString(nextGreater(a4)));
		// 10, 10, -1, 10, 10, 10, 10, 10, 10, 10
		int[] a5 = { 2, 1, 10, 9, 8, 7, 6, 5, 4, 3 };
		System.out.println(Arrays.toString(nextGreater(a5)));
		// 4, 5, 6, 4, 5, 6, 4, 5, 6, 7, -1, 2, 3
		int[] a6 = { 3, 4, 5, 3, 4, 5, 3, 4, 5, 6, 7, 1, 2 };
		System.out.println(Arrays.toString(nextGreater(a6)));
	}
}
