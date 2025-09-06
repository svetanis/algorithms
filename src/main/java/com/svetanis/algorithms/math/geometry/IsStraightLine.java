package com.svetanis.algorithms.math.geometry;

// 1232. Check If It Is a Straight Line

// slope1 = (y2 - y1)/(x2 - x1)
// slope2 = (y - y1)/(x - x1)
// (x - x1) * (y2 - y1) == (y - y1) * (x2 - x1)

public class IsStraightLine {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static boolean isBoomerang(int[][] points) {
		int x1 = points[0][0], y1 = points[0][1]; // p1
		int x2 = points[1][0], y2 = points[1][1]; // p2
		for (int i = 2; i < points.length; i++) {
			int x = points[i][0], y = points[i][1]; // p3
			int dx1 = x - x1;
			int dy1 = y2 - y1;
			int dx2 = x2 - x1;
			int dy2 = y - y1;
			if (dx1 * dy1 != dy2 * dx2) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 } };
		System.out.println(isBoomerang(points1)); // true

		int[][] points2 = { { 1, 1 }, { 2, 2 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 7, 7 } };
		System.out.println(isBoomerang(points2)); // false
	}
}
