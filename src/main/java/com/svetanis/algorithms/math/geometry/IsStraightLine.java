package com.svetanis.algorithms.math.geometry;

// 1232. Check If It Is a Straight Line

// Every point p must lie on the line through p1 and p2: the slopes
// p1 -> p2 and p1 -> p are equal.
//   (y2 - y1)/(x2 - x1) == (y - y1)/(x - x1)
// Cross-multiplied, so nothing is divided and a vertical line needs no
// special case -- this is the cross product of the two arrows being 0.
//   (x - x1) * (y2 - y1) == (y - y1) * (x2 - x1)

public final class IsStraightLine {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static boolean checkStraightLine(int[][] points) {
		int x1 = points[0][0], y1 = points[0][1]; // p1
		int x2 = points[1][0], y2 = points[1][1]; // p2
		for (int i = 2; i < points.length; i++) {
			int x = points[i][0], y = points[i][1]; // p3
			int dxLine = x2 - x1, dyLine = y2 - y1; // arrow p1 -> p2
			int dxPoint = x - x1, dyPoint = y - y1; // arrow p1 -> p
			if (dxPoint * dyLine != dyPoint * dxLine) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 } };
		System.out.println(checkStraightLine(points1)); // true

		int[][] points2 = { { 1, 1 }, { 2, 2 }, { 3, 4 }, { 4, 5 }, { 5, 6 }, { 7, 7 } };
		System.out.println(checkStraightLine(points2)); // false
	}
}
