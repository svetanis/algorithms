package com.svetanis.algorithms.monotonicstack;

import static java.lang.Math.max;

import java.util.ArrayDeque;
import java.util.Deque;

// 84. Largest Rectangle in Histogram
//
// Bars of width 1 stand side by side. Return the area of the largest rectangle that fits
// inside them. heights[i] is the height of bar i, 0 or more; the answer is an int.
//
// The largest rectangle is as tall as some bar, and stretches from that bar in both
// directions until a shorter bar. The stack keeps bars whose heights never fall from bottom
// to top. A shorter bar arriving ends the rectangle of every taller bar on top, and the bar
// left under a popped one is its nearest shorter bar on the left.
//
// Two siblings solve the same problem:
//   LargestAreaInHistogramPrimitives finds each bar's two walls in two separate passes,
//     previous smaller and next smaller, then measures every bar in a third.
//   LargestAreaInHistogramSubmit finds both walls in one pass into two arrays, then
//     measures every bar.
//   This file stores no walls: it measures a bar's rectangle at the moment the bar is popped.

public final class LargestAreaInHistogram {
	// Time Complexity: O(n), every bar is pushed once and popped once
	// Space Complexity: O(n) for the stack
	// heights up to 10^4 and n up to 10^5, so an area fits in an int

	public static int maxArea(int[] heights) {
		int n = heights.length;
		int i = 0; // START: the next bar to arrive
		int max = 0; // ANSWER: the largest area so far
		Deque<Integer> stack = new ArrayDeque<>(); // positions, heights never falling to the top
		while (i < n) { // WALK: bar i arrives; i moves on only when it is pushed
			if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) { // OPEN: i is not shorter than the top
				stack.push(i++); // PUSH: not shorter than the top: its rectangle is still open
			} else {
				max = max(max, areaWithTop(stack, heights, i)); // STOP: shorter: the top's rectangle ends here
			}
		}

		while (!stack.isEmpty()) { // END: i == n: the bars left reach the right end
			max = max(max, areaWithTop(stack, heights, i)); // MEASURE: the rectangle runs to the last bar
		}
		return max; // RETURN: the largest rectangle
	}

	// pops the top bar and returns the area of its rectangle, which ends at i - 1
	private static int areaWithTop(Deque<Integer> stack, int[] heights, int i) {
		int index = stack.pop(); // POP: the bar whose rectangle closes
		int right = i - 1; // RIGHT: the last position of the rectangle
		int left = stack.isEmpty() ? -1 : stack.peek(); // LEFT: the nearest shorter bar on the left
		return heights[index] * (right - left); // MEASURE: height times width, left outside, right inside
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 5, 6, 2, 3 };
		System.out.println(maxArea(a1)); // 10

		int[] a2 = { 2, 4 };
		System.out.println(maxArea(a2)); // 4
	}
}
