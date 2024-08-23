package com.svetanis.algorithms.search.quickselect.points;

import static com.google.common.collect.Lists.newArrayList;
import static com.svetanis.java.base.collect.Lists.newList;
import static com.svetanis.java.base.utils.Print.print;
import static java.lang.Math.pow;

import java.awt.Point;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.collect.ImmutableList;

// given an array of points in a 2D plane,
// find K closest points to the origin

public final class KClosestPointsPQ {

	public static ImmutableList<Point> select(List<Point> points, int k) {
		// Time complexity: O(n * log k)
		// Space complexity: O(k)

		Distance[] distances = distances(points);
		Queue<Distance> pq = priorityQueue(distances, k);
		// go through the remaining points of the input array,
		// if a point is closer to the origin than the top point
		// of the max heap, remove the top point from heap and
		// the point from the input
		for (int i = k; i < distances.length; i++) {
			if (distances[k].dist < pq.peek().dist) {
				pq.poll();
				pq.add(distances[i]);
			}
		}
		return kClosest(pq, points);
	}

	private static Queue<Distance> priorityQueue(Distance[] distances, int k) {
		Queue<Distance> pq = new PriorityQueue<>((d1, d2) -> d2.dist - d1.dist);
		// put first k points in the max heap
		for (int i = 0; i < k; i++) {
			pq.add(distances[i]);
		}
		return pq;
	}

	private static ImmutableList<Point> kClosest(Queue<Distance> pq, List<Point> points) {
		List<Point> list = newArrayList();
		while (!pq.isEmpty()) {
			int index = pq.poll().index;
			list.add(points.get(index));
		}
		return newList(list);
	}

	public static void main(String[] args) {
		List<Point> points1 = points1();
		print(select(points1, 2));
		
		List<Point> points2 = points2();
		print(select(points2, 2));		
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

	private static Distance[] distances(List<Point> points) {
		Distance[] a = new Distance[points.size()];
		for (int i = 0; i < points.size(); i++) {
			a[i] = new Distance(distToOrigin(points.get(i)), i);
		}
		return a;
	}

	private static int distToOrigin(Point p) {
		return dist(p, new Point(0, 0));
	}

	private static int dist(Point p1, Point p2) {
		double dx = p1.getX() - p2.getX();
		double dy = p1.getY() - p2.getY();
		return new Double(pow(dx, 2) + pow(dy, 2)).intValue();
	}

	private static class Distance implements Comparable<Distance> {

		private int dist;
		private int index;

		public Distance(int dist, int index) {
			this.dist = dist;
			this.index = index;
		}

		@Override
		public int compareTo(Distance other) {
			return this.dist - other.dist;
		}
	}

}
