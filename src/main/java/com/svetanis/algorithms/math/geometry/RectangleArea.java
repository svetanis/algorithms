package com.svetanis.algorithms.math.geometry;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.awt.Point;

// 223. Rectangle Area

// find the total area covered by two
// rectangles in a 2D plane: the sum
// of the areas of both rectangles minus
// any overlapping area

public class RectangleArea {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static int area(Point p1, Point p2, Point p3, Point p4) {
		// area of the first rectangle
		int a1 = (p2.x - p1.x) * (p2.y - p1.y);
		// area of the second rectangle
		int a2 = (p4.x - p3.x) * (p4.y - p3.y);

		// width of the overlapping area
		int left = max(p1.x, p3.x);
		int right = min(p2.x, p4.x);
		int width = max(right - left, 0);
		// height of the overlapping area
		int top = min(p2.y, p4.y);
		int bottom = max(p1.y, p3.y);
		int height = max(top - bottom, 0);
		// area of the overlapping region
		int overlap = width * height;
		return a1 + a2 - overlap;
	}

	public static void main(String[] args) {
		Point p1 = new Point(-3, 0);
		Point p2 = new Point(3, 4);
		Point p3 = new Point(0, -1);
		Point p4 = new Point(9, 2);
		System.out.println(area(p1, p2, p3, p4)); // 45

		Point r1 = new Point(1, 2);
		Point r2 = new Point(5, 5);
		Point r3 = new Point(3, 1);
		Point r4 = new Point(7, 4);
		System.out.println(area(r1, r2, r3, r4)); // 20

		Point s1 = new Point(-2, -2);
		Point s2 = new Point(2, 2);
		Point s3 = new Point(-2, -2);
		Point s4 = new Point(2, 2);
		System.out.println(area(s1, s2, s3, s4)); // 16
	}
}
