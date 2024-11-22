package com.svetanis.algorithms.dp.grid;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 85. Maximal Rectangle

public final class MaximalRectangle {
	// Time Complexity: O(n * m))
	// Space Complexity: O(n)

	public static int maxArea(char[][] grid) {
		int m = grid[0].length;
		int max = Integer.MIN_VALUE;
		int[] heights = new int[m];
		for (char[] row : grid) {
			for (int j = 0; j < m; j++) {
				heights[j] = row[j] == '1' ? heights[j] + 1 : 0;
			}
			int area = maxArea(heights);
			max = Math.max(max, area);
		}
		return max;
	}

	private static int maxArea(int[] heights) {
		int n = heights.length;
		// next smaller element to the left
		int[] left = new int[n];
		// next smaller element to the right
		int[] right = new int[n];
		Arrays.fill(right, n);
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			// remove all bars from stack taller than the current
			while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
				int index = stack.pop();
				right[index] = i;
			}
			// set the left boundary to the current bar
			left[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(i);
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int area = heights[i] * (right[i] - left[i] - 1);
			max = Math.max(max, area);
		}
		return max;
	}

	public static void main(String[] args) {
		char[][] g1 = { //
				{ '1', '0', '1', '0', '0' }, //
				{ '1', '0', '1', '1', '1' }, //
				{ '1', '1', '1', '1', '1' }, //
				{ '1', '0', '0', '1', '0' } };
		System.out.println(maxArea(g1)); // 6
		char[][] g2 = { { '0', '1' }, { '1', '0' } };
		System.out.println(maxArea(g2)); // 1
		char[][] g3 = { { '0' } };
		System.out.println(maxArea(g3)); // 0

	}
}