package com.svetanis.algorithms.matrix;

// 1779. Find Nearest Point That Has the Same X or Y Coordinate

public final class NearestPoint {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int nearestValidPoint(int x, int y, int[][] points) {
		int index = -1;
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < points.length; i++) {
			int row = points[i][0];
			int col = points[i][1];
			if (row == x || col == y) {
				int dist = Math.abs(row - x) + Math.abs(col - y);
				if (dist < minDist) {
					minDist = dist;
					index = i;
				}
			}
		}
		return index;
	}

	public static void main(String[] agrs) {
		int[][] points1 = { { 1, 2 }, { 3, 1 }, { 2, 4 }, { 2, 3 }, { 4, 4 } };
		System.out.println(nearestValidPoint(3, 4, points1)); // 2

		int[][] points2 = { { 3, 4 } };
		System.out.println(nearestValidPoint(3, 4, points2)); // 0

		int[][] points3 = { { 2, 3 } };
		System.out.println(nearestValidPoint(3, 4, points3)); // -1
	}
}
