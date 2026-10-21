package com.svetanis.algorithms.slidingwindow.queue;

import static java.util.Comparator.comparingInt;

import java.util.PriorityQueue;

// 1499. Max Value of Equation
//
// points[i] = {x, y}, sorted by x with no two x equal, and a limit k. Returns the largest
// yi + yj + |xi - xj| over pairs i < j with |xi - xj| <= k. The problem promises at least one
// such pair; when there is none the method returns Integer.MIN_VALUE.
//
// Because x only grows, the value splits into (yi - xi) + (yj + xj), and the best partner for
// point j is the earlier point with the largest yi - xi still within k of xj. A max-heap keeps
// the largest yi - xi on top. A heap cannot take out an arbitrary point, so a point that slid
// out of reach stays inside until it reaches the top; only the top is ever read, and the top
// is checked first, so a stale point lower down does no harm. Once out of reach, a point stays
// out, because x only grows.
//
// Siblings, same problem:
//   MaxValueOfEquation  a monotonic deque instead of the heap; O(n), the version to prefer

public final class MaxValueOfEquationPQ {
	// Time Complexity: O(n log n), each point is pushed once and popped at most once,
	// and each heap operation costs log of the heap's size
	// Space Complexity: O(n), stale points stay in the heap until they reach the top

	public static int maxValue(int[][] points, int k) {
		int max = Integer.MIN_VALUE;
		// {yi - xi, xi}, the largest yi - xi on top
		PriorityQueue<int[]> pq = new PriorityQueue<>(comparingInt((int[] a) -> a[0]).reversed());
		for (int[] point : points) {
			int x = point[0];
			int y = point[1];
			while (!pq.isEmpty() && x - pq.peek()[1] > k) {
				pq.poll(); // POP: the top is out of reach, and for good
			}
			if (!pq.isEmpty()) {
				max = Math.max(max, x + y + pq.peek()[0]); // RECORD: PEEK the best partner, before the newcomer joins
			}
			pq.add(new int[] { y - x, x }); // PUSH
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] points1 = { { 1, 3 }, { 2, 0 }, { 5, 10 }, { 6, -10 } };
		System.out.println(maxValue(points1, 1)); // 4
		int[][] points2 = { { 0, 0 }, { 3, 0 }, { 9, 2 } };
		System.out.println(maxValue(points2, 3)); // 3
	}
}
