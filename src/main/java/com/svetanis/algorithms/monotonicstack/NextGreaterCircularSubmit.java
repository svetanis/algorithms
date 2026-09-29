package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;

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

public final class NextGreaterCircularSubmit {
	// Time Complexity: O(n), 2n steps, every position pushed once and popped at most once
	// Space Complexity: O(n) for the stack

	public static int[] nextGreater(int[] a) {
		int n = a.length;
		int[] greater = new int[n];
		Arrays.fill(greater, -1);
		Deque<Integer> stack = new ArrayDeque<>(); // positions, values never rising to the top
		for (int i = 0; i < 2 * n; i++) {
			while (!stack.isEmpty() && a[stack.peek()] < a[i % n]) {
				int index = stack.pop(); // a[i % n] is the first larger one after it, going round
				greater[index] = a[i % n];
			}
			if (i < n) { // first lap only
				stack.push(i);
			}
		}
		return greater;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 1 };
		print(nextGreater(a1)); // 2, -1, 2
		// 2, 4, 2, 2, 2, 4, -1, 2
		int[] a2 = { 1, 2, 1, 1, 1, 2, 4, 1 };
		print(nextGreater(a2));
		// 5, 6, 8, 9, -1, 2, 4, 2, 4
		int[] a3 = { 4, 5, 6, 8, 9, 1, 2, 1, 2 };
		print(nextGreater(a3));
		// 6, 3, 6, 8, 4, 8, -1, 8, 3, 8, -1, 8
		int[] a4 = { 5, 2, 3, 6, 1, 4, 8, 7, 2, 3, 8, 6 };
		print(nextGreater(a4));
		// 10, 10, -1, 10, 10, 10, 10, 10, 10, 10
		int[] a5 = { 2, 1, 10, 9, 8, 7, 6, 5, 4, 3 };
		print(nextGreater(a5));
		// 4, 5, 6, 4, 5, 6, 4, 5, 6, 7, -1, 2, 3
		int[] a6 = { 3, 4, 5, 3, 4, 5, 3, 4, 5, 6, 7, 1, 2 };
		print(nextGreater(a6));
	}
}
