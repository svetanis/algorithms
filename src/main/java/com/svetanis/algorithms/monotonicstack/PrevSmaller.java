package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Deque;

public final class PrevSmaller {

	public static int[] prevSmaller(int[] a) {
		// Time Complexity: O(n);
		// Space complexity: O(n)

		int n = a.length;
		int[] smaller = new int[n];
		fill(smaller, -1);

		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = n - 1; i >= 0; i--) {
			while (!dq.isEmpty() && a[i] < a[dq.peekLast()]) {
				int top = dq.pollLast();
				smaller[top] = a[i];
			}
			dq.add(i);
		}
		return smaller;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 6, 4, 10, 2, 5 };
		print(prevSmaller(a1));

		int[] a2 = { 1, 3, 0, 2, 5 };
		print(prevSmaller(a2));
	}
}
