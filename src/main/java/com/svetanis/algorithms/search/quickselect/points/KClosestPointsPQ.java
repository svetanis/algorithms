package com.svetanis.algorithms.search.quickselect.points;

import static com.svetanis.java.base.utils.Print.print;

import java.util.PriorityQueue;
import java.util.Queue;

// 973. K Closest Points to Origin

public final class KClosestPointsPQ {
	// Time complexity: O(n * log k)
	// Space complexity: O(k)

	public static int[][] kClosest(int[][] points, int k) {
		Queue<int[]> pq = priorityQueue(points, k);
		// go through the remaining points of the input array,
		// if a point is closer to the origin than the top point
		// of the max heap, remove the top point from heap and add
		// the point from the input
		for (int i = k; i < points.length; i++) {
			int[] p = points[i];
			int[] top = pq.peek();
			if (dto(p) < dto(top)) {
				pq.poll();
				pq.add(p);
			}
		}
		return kClosest(pq, k);
	}

	private static Queue<int[]> priorityQueue(int[][] points, int k) {
		Queue<int[]> pq = new PriorityQueue<>((p1, p2) -> dto(p2) - dto(p1));
		// put first k points in the max heap
		for (int i = 0; i < k; i++) {
			pq.add(points[i]);
		}
		return pq;
	}

	private static int[][] kClosest(Queue<int[]> pq, int k) {
		int[][] a = new int[k][2];
		for (int i = 0; i < k; i++) {
			a[i] = pq.poll();
		}
		return a;
	}

	private static int dto(int[] p) {
		return dist(p, new int[] { 0, 0 });
	}

	private static int dist(int[] p1, int[] p2) {
		int dx = p1[0] - p2[0];
		int dy = p1[1] - p2[1];
		return dx * dx + dy * dy;
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 0 }, { 2, 1 }, { 0, 1 } };
		print(kClosest(points1, 2)); // [x=0, y=1], [x=1, y=0]

		int[][] points2 = { { 1, 3 }, { 3, 4 }, { 2, -1 } };
		print(kClosest(points2, 2)); // [x=2, y=-1], [x=1, y=3]

		int[][] points3 = { { 1, 1 }, { 2, 2 }, { 3, 3 } };
		print(kClosest(points3, 1)); // [x=1, y=1]

		int[][] points4 = { { 1, 3 }, { -2, 2 } };
		print(kClosest(points4, 1)); // [x=-2, y=2]

		int[][] points5 = { { 4, 4 }, { 2, 4 }, { 8, 1 }, { 3, -5 } };
		print(kClosest(points5, 2)); // [x=2, y=4], [4, 4]
	}
}
