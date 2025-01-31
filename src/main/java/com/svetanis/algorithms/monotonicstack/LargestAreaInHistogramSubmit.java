package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 84. Largest Area in Histogram

public final class LargestAreaInHistogramSubmit {
	// Time Complexity: O(n)

	public static int maxArea(int[] heights) {
		int n = heights.length;
		Deque<Integer> dq = new ArrayDeque<>();
		int[] left = new int[n];
		int[] right = new int[n];
		Arrays.fill(right, n);
		for (int i = 0; i < n; i++) {
			while (!dq.isEmpty() && heights[dq.peek()] >= heights[i]) {
				right[dq.pop()] = i;
			}
			left[i] = dq.isEmpty() ? -1 : dq.peek();
			dq.push(i);
		}
		return maxArea(heights, left, right);
	}

	private static int maxArea(int[] heights, int[] left, int[] right) {
		int max = 0;
		for (int i = 0; i < left.length; i++) {
			int width = right[i] - left[i] - 1;
			max = Math.max(max, heights[i] * width);
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 5, 6, 2, 3 };
		System.out.println(maxArea(a1)); // 10

		int[] a2 = { 2, 4 };
		System.out.println(maxArea(a2)); // 4
	}
}