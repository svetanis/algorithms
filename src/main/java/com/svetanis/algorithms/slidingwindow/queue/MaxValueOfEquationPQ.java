package com.svetanis.algorithms.slidingwindow.queue;

import java.util.PriorityQueue;

// 1499. Max Value of Equation

public final class MaxValueOfEquationPQ {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int maxValue(int[][] points, int k) {
		int max = Integer.MIN_VALUE;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
		for (int[] point : points) {
			int x = point[0];
			int y = point[1];
			while (!pq.isEmpty() && x - pq.peek()[1] > k) {
				pq.poll();
			}
			if (!pq.isEmpty()) {
				max = Math.max(max, x + y + pq.peek()[0]);
			}
			pq.add(new int[] { y - x, x });
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
