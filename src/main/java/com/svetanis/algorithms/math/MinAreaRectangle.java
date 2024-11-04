package com.svetanis.algorithms.math;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 939. Minimum Area Rectangle

public final class MinAreaRectangle {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	public static int minArea(int[][] grid) {
		List<Point> points = points(grid);
		Map<Integer, Set<Integer>> map = mapped(points);
		int min = minArea(points, map);
		return min == MAX_VALUE ? 0 : min;
	}

	private static int minArea(List<Point> points, Map<Integer, Set<Integer>> map) {
		int min = MAX_VALUE;
		for (int i = 0; i < points.size(); i++) {
			Point p1 = points.get(i);
			for (int j = i + 1; j < points.size(); j++) {
				Point p2 = points.get(j);
				if (p1.x == p2.x || p1.y == p2.y) {
					continue;
				}
				boolean one = map.get(p1.x).contains(p2.y);
				boolean two = map.get(p2.x).contains(p1.y);
				if (one && two) {
					int area = abs(p1.x - p2.x) * abs(p1.y - p2.y);
					min = min(min, area);
				}
			}
		}
		return min;
	}

	private static Map<Integer, Set<Integer>> mapped(List<Point> points) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (Point point : points) {
			int x = point.x;
			map.computeIfAbsent(x, k -> new HashSet<>());
			map.get(x).add(point.y);
		}
		return map;
	}

	private static List<Point> points(int[][] points) {
		List<Point> list = new ArrayList<>();
		for (int[] point : points) {
			list.add(new Point(point[0], point[1]));
		}
		return list;
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 1 }, { 1, 3 }, { 3, 1 }, { 3, 3 }, { 2, 2 } };
		System.out.println(minArea(points1)); // 4
		int[][] points2 = { { 1, 1 }, { 1, 3 }, { 3, 1 }, { 3, 3 }, { 4, 1 }, { 4, 3 } };
		System.out.println(minArea(points2)); // 2
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
