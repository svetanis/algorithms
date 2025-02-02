package com.svetanis.algorithms.search.quickselect.points;

import java.util.Arrays;

import com.svetanis.java.base.utils.Print;

// 973. K Closest Points to Origin

public final class KClosestPoints973 {
	// Time complexity: O(n * log n)
	// Space complexity: O(n)

	public static int[][] kClosest(int[][] points, int k) {
		Arrays.sort(points, (point1, point2) -> {
			// Calculate the squared distance for the first point from the origin
			int distance1 = point1[0] * point1[0] + point1[1] * point1[1];
			// Calculate the squared distance for the second point from the origin
			int distance2 = point2[0] * point2[0] + point2[1] * point2[1];
			// Compare the two distances
			return distance1 - distance2;
		});

		// Return the first k elements of the sorted array, which are the k closest to the origin
		return Arrays.copyOfRange(points, 0, k);
	}

	public static void main(String[] args) {
		int[][] grid1 = { { 1, 3 }, { -2, 2 } };
		Print.print(kClosest(grid1, 1)); // [-2,2]

		int[][] grid2 = { { 3, 3 }, { 5, -1 }, { -2, 4 } };
		Print.print(kClosest(grid2, 2)); // [3,3], [-2,4]
	}

}
