package com.svetanis.algorithms.search.quickselect.points;

import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Random.randomIndex;
import static com.svetanis.java.base.utils.Swap.swap;
import static java.lang.Math.pow;
import static java.util.Arrays.asList;
import static java.util.Arrays.copyOf;

import com.google.common.collect.ImmutableList;

// given an array of points in a 2D plane,
// find K closest points to the origin

public final class KClosestPointsQS {

	public static ImmutableList<Point> select(Point[] a, int k) {
		// Time complexity: O(n)
		// Worst case: O(n^2)

		int n = a.length;
		if (k <= 0) {
			return newList(new Point(0, 0));
		}
		int pivot = select(a, 0, n - 1, k);
		return newList(asList(copyOf(a, pivot + 1)));
	}

	private static int select(Point[] a, int left, int right, int k) {
		int index = randomIndex(left, right);
		int pivot = partition(a, left, right, index);
		int dist = pivot - left + 1;
		if (dist == k) {
			return pivot;
		} else if (k < dist) {
			return select(a, left, pivot, k);
		} else {
			return select(a, pivot + 1, right, k - dist);
		}
	}

	// smaller elements to the left
	public static int partition(Point[] points, int left, int right, int index) {
		int i = left;
		swap(points, right, index);
		Point pivot = points[right];
		for (int j = left; j < right; j++) {
			if (points[j].compareTo(pivot) < 1) {
				swap(points, i, j);
				i++;
			}
		}
		// move pivot to its final place
		swap(points, i, right);
		return i;
	}

	public static void main(String[] args) {
		Point[] points = points();
		System.out.println(select(points, 2));
	}

	private static Point[] points() {
		Point[] points = new Point[3];
		points[0] = new Point(3, 3);
		points[1] = new Point(5, -1);
		points[2] = new Point(-2, 4);
		return points;
	}

	private static class Point implements Comparable<Point> {

		private int x;
		private int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		private double dist(Point p1, Point p2) {
			int dx = p1.x - p2.x;
			int dy = p1.y - p2.y;
			return pow(dx, 2) + pow(dy, 2);
		}

		@Override
		public int compareTo(Point other) {
			double d1 = dist(this, new Point(0, 0));
			double d2 = dist(other, new Point(0, 0));
			if (d1 < d2) {
				return -1;
			}
			if (d1 == d2) {
				return 0;
			}
			return 1;
		}

		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + "]";
		}
	}
}