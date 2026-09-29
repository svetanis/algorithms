package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 84. Largest Rectangle in Histogram
//
// Bars of width 1 stand side by side. Return the area of the largest rectangle that fits
// inside them.
//
// Each bar's rectangle is as tall as the bar and ends at the nearest shorter bar on each
// side. One pass finds both: a bar popped by i has i as its right boundary, and the bar left
// on top when i is pushed is i's left boundary. The width is right - left - 1.

public final class LargestAreaInHistogramSubmit {
	// Time Complexity: O(n)
	// Space Complexity: O(n) for the stack and the two boundary arrays
	// heights up to 10^4 and n up to 10^5, so an area fits in an int

	public static int maxArea(int[] heights) {
		int n = heights.length;
		Deque<Integer> stack = new ArrayDeque<>(); // positions, heights rising to the top
		int[] left = new int[n]; // the nearest shorter bar on the left, -1 for none
		int[] right = new int[n]; // the nearest shorter bar on the right, n for none
		Arrays.fill(right, n);
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
				right[stack.pop()] = i; // pop time: i is the popped bar's right boundary
			}
			left[i] = stack.isEmpty() ? -1 : stack.peek(); // push time: the survivor is i's left boundary
			stack.push(i);
		}
		return maxArea(heights, left, right);
	}

	private static int maxArea(int[] heights, int[] left, int[] right) {
		int max = 0;
		for (int i = 0; i < left.length; i++) {
			int width = right[i] - left[i] - 1; // both boundaries excluded
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
