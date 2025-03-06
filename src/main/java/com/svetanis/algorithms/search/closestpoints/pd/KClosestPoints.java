package com.svetanis.algorithms.search.closestpoints.pd;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.closestpoints.pd.Distances.distances;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;
import static com.svetanis.java.base.utils.Random.randomIndex;
import static com.svetanis.java.base.utils.Swap.swap;

import java.awt.Point;
import java.util.List;

import com.google.common.collect.ImmutableList;

// given an array of points in a 2D plane,
// find K closest points to the origin

public final class KClosestPoints {
	// Time complexity: O(n)
	// Worst case: O(n^2)

	public static ImmutableList<Point> kClosest(List<Point> points, int k) {
		List<Distance> distances = distances(points);
		int pivot = select(distances, 0, distances.size() - 1, k);
		List<Point> list = newArrayList();
		for (int i = 0; i <= pivot; i++) {
			list.add(points.get(distances.get(i).index));
		}
		return newList(list);
	}

	private static int select(List<Distance> distances, int left, int right, int k) {
		int index = randomIndex(left, right);
		int pivot = partition(distances, left, right, index);
		int dist = pivot - left + 1;
		if (dist == k) {
			return pivot;
		} else if (k < dist) {
			return select(distances, left, pivot, k);
		} else {
			return select(distances, pivot + 1, right, k - dist);
		}
	}

	// smaller elements to the left
	private static int partition(List<Distance> distances, int left, int right, int index) {
		int i = left;
		swap(distances, right, index);
		Distance pivot = distances.get(right);
		for (int j = left; j < right; ++j) {
			if (distances.get(j).compareTo(pivot) < 1) {
				swap(distances, i, j);
				i++;
			}
		}
		// move pivot to its final place
		swap(distances, i, right);
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

	private static ImmutableList<Point> points1() {
		List<Point> list = newArrayList();
		list.add(new Point(1, 0));
		list.add(new Point(2, 1));
		list.add(new Point(0, 1));
		return newList(list);
	}

	private static ImmutableList<Point> points2() {
		List<Point> list = newArrayList();
		list.add(new Point(1, 3));
		list.add(new Point(3, 4));
		list.add(new Point(2, -1));
		return newList(list);
	}

	private static ImmutableList<Point> points3() {
		List<Point> list = newArrayList();
		list.add(new Point(1, 1));
		list.add(new Point(2, 2));
		list.add(new Point(3, 3));
		return newList(list);
	}

	private static ImmutableList<Point> points4() {
		List<Point> list = newArrayList();
		list.add(new Point(1, 3));
		list.add(new Point(-2, 2));
		return newList(list);
	}

	private static ImmutableList<Point> points5() {
		List<Point> list = newArrayList();
		list.add(new Point(4, 4));
		list.add(new Point(2, 4));
		list.add(new Point(8, 1));
		list.add(new Point(3, -5));
		return newList(list);
	}
}
