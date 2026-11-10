package com.svetanis.algorithms.matrix;

// 1779. Find Nearest Point That Has the Same X or Y Coordinate
//
// Input: x, y -- your location; points[i] = [a, b] -- a point. A point is valid if a == x or
// b == y. Returns the index of the valid point at the smallest Manhattan distance |x - a| + |y - b|,
// the smallest such index on a tie, or -1 when no point is valid. On LeetCode:
// 1 <= points.length <= 10^4, points[i].length == 2, 1 <= x, y, a, b <= 10^4.
//
// The one idea: one entry of points is one location, and it is either on your row or column or it
// is not. One pass keeps the closest valid point seen so far. The comparison is strict (<), so a
// later point at the same distance never replaces an earlier one -- that is the smallest-index rule.
//
// Time: O(n) -- one pass over the points.
// Space: O(1) -- the best index and its distance.

public final class NearestPoint {

	public static int nearestValidPoint(int x, int y, int[][] points) {
		int index = -1; // BEST: -1 until a valid point is seen
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < points.length; i++) { // WALK: in index order, for the tie rule
			int row = points[i][0];
			int col = points[i][1];
			if (row == x || col == y) { // VALID: shares x or y
				int dist = Math.abs(row - x) + Math.abs(col - y);
				if (dist < minDist) { // KEEP: strictly closer only -- a tie keeps the smaller index
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
