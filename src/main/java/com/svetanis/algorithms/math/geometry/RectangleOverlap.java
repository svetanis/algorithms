package com.svetanis.algorithms.math.geometry;

// 836. Rectangle Overlap

public class RectangleOverlap {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static boolean rectOverlap(int[] rec1, int[] rec2) {
		// bottom left of first rectangle x1
		int bot1X = rec1[0], bot1Y = rec1[1];
		// top right of first rectangle x2
		int top1X = rec1[2], top1Y = rec1[3];
		// bottom left of second rectangle x3
		int bot2X = rec2[0], bot2Y = rec2[1];
		// top right of second rectangle x4
		int top2X = rec2[2], top2Y = rec2[3];
		// one rectangle is to the left of the other
		boolean left = bot2X >= top1X || top2X <= bot1X;
		// one rectangle is to the right of the other
		boolean right = top1X <= bot2X || bot1X >= top2X;
		// one rectangle is above the other
		boolean above = bot2Y >= top1Y || bot1Y >= top2Y;
		// one rectangle is below the other
		boolean below = top1Y <= bot2Y || top2Y <= bot1Y;
		return !(left || right || above || below);
	}

	public static void main(String[] args) {
		int[] a1 = { 0, 0, 2, 2 };
		int[] a2 = { 1, 1, 3, 3 };
		System.out.println(rectOverlap(a1, a2)); // true

		int[] a3 = { 0, 0, 1, 1 };
		int[] a4 = { 1, 0, 2, 1 };
		System.out.println(rectOverlap(a3, a4)); // false

		int[] a5 = { 0, 0, 1, 1 };
		int[] a6 = { 2, 2, 3, 3 };
		System.out.println(rectOverlap(a5, a6)); // false

		int[] a7 = { 5, 15, 8, 18 };
		int[] a8 = { 0, 3, 7, 9 };
		System.out.println(rectOverlap(a7, a8)); // false

		int[] a9 = { 7, 8, 13, 15 };
		int[] a10 = { 10, 8, 12, 20 };
		System.out.println(rectOverlap(a9, a10)); // true

	}
}
