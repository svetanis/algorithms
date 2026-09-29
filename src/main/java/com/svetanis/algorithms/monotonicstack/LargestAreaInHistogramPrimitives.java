package com.svetanis.algorithms.monotonicstack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 84. Largest Rectangle in Histogram
//
// Bars of width 1 stand side by side. Return the area of the largest rectangle that fits
// inside them.
//
// Every rectangle is as tall as its shortest bar, so give each bar the widest rectangle it is
// the shortest bar of. That rectangle stretches until the nearest strictly shorter bar on each
// side: the previous smaller and the next smaller. Both walls are outside the rectangle, so the
// width is right - left - 1, with -1 for no wall on the left and n for none on the right.
// LargestAreaInHistogramSubmit finds both walls in a single pass.

public final class LargestAreaInHistogramPrimitives {
	// Time Complexity: O(n), three passes, every position pushed once and popped at most once per pass
	// Space Complexity: O(n) for the stack and the two wall arrays
	// heights up to 10^4 and n up to 10^5, so an area fits in an int

	public static int maxArea(int[] heights) {
		int n = heights.length;
		int[] left = previousSmaller(heights); // the left wall of each bar's rectangle, -1 = none
		int[] right = nextSmaller(heights); // the right wall, n = none
		int max = 0;
		for (int i = 0; i < n; i++) {
			int width = right[i] - left[i] - 1; // strictly between the walls
			max = Math.max(max, heights[i] * width); // bar i is the shortest inside, so it sets the height
		}
		return max;
	}

	// left to right, the wall is behind the walk: read what survived, after the while
	private static int[] previousSmaller(int[] a) {
		int n = a.length;
		int[] prev = new int[n];
		Deque<Integer> stack = new ArrayDeque<>(); // positions that might still be a wall
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && a[stack.peek()] >= a[i]) { // an equal bar is not a wall
				stack.pop();
			}
			prev[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(i);
		}
		return prev;
	}

	// left to right, the wall is ahead of the walk: written inside, into the popped bar's cell
	private static int[] nextSmaller(int[] a) {
		int n = a.length;
		int[] next = new int[n];
		Arrays.fill(next, n); // never popped: nothing shorter to the right, the rectangle runs to the edge
		Deque<Integer> stack = new ArrayDeque<>(); // positions still waiting for a wall
		for (int i = 0; i < n; i++) {
			while (!stack.isEmpty() && a[i] < a[stack.peek()]) { // strict: an equal bar is not a wall
				int index = stack.pop();
				next[index] = i;
			}
			stack.push(i);
		}
		return next;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 1, 5, 6, 2, 3 };
		System.out.println(maxArea(a1)); // 10

		int[] a2 = { 2, 4 };
		System.out.println(maxArea(a2)); // 4

		int[] a3 = { 1, 2, 3, 4, 5 };
		System.out.println(maxArea(a3)); // 9, every rectangle runs to the right edge

		int[] a4 = { 2, 2 };
		System.out.println(maxArea(a4)); // 4, equal bars
	}
}
