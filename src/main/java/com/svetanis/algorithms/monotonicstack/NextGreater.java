package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Deque;

// Next greater element: for each element, the first larger element to its right; -1 if none.
//
// Scan left to right. The stack holds the positions still waiting for a larger element, and
// their values never rise from bottom to top. An arriving element answers every smaller one
// on top: it is the first larger element each of them has met.

public final class NextGreater {
	// Time Complexity: O(n), every position is pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] nextGreater(int[] a) {
		int n = a.length;
		int[] greater = new int[n];
		fill(greater, -1); // stays -1 for an element nothing larger follows
		Deque<Integer> stack = new ArrayDeque<>(); // holds positions, never values
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && a[i] > a[stack.peek()]) {
				int index = stack.pop(); // a[i] is the first element to beat a[index]
				greater[index] = a[i];
			}
			stack.push(i); // a[i] has no answer yet
		}
		return greater;
	}

	public static void main(String[] args) {
		int[] a1 = { 11, 13, 21, 3 };
		print(nextGreater(a1)); // 13, 21, -1, -1

		int[] a2 = { 4, 5, 2, 25 };
		print(nextGreater(a2)); // 5, 25, 25, -1

		int[] a3 = { 13, 7, 6, 12 };
		print(nextGreater(a3)); // -1, 12, 12, -1

		int[] a4 = { 4, 5, 2, 10 };
		print(nextGreater(a4)); // 5, 10, 10, -1

		int[] a5 = { 3, 2, 1 };
		print(nextGreater(a5)); // -1, -1, -1

		int[] a6 = { 5, 2, 4, 6, 1 };
		print(nextGreater(a6)); // 6, 4, 6, -1, -1
	}
}
