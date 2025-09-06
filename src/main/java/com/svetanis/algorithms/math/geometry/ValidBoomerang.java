package com.svetanis.algorithms.math.geometry;

// 1037. Valid Boomerang

// slope1 = (y2 - y1)/(x2 - x1)
// slope2 = (y3 - y2)/(x3 - x2)

public class ValidBoomerang {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static boolean isBoomerang(int[][] points) {
		int x1 = points[0][0], y1 = points[0][1]; // p1
		int x2 = points[1][0], y2 = points[1][1]; // p2
		int x3 = points[2][0], y3 = points[2][1]; // p3
		int left = (y2 - y1) * (x3 - x2);
		int right = (y3 - y2) * (x2 - x1);
		return left != right;
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 1 }, { 2, 3 }, { 3, 2 } };
		System.out.println(isBoomerang(points1)); // true

		int[][] points2 = { { 1, 1 }, { 2, 2 }, { 3, 3 } };
		System.out.println(isBoomerang(points2)); // false
	}
}
