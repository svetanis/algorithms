package com.svetanis.algorithms.search.quickselect.points;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Math.pow;
import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// given an array of points in a 2D plane,
// find K closest points to the origin

public final class KClosestPointsToSubmit {
	// Time complexity: O(n * log k)
	// Space complexity: O(k)

	public static List<List<Integer>> kClosest(List<List<Integer>> points, int k) {
		Queue<List<Integer>> pq = priorityQueue(points, k);
		// go through the remaining points of the input array,
		// if a point is closer to the origin than the top point
		// of the max heap, remove the top point from heap and add
		// the point from the input
		for (int i = k; i < points.size(); i++) {
			List<Integer> p = points.get(i);
			List<Integer> top = pq.peek();
			if (dto(p) < dto(top)) {
				pq.poll();
				pq.add(p);
			}
		}
		return kClosest(pq);
	}

	private static Queue<List<Integer>> priorityQueue(List<List<Integer>> points, int k) {
		Queue<List<Integer>> pq = new PriorityQueue<>((p1, p2) -> dto(p2) - dto(p1));
		// put first k points in the max heap
		for (int i = 0; i < k; i++) {
			pq.add(points.get(i));
		}
		return pq;
	}

	private static List<List<Integer>> kClosest(Queue<List<Integer>> pq) {
		List<List<Integer>> list = new ArrayList<>();
		while (!pq.isEmpty()) {
			list.add(0, pq.poll());
		}
		return list;
	}

	private static int dto(List<Integer> p) {
		return dist(p, asList(0, 0));
	}

	private static int dist(List<Integer> p1, List<Integer> p2) {
		double dx = p1.get(0) - p2.get(0);
		double dy = p1.get(1) - p2.get(1);
		return new Double(pow(dx, 2) + pow(dy, 2)).intValue();
	}

	public static void main(String[] args) {
		List<List<Integer>> points1 = points1();
		print(kClosest(points1, 2)); // [x=0, y=1], [x=1, y=0]

		List<List<Integer>> points2 = points2();
		print(kClosest(points2, 2)); // [x=2, y=-1], [x=1, y=3]

		List<List<Integer>> points3 = points3();
		print(kClosest(points3, 1)); // [x=1, y=1]

		List<List<Integer>> points4 = points4();
		print(kClosest(points4, 1)); // [x=-2, y=2]

		List<List<Integer>> points5 = points5();
		print(kClosest(points5, 2)); // [x=2, y=4], [4, 4]
	}

	private static List<List<Integer>> points1() {
		List<List<Integer>> list = newArrayList();
		list.add(asList(1, 0));
		list.add(asList(2, 1));
		list.add(asList(0, 1));
		return list;
	}

	private static List<List<Integer>> points2() {
		List<List<Integer>> list = newArrayList();
		list.add(asList(1, 3));
		list.add(asList(3, 4));
		list.add(asList(2, -1));
		return list;
	}

	private static List<List<Integer>> points3() {
		List<List<Integer>> list = newArrayList();
		list.add(asList(1, 1));
		list.add(asList(2, 2));
		list.add(asList(3, 3));
		return list;
	}

	private static List<List<Integer>> points4() {
		List<List<Integer>> list = newArrayList();
		list.add(asList(1, 3));
		list.add(asList(-2, 2));
		return list;
	}

	private static List<List<Integer>> points5() {
		List<List<Integer>> list = newArrayList();
		list.add(asList(4, 4));
		list.add(asList(2, 4));
		list.add(asList(8, 1));
		list.add(asList(3, -5));
		return list;
	}
}
