package com.svetanis.algorithms.slidingwindow.queue;

import java.util.ArrayDeque;
import java.util.Deque;

// 1499. Max Value of Equation
//
// points[i] = {x, y}, sorted by x with no two x equal, and a limit k. Returns the largest
// yi + yj + |xi - xj| over pairs i < j with |xi - xj| <= k. The problem promises at least one
// such pair; when there is none the method returns Integer.MIN_VALUE.
//
// Because x only grows, |xi - xj| = xj - xi, so the value splits into (yi - xi) + (yj + xj).
// For the point j being read, yj + xj is fixed, and the best partner is the earlier point with
// the largest yi - xi that is still within k of xj. That is a sliding-window maximum of yi - xi:
// a point leaves from the front once xj - xi > k, and an older point whose yi - xi is <= the
// newcomer's can never be the best partner again -- the newcomer is at least as good and stays
// in reach longer -- so it is thrown out from the back. The deque stays in decreasing yi - xi,
// and its front is the best partner.
//
// Siblings, same problem:
//   MaxValueOfEquationPQ  a max-heap of yi - xi instead of the deque; a point that slid out is
//                         dropped only when it reaches the top, so O(n log n)
// The same deque on a plain array of numbers: SlidingWindowMaxSubmit (LC 239).

public final class MaxValueOfEquation {
	// Time Complexity: O(n), each point joins the deque once and leaves it at most once
	// Space Complexity: O(n), the deque holds every point when yi - xi keeps falling

	public static int maxValue(int[][] points, int k) {
		int max = Integer.MIN_VALUE;
		Deque<int[]> dq = new ArrayDeque<>(); // {yi - xi, xi}, yi - xi decreasing from the front
		for (int[] point : points) {
			int x = point[0];
			int y = point[1];
			while (!dq.isEmpty() && x - dq.peekFirst()[1] > k) {
				dq.pollFirst(); // DROP: out of reach now, and x only grows, so for good
			}
			if (!dq.isEmpty()) {
				max = Math.max(max, x + y + dq.peekFirst()[0]); // RECORD: before the newcomer joins, i < j
			}
			while (!dq.isEmpty() && y - x >= dq.peekLast()[0]) {
				dq.pollLast(); // THROW: older and no better than the newcomer
			}
			dq.addLast(new int[] { y - x, x }); // JOIN
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
