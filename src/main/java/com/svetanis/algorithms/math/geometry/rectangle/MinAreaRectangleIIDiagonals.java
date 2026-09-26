package com.svetanis.algorithms.math.geometry.rectangle;

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 963. Minimum Area Rectangle II

// Work from the diagonals instead of the corners. Two segments are the two
// diagonals of one rectangle exactly when they cross at their middles and
// have the same length. So every pair of points is filed under its midpoint
// and its length, and any two pairs filed together make a rectangle.
//
// The midpoint is kept doubled -- (x1 + x2, y1 + y2) -- and the length
// squared, so the key is whole numbers: no halves, no square roots.
//
// No fourth corner is ever computed, so nothing is looked up that might
// not be one of the input points (see MinAreaRectangleII's range check).

public final class MinAreaRectangleIIDiagonals {
	// Time Complexity: O(n^2) to file the pairs, plus O(k^2) for a group of k
	// pairs -- k is at most n / 2, since each point sits on one diagonal
	// of a given midpoint and length; O(n^3) only in the worst case
	// Space Complexity: O(n^2) for the pairs

	public static int minArea(int[][] points) {
		Map<Diagonal, List<int[]>> groups = diagonals(points);
		long min = Long.MAX_VALUE;
		for (List<int[]> pairs : groups.values()) {
			min = min(min, minArea(points, pairs));
		}
		return min == Long.MAX_VALUE ? 0 : (int) min;
	}

	// every pair of points, filed by doubled midpoint and squared length
	private static Map<Diagonal, List<int[]>> diagonals(int[][] points) {
		Map<Diagonal, List<int[]>> groups = new HashMap<>();
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				int[] p = points[i], q = points[j];
				long dx = q[0] - p[0], dy = q[1] - p[1];
				Diagonal key = new Diagonal(p[0] + q[0], p[1] + q[1], dx * dx + dy * dy);
				groups.computeIfAbsent(key, k -> new ArrayList<>()).add(new int[] { i, j });
			}
		}
		return groups;
	}

	// any two diagonals in one group are a rectangle: p and q are opposite
	// corners, and r, s the other two -- both next to p
	private static long minArea(int[][] points, List<int[]> pairs) {
		long min = Long.MAX_VALUE;
		for (int a = 0; a < pairs.size(); a++) {
			int[] p = points[pairs.get(a)[0]];
			for (int b = a + 1; b < pairs.size(); b++) {
				int[] r = points[pairs.get(b)[0]], s = points[pairs.get(b)[1]];
				min = min(min, abs(cross(p, r, s)));
			}
		}
		return min;
	}

	// cross product of the arrows p -> r and p -> s: the area of the
	// parallelogram they span, which here is the rectangle
	private static long cross(int[] p, int[] r, int[] s) {
		long dxr = r[0] - p[0], dyr = r[1] - p[1];
		long dxs = s[0] - p[0], dys = s[1] - p[1];
		return dxr * dys - dyr * dxs;
	}

	private record Diagonal(int sumX, int sumY, long length2) {
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 2 }, { 2, 1 }, { 1, 0 }, { 0, 1 } };
		System.out.println(minArea(points1)); // 2
		int[][] points2 = { { 0, 1 }, { 2, 1 }, { 1, 1 }, { 1, 0 }, { 2, 0 } };
		System.out.println(minArea(points2)); // 1
		int[][] points3 = { { 0, 3 }, { 1, 2 }, { 3, 1 }, { 1, 3 }, { 2, 1 } };
		System.out.println(minArea(points3)); // 0
		int[][] points4 = { { 200, 0 }, { 0, 40000 }, { 400, 1 }, { 201, 0 } };
		System.out.println(minArea(points4)); // 0
	}
}
