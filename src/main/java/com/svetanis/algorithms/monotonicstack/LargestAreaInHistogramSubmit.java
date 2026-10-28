package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 84. Largest Rectangle in Histogram
//
// Bars of width 1 stand side by side. Return the area of the largest rectangle that fits
// inside them. heights[i] is the height of bar i, 0 or more; the answer is an int.
//
// Each bar's rectangle is as tall as the bar and ends at the nearest shorter bar on each
// side. One pass finds both: a bar popped by i has i as its right boundary, and the bar left
// on top when i is pushed is i's left boundary. The width is right - left - 1.
//
// The pop is >=, so an EQUAL bar can be a right boundary: in a run of equal bars, each one
// but the last is cut short on the right. The last bar of the run has a strictly shorter
// right boundary and, since the earlier equal bars were popped, a strictly shorter left
// one, so it measures the full run and the maximum is still right. [2, 2]: bar 0 gets
// width 1, bar 1 gets width 2, answer 4.
//
// LargestAreaInHistogramPrimitives finds the same two walls in two separate passes.
// LargestAreaInHistogram stores no walls: it measures a bar's rectangle when the bar is popped.

public final class LargestAreaInHistogramSubmit {
	// Time Complexity: O(n), every position is pushed once and popped at most once
	// Space Complexity: O(n) for the stack and the two boundary arrays
	// heights up to 10^4 and n up to 10^5, so an area fits in an int

	public static int maxArea(int[] heights) {
		int n = heights.length;
		Deque<Integer> stack = new ArrayDeque<>(); // positions, heights rising to the top
		int[] left = new int[n]; // the nearest shorter bar on the left, -1 for none
		int[] right = new int[n]; // the nearest bar on the right not taller (equal counts), n for none
		Arrays.fill(right, n); // START: never popped means no shorter bar on the right
		for (int i = 0; i < n; i++) { // WALK: bar i arrives
			while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) { // STOP: i is not taller than the top
				right[stack.pop()] = i; // RIGHT: pop time: i is the popped bar's right boundary
			}
			left[i] = stack.isEmpty() ? -1 : stack.peek(); // LEFT: push time: the survivor is i's left boundary
			stack.push(i); // PUSH: i waits for its right boundary
		}
		return maxArea(heights, left, right); // RETURN: measure every bar
	}

	private static int maxArea(int[] heights, int[] left, int[] right) {
		int max = 0; // ANSWER: the largest area so far
		for (int i = 0; i < left.length; i++) { // WALK: bar i sets the height
			int width = right[i] - left[i] - 1; // WIDTH: both boundaries excluded
			max = Math.max(max, heights[i] * width); // MEASURE: bar i is the shortest inside its rectangle
		}
		return max; // RETURN: the largest rectangle
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 5, 6, 2, 3 };
		System.out.println(maxArea(a1)); // 10

		int[] a2 = { 2, 4 };
		System.out.println(maxArea(a2)); // 4
	}
}
