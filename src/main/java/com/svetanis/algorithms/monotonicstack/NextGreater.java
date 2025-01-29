package com.svetanis.algorithms.monotonicstack;

import static com.svetanis.java.base.utils.Print.print;
import static java.util.Arrays.fill;

import java.util.ArrayDeque;
import java.util.Deque;

public final class NextGreater {
	// Time complexity: O(n)

	public static int[] nextGreater(int[] a) {
		int n = a.length;
		int[] greater = new int[n];
		fill(greater, -1);
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			while (!dq.isEmpty() && a[i] > a[dq.peekLast()]) {
				int top = dq.pollLast();
				greater[top] = a[i];
			}
			dq.addLast(i);
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