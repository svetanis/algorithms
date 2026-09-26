package com.svetanis.algorithms.math.geometry;

import java.awt.Point;

// given two vectors a=(x1,y1) and b=(x2,y2)
// cross product a x b = x1 * y2 - x2 * y1
// a x b > 0: b turns left 
// a x b < 0: b turns right
// a x b == 0: b does not turn 

// (p - s1) x (p - s2) > 0 --> left
// (p - s1) x (p - s2) < 0 --> right
// (p - s1) x (p - s2) == 0 --> touch

// cross product = (s2.x - s1.x) * (p.y - s1.y) - (s2.y - s1.y) * (p.x - s1.x)

// CSES: Point Location

// NOTE the types. Every value below is a product of two coordinate DIFFERENCES,
// and CSES allows coordinates to 10^9, so a difference reaches 2 * 10^9 and the
// product reaches 4 * 10^18 -- nine digits past what an int holds. Only the SIGN
// is ever read, and the sign is exactly what an overflow destroys -- so every
// difference is cast to long before it is subtracted.

public final class PointLocation {

	public static String pointLocation(Point s1, Point s2, Point p) {
		// vector a=(x1, y1)
		long x1 = (long) s2.x - s1.x;
		long y1 = (long) s2.y - s1.y;
		// vector b=(x2, y2)
		long x2 = (long) p.x - s1.x;
		long y2 = (long) p.y - s1.y;

		// cross product a x b = x1 * y2 - x2 * y1
		long product = x1 * y2 - x2 * y1;
		if (product > 0) {
			return "LEFT";
		} else if (product < 0) {
			return "RIGHT";
		} else {
			return "TOUCH";
		}
	}

	// The same function seen from the other end, not a different test.
	// Write u = p - s1 and d = s2 - s1. Then p - s2 is u - d, and
	//   u x (u - d) = (u x u) - (u x d) = -(u x d) = d x u = (s2 - s1) x (p - s1)
	// which is what pointLocation computes. The SAME VALUE, not merely the same
	// sign -- so the two methods agree on TOUCH as well as on LEFT and RIGHT
	public static String pointLocation2(Point s1, Point s2, Point p) {
		// vector a=(x1, y1)
		long x1 = (long) p.x - s1.x;
		long y1 = (long) p.y - s1.y;
		// vector b=(x2, y2)
		long x2 = (long) p.x - s2.x;
		long y2 = (long) p.y - s2.y;
		// cross product a x b = x1 * y2 - x2 * y1
		long product = x1 * y2 - x2 * y1;
		if (product > 0) {
			return "LEFT";
		} else if (product < 0) {
			return "RIGHT";
		} else {
			return "TOUCH";
		}
	}

	public static void main(String[] args) {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(5, 3);
		Point p3 = new Point(2, 3);
		System.out.println(pointLocation(p1, p2, p3)); // left

		Point p4 = new Point(1, 1);
		Point p5 = new Point(5, 3);
		Point p6 = new Point(4, 1);
		System.out.println(pointLocation(p4, p5, p6)); // right

		Point p7 = new Point(1, 1);
		Point p8 = new Point(5, 3);
		Point p9 = new Point(3, 2);
		System.out.println(pointLocation(p7, p8, p9)); // touch

		// CSES's own range: needs long
		int max = 1000000000;
		Point far1 = new Point(-max, -max);
		Point far2 = new Point(max, max);
		Point far3 = new Point(-max, max);
		System.out.println(pointLocation(far1, far2, far3)); // left
		System.out.println(pointLocation2(far1, far2, far3)); // left
	}
}