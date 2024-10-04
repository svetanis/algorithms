package com.svetanis.algorithms.search.quickselect.points;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;
import static com.svetanis.java.base.utils.Random.randomIndex;
import static com.svetanis.java.base.utils.Swap.swap;

import java.util.List;

import com.google.common.collect.ImmutableList;

// given an array of points in a 2D plane,
// find K closest points to the origin

public final class KClosestPointsQS {
	// Time complexity: O(n)
	// Worst case: O(n^2)

	public static ImmutableList<Point> kClosest(List<Point> points, int k) {
		int n = points.size();
		if (k <= 0) {
			return newList(new Point(0, 0));
		}
		int pivot = select(points, 0, n - 1, k);
		return newList(points.subList(0, pivot + 1));
	}

	private static int select(List<Point> points, int left, int right, int k) {
		int index = randomIndex(left, right);
		int pivot = partition(points, left, right, index);
		int dist = pivot - left + 1;
		if (dist == k) {
			return pivot;
		} else if (k < dist) {
			return select(points, left, pivot, k);
		} else {
			return select(points, pivot + 1, right, k - dist);
		}
	}

	// smaller elements to the left
	public static int partition(List<Point> points, int left, int right, int index) {
		int i = left;
		swap(points, right, index);
		Point pivot = points.get(right);
		for (int j = left; j < right; j++) {
			if (points.get(j).compareTo(pivot) < 1) {
				swap(points, i, j);
				i++;
			}
		}
		// move pivot to its final place
		swap(points, i, right);
		return i;
	}

	public static void main(String[] args) {
		List<Point> points1 = points1();
		print(kClosest(points1, 2)); // [x=0, y=1], [x=1, y=0]

		List<Point> points2 = points2();
		print(kClosest(points2, 2)); // [x=2, y=-1], [x=1, y=3]

		List<Point> points3 = points3();
		print(kClosest(points3, 1)); // [x=1, y=1]

		List<Point> points4 = points4();
		print(kClosest(points4, 1)); // [x=-2, y=2]

		List<Point> points5 = points5();
		print(kClosest(points5, 2)); // [x=2, y=4], [4, 4]
	}

	private static List<Point> points1() {
		List<Point> list = newArrayList();
		list.add(new Point(1, 0));
		list.add(new Point(2, 1));
		list.add(new Point(0, 1));
		return list;
	}

	private static List<Point> points2() {
		List<Point> list = newArrayList();
		list.add(new Point(1, 3));
		list.add(new Point(3, 4));
		list.add(new Point(2, -1));
		return list;
	}

	private static List<Point> points3() {
		List<Point> list = newArrayList();
		list.add(new Point(1, 1));
		list.add(new Point(2, 2));
		list.add(new Point(3, 3));
		return list;
	}

	private static List<Point> points4() {
		List<Point> list = newArrayList();
		list.add(new Point(1, 3));
		list.add(new Point(-2, 2));
		return list;
	}

	private static List<Point> points5() {
		List<Point> list = newArrayList();
		list.add(new Point(4, 4));
		list.add(new Point(2, 4));
		list.add(new Point(8, 1));
		list.add(new Point(3, -5));
		return list;
	}
}