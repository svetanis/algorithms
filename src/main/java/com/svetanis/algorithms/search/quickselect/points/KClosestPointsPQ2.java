package com.svetanis.algorithms.search.quickselect.points;

import static com.svetanis.java.base.utils.Print.print;

import java.util.PriorityQueue;
import java.util.Queue;

// 973. K Closest Points to Origin

public final class KClosestPointsPQ2 {
	// Time complexity: O(n * log k)
	// Space complexity: O(k)

	public static int[][] kClosest(int[][] points, int k) {
		Queue<int[]> pq = new PriorityQueue<>((p1, p2) -> dist(p2) - dist(p1));
		for (int[] point : points) {
			pq.add(point);
			if (pq.size() > k) {
				pq.poll();
			}
		}
		return kClosest(pq, k);
	}

	private static int dist(int[] p) {
		return p[0] * p[0] + p[1] * p[1];
	}

	private static int[][] kClosest(Queue<int[]> pq, int k) {
		int[][] a = new int[k][2];
		for (int i = 0; i < k; i++) {
			a[i] = pq.poll();
		}
		return a;
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
