package com.svetanis.algorithms.math;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 963. Minimum Area Rectangle II

public final class MinAreaRectangleII {
	// Time Complexity: O(n^3)
	// Space Complexity: O(n)

	public static int minArea(int[][] grid) {
		List<Point> points = points(grid);
		Set<Integer> set = points(points);
		int min = minArea(points, set);
		return min == Integer.MAX_VALUE ? 0 : min;
	}

	private static int minArea(List<Point> points, Set<Integer> set) {
		int min = Integer.MAX_VALUE;
		int n = points.size();
		for (int i = 0; i < n; i++) {
			Point p1 = points.get(i);
			for (int j = 0; j < n; j++) {
				if (i == j) {
					continue;
				}
				Point p2 = points.get(j);
				for (int k = j + 1; k < n; k++) {
					if (k == i) {
						continue;
					}
					Point p3 = points.get(k);
					int x4 = p2.x - p1.x + p3.x;
					int y4 = p2.y - p1.y + p3.y;
					int hash = encode(new Point(x4, y4));
					if (set.contains(hash)) {
						int v1 = (p2.x - p1.x) * (p3.x - p1.x);
						int v2 = (p2.y - p1.y) * (p3.y - p1.y);
						if (v1 + v2 == 0) {
							int one = (p2.x - p1.x) * (p2.x - p1.x);
							int two = (p2.y - p1.y) * (p2.y - p1.y);
							int width = one + two;
							int three = (p3.x - p1.x) * (p3.x - p1.x);
							int four = (p3.y - p1.y) * (p3.y - p1.y);
							int height = three + four;
							int area = (int) Math.sqrt((long) width * height);
							min = Math.min(min, area);
						}
					}
				}
			}
		}
		return min;
	}

	private static Set<Integer> points(Iterable<Point> iterable) {
		Set<Integer> set = new HashSet<>();
		for (Point p : iterable) {
			set.add(encode(p));
		}
		return set;
	}

	private static List<Point> points(int[][] points) {
		List<Point> list = new ArrayList<>();
		for (int[] point : points) {
			list.add(new Point(point[0], point[1]));
		}
		return list;
	}

	private static int encode(Point p) {
		return p.x * 40001 + p.y;
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 2 }, { 2, 1 }, { 1, 0 }, { 0, 1 } };
		System.out.println(minArea(points1)); // 2
		int[][] points2 = { { 0, 1 }, { 2, 1 }, { 1, 1 }, { 1, 0 }, { 2, 0 } };
		System.out.println(minArea(points2)); // 1
		int[][] points3 = { { 0, 3 }, { 1, 2 }, { 3, 1 }, { 1, 3 }, { 2, 1 } };
		System.out.println(minArea(points3)); // 0
	}

	private static class Point {
		private int x;
		private int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
