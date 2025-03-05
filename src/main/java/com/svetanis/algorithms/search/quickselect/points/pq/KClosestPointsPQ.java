package com.svetanis.algorithms.search.quickselect.points.pq;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.algorithms.search.quickselect.points.pq.Distances.distances;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;

import java.awt.Point;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// given an array of points in a 2D plane,
// find K closest points to the origin

public final class KClosestPointsPQ {

	public static ImmutableList<Point> kClosest(List<Point> points, int k) {
		// Time complexity: O(n * log k)
		// Space complexity: O(k)

		List<Distance> distances = distances(points);
		Queue<Distance> pq = priorityQueue(distances, k);
		// go through the remaining points of the input array,
		// if a point is closer to the origin than the top point
		// of the max heap, remove the top point from heap and add
		// the point from the input
		for (int i = k; i < distances.size(); i++) {
			Distance distance = distances.get(i);
			if (distance.dist < pq.peek().dist) {
				pq.poll();
				pq.add(distance);
			}
		}
		return kClosest(pq, points);
	}

	private static Queue<Distance> priorityQueue(List<Distance> distances, int k) {
		Queue<Distance> pq = new PriorityQueue<>((d1, d2) -> d2.dist - d1.dist);
		// put first k points in the max heap
		for (int i = 0; i < k; i++) {
			pq.add(distances.get(i));
		}
		return pq;
	}

	private static ImmutableList<Point> kClosest(Queue<Distance> pq, List<Point> points) {
		List<Point> list = newArrayList();
		while (!pq.isEmpty()) {
			int index = pq.poll().index;
			list.add(0, points.get(index));
		}
		return newList(list);
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
