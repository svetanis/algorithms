package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Deque;

public final class NextSmaller {

	public static int[] nextSmaller(int[] a) {
		// time complexity: O(n);
		// Space complexity: O(n)

		int n = a.length;
		int[] smaller = new int[n];
		fill(smaller, -1);

		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			while (!dq.isEmpty() && a[i] < a[dq.peek()]) {
				int top = dq.pop();
				smaller[top] = a[i];
			}
			dq.push(i);
		}
		return smaller;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 1, 3, 4, 6, 2 };
		print(nextSmaller(a1));

		int[] a2 = { 1, 3, 3, 2, 5 };
		print(nextSmaller(a2));

		int[] a3 = { 4, 2, 1, 5, 3 };
		print(nextSmaller(a3));

		int[] a4 = { 1, 2, 3, 4, 5 };
		print(nextSmaller(a4));

		int[] a5 = { 5, 4, 3, 2, 1 };
		print(nextSmaller(a5));

		int[] a6 = { 1, 3, 5, 4, 2 };
		print(nextSmaller(a6));

		int[] a7 = { 6, 4, 2 };
		print(nextSmaller(a7));
	}
}
