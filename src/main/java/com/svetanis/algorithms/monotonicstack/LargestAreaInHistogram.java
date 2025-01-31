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
		Deque<Integer> stack = new ArrayDeque<>();
		while (i < n) {
			if (stack.isEmpty() || a[stack.peek()] <= a[i]) {
				stack.push(i++);
			} else {
				max = max(max, areaWithTop(stack, a, i));
			}
		}

		while (!stack.isEmpty()) {
			max = max(max, areaWithTop(stack, a, i));
		}
		return max;
	}

	private static int areaWithTop(Deque<Integer> stack, int[] a, int i) {
		int top = stack.pop();
		if (stack.isEmpty()) {
			return a[top] * i;
		} else {
			return a[top] * (i - stack.peek() - 1);
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 5, 6, 2, 3 };
		System.out.println(maxArea(a1)); // 10

		int[] a2 = { 2, 4 };
		System.out.println(maxArea(a2)); // 4
	}
}