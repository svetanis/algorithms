package com.svetanis.algorithms.math.geometry;

// 223. Rectangle Area

// find the total area covered by two
// rectangles in a 2D plane: the sum
// of the areas of both rectangles minus
// any overlapping area

public class RectangleAreaSubmit {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static int area(int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y) {
		// area of the first rectangle
		int a1 = (p2x - p1x) * (p2y - p1y);
		// area of the second rectangle
		int a2 = (p4x - p3x) * (p4y - p3y);

		// width of the overlapping area
		int left = Math.max(p1x, p3x);
		int right = Math.min(p2x, p4x);
		int width = Math.max(right - left, 0);
		// height of the overlapping area
		int top = Math.min(p2y, p4y);
		int bottom = Math.max(p1y, p3y);
		int height = Math.max(top - bottom, 0);
		// area of the overlapping region
		int overlap = width * height;
		return a1 + a2 - overlap;
	}

	public static void main(String[] args) {
		System.out.println(area(-3, 0, 3, 4, 0, -1, 9, 2)); // 45
		System.out.println(area(1, 2, 5, 5, 3, 1, 7, 4)); // 20
		System.out.println(area(-2, -2, 2, 2, -2, -2, 2, 2)); // 16
	}
}
