package com.svetanis.algorithms.math.geometry;

import static java.util.Comparator.comparingInt;

import java.util.Arrays;

// CSES: Minimum Euclidean Distance

// The answer is a SQUARED distance, so it is quadratic in the coordinates:
// CSES allows 10^9, which makes the largest answer 8 * 10^18 -- past int,
// inside long.

public final class MinEuclideanDistance {
	// Time Complexity: O(n^2) -- every pair, from the two nested loops. The sort
	// costs O(n log n) and then buys nothing, because the double loop below reaches
	// every pair whatever order they sit in. It is a leftover of the algorithm this
	// file does NOT implement: the intended solution is the divide-and-conquer
	// closest pair, which is O(n log n) and is where sorting by x earns its keep.
	// CSES allows n up to 2 * 10^5, so n^2 is 4 * 10^10 pairs -- this passes the
	// two samples below and would not pass the judge

	public static long minSquaredDistance(int[][] points) {
		int n = points.length;
		long min = Long.MAX_VALUE;
		Arrays.sort(points, comparingInt(p -> p[0])); // sorts the caller's array in place
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int[] p1 = points[i];
				int[] p2 = points[j];
				long sqrDist = sqrDist(p1, p2);
				min = Math.min(min, sqrDist);
			}
		}
		return min;
	}

	private static long sqrDist(int[] p1, int[] p2) {
		long dx = (long) p2[0] - p1[0];
		long dy = (long) p2[1] - p1[1];
		return dx * dx + dy * dy;
	}

	public static void main(String[] args) {
		int[][] points1 = { { 2, 1 }, { 4, 4 }, { 1, 2 }, { 6, 3 } };
		System.out.println(minSquaredDistance(points1)); // 2

		int[][] points2 = { { 2, 12 }, { 1, 4 }, { 3, 2 }, { 1, 3 } };
		System.out.println(minSquaredDistance(points2)); // 1

		// one far-away point: the squared distances need long
		int max = 1000000000;
		int[][] points3 = { { 0, 0 }, { max, max }, { 1, 0 }, { 2, 0 } };
		System.out.println(minSquaredDistance(points3)); // 1
	}
}