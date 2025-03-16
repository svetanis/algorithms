package com.svetanis.algorithms.slidingwindow.queue;

import java.util.ArrayDeque;
import java.util.Deque;

// 1499. Max Value of Equation

public final class MaxValueOfEquation {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int maxValue(int[][] points, int k) {
		int max = Integer.MIN_VALUE;
		Deque<int[]> dq = new ArrayDeque<>();
		for (int[] point : points) {
			int x = point[0];
			int y = point[1];
			while (!dq.isEmpty() && x - dq.peekFirst()[1] > k) {
				dq.pollFirst();
			}
			if (!dq.isEmpty()) {
				max = Math.max(max, x + y + dq.peekFirst()[0]);
			}
			while (!dq.isEmpty() && y - x >= dq.peekLast()[0]) {
				dq.pollLast();
			}
			dq.addLast(new int[] { y - x, x });
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 3 }, { 2, 0 }, { 5, 10 }, { 6, -10 } };
		System.out.println(maxValue(points1, 1)); // 4
		int[][] points2 = { { 0, 0 }, { 3, 0 }, { 9, 2 } };
		System.out.println(maxValue(points2, 3)); // 3
	}
}
