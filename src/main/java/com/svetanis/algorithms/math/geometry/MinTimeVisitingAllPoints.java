package com.svetanis.algorithms.math.geometry;

// 1266. Minimum Time Visiting All Points

public class MinTimeVisitingAllPoints {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int minTime(int[][] points) {
		int total = 0;
		for (int i = 1; i < points.length; i++) {
			total += time(points[i - 1], points[i]);
		}
		return total;
	}

	private static int time(int[] p1, int[] p2) {
		int dx = Math.abs(p1[0] - p2[0]);
		int dy = Math.abs(p1[1] - p2[1]);
		return Math.max(dx, dy);
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 1 }, { 3, 4 }, { -1, 0 } };
		System.out.println(minTime(points1)); // 7

		int[][] points2 = { { 3, 2 }, { -2, 2 } };
		System.out.println(minTime(points2)); // 5
	}
}
