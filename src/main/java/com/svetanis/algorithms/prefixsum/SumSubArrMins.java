package com.svetanis.algorithms.prefixsum;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 907. Sum of Subarray Minimums

public final class SumSubArrMins {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	private static final int MOD = (int) 1e9 + 7;

	public int sumSubArrMins(int[] a) {
		int n = a.length;
		int[] left = leftPrefix(a);
		int[] right = rightPrefix(a);
		long sum = 0;
		for (int i = 0; i < n; i++) {
			long count = (i - left[i]) * (right[i] - i) % MOD;
			sum = (sum + count * a[i]) % MOD;
		}
		return (int) sum;
	}

	private static int[] leftPrefix(int[] a) {
		int n = a.length;
		int[] left = new int[n];
		Arrays.fill(left, -1);
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			while (!dq.isEmpty() && a[dq.peek()] >= a[i]) {
				dq.pop();
			}
			if (!dq.isEmpty()) {
				left[i] = dq.peek();
			}
			dq.push(i);
		}
		return left;
	}

	private static int[] rightPrefix(int[] a) {
		int n = a.length;
		int[] right = new int[n];
		Arrays.fill(right, n);
		Deque<Integer> dq = new ArrayDeque<>();
		for (int i = n - 1; i >= 0; i--) {
			while (!dq.isEmpty() && a[dq.peek()] > a[i]) {
				dq.pop();
			}
			if (!dq.isEmpty()) {
				right[i] = dq.peek();
			}
			dq.push(i);
		}
		return right;
	}

	public static void main(String[] args) {
		SumSubArrMins ssm = new SumSubArrMins();
		int[] a1 = { 3, 1, 2, 4 };
		System.out.println(ssm.sumSubArrMins(a1)); // 17

		int[] a2 = { 11, 81, 94, 43, 3 };
		System.out.println(ssm.sumSubArrMins(a2)); // 444
	}
}
