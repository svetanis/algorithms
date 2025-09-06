package com.svetanis.algorithms.math.geometry;

// 3047. Find the Largest Area of Square Inside Two Rectangles

public class MaxSquareAreaInsideTwoRectangles {
	// Time Complexity: O(n^2)
	// Space Complexity: O(1)

	public static long maxSquareArea(int[][] bottomLeft, int[][] topRight) {
		long maxArea = 0;
		int n = bottomLeft.length;
		for (int i = 0; i < n; i++) {
			int x1 = bottomLeft[i][0], y1 = bottomLeft[i][1];
			int x2 = topRight[i][0], y2 = topRight[i][1];
			for (int j = i + 1; j < n; j++) {
				int x3 = bottomLeft[j][0], y3 = bottomLeft[j][1];
				int x4 = topRight[j][0], y4 = topRight[j][1];
				int width = Math.min(x2, x4) - Math.max(x1, x3);
				int height = Math.min(y2, y4) - Math.max(y1, y3);
				int maxEdge = Math.min(width, height);
				if (maxEdge > 0) {
					maxArea = Math.max(maxArea, 1L * maxEdge * maxEdge);
				}
			}
		}
		return maxArea;
	}

	public static void main(String[] args) {
		int[][] bl1 = { { 1, 1 }, { 2, 2 }, { 3, 1 } };
		int[][] tr1 = { { 3, 3 }, { 4, 4 }, { 6, 6 } };
		System.out.println(maxSquareArea(bl1, tr1)); // 1

		int[][] bl2 = { { 1, 1 }, { 1, 3 }, { 1, 5 } };
		int[][] tr2 = { { 5, 5 }, { 5, 7 }, { 5, 9 } };
		System.out.println(maxSquareArea(bl2, tr2)); // 4

		int[][] bl3 = { { 1, 1 }, { 2, 2 }, { 1, 2 } };
		int[][] tr3 = { { 3, 3 }, { 4, 4 }, { 3, 4 } };
		System.out.println(maxSquareArea(bl3, tr3)); // 1

		int[][] bl4 = { { 1, 1 }, { 3, 3 }, { 3, 1 } };
		int[][] tr4 = { { 2, 2 }, { 4, 4 }, { 4, 2 } };
		System.out.println(maxSquareArea(bl4, tr4)); // 0
	}
}
