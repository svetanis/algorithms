package com.svetanis.algorithms.monotonicstack;

import static java.lang.Math.max;

import java.util.ArrayDeque;
import java.util.Deque;

// 84. Largest Area in Histogram

public final class LargestAreaInHistogram {
	// Time Complexity: O(n)

	public static int maxArea(int[] a) {
		int n = a.length;
		int i = 0;
		int max = 0;
		Deque<Integer> dq = new ArrayDeque<>();
		while (i < n) {
			if (dq.isEmpty() || a[dq.peek()] <= a[i]) {
				dq.push(i++);
			} else {
				max = max(max, areaWithTop(dq, a, i));
			}
		}

		while (!dq.isEmpty()) {
			max = max(max, areaWithTop(dq, a, i));
		}
		return max;
	}

	private static int areaWithTop(Deque<Integer> dq, int[] a, int i) {
		int top = dq.pop();
		int right = i - 1;
		int left = dq.isEmpty() ? -1 : dq.peek();
		return a[top] * (right - left);
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 5, 6, 2, 3 };
		System.out.println(maxArea(a1)); // 10

		int[] a2 = { 2, 4 };
		System.out.println(maxArea(a2)); // 4
	}
}