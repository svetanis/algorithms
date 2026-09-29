package com.svetanis.algorithms.monotonicstack;

import static java.lang.Math.max;

import java.util.ArrayDeque;
import java.util.Deque;

// 84. Largest Rectangle in Histogram
//
// Bars of width 1 stand side by side. Return the area of the largest rectangle that fits
// inside them.
//
// The largest rectangle is as tall as some bar, and stretches from that bar in both
// directions until a shorter bar. The stack keeps bars whose heights never fall from bottom
// to top. A shorter bar arriving ends the rectangle of every taller bar on top, and the bar
// left under a popped one is its nearest shorter bar on the left.

public final class LargestAreaInHistogram {
	// Time Complexity: O(n), every bar is pushed once and popped once
	// Space Complexity: O(n) for the stack
	// heights up to 10^4 and n up to 10^5, so an area fits in an int

	public static int maxArea(int[] heights) {
		int n = heights.length;
		int i = 0;
		int max = 0;
		Deque<Integer> stack = new ArrayDeque<>(); // positions, heights never falling to the top
		while (i < n) {
			if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
				stack.push(i++); // not shorter than the top: its rectangle is still open
			} else {
				max = max(max, areaWithTop(stack, heights, i)); // shorter: the top's rectangle ends here
			}
		}

		while (!stack.isEmpty()) { // i == n: the bars left reach the right end
			max = max(max, areaWithTop(stack, heights, i));
		}
		return max;
	}

	// pops the top bar and returns the area of its rectangle, which ends at i - 1
	private static int areaWithTop(Deque<Integer> stack, int[] heights, int i) {
		int index = stack.pop();
		int right = i - 1; // the last position of the rectangle
		int left = stack.isEmpty() ? -1 : stack.peek(); // the nearest shorter bar on the left
		return heights[index] * (right - left);
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 5, 6, 2, 3 };
		System.out.println(maxArea(a1)); // 10

		int[] a2 = { 2, 4 };
		System.out.println(maxArea(a2)); // 4
	}
}
