package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 853. Car Fleet

public final class CarFleet {
	// Time Complexity: O(n log n)
	// Space Complexity: O(n)

	public static int carFleet(int target, int[] positions, int[] speed) {
		Integer[] indices = indices(positions);
		int count = 0;
		double prev = 0;
		for (int index : indices) {
			int start = positions[index];
			double time = 1.0 * (target - start) / speed[index];
			if (time > prev) {
				count++;
				prev = time;
			}
		}
		return count;
	}

	private static Integer[] indices(int[] positions) {
		int n = positions.length;
		Integer[] indices = new Integer[n];
		for (int i = 0; i < n; i++) {
			indices[i] = i;
		}
		Arrays.sort(indices, (a, b) -> positions[b] - positions[a]);
		return indices;
	}

	public static void main(String[] args) {
		int[] p1 = { 10, 8, 0, 5, 3 };
		int[] s1 = { 2, 4, 1, 1, 3 };
		System.out.println(carFleet(12, p1, s1)); // 3
		int[] p2 = { 3 };
		int[] s2 = { 3 };
		System.out.println(carFleet(10, p2, s2)); // 1
		int[] p3 = { 0, 2, 4 };
		int[] s3 = { 4, 2, 1 };
		System.out.println(carFleet(100, p3, s3)); // 1
	}
}
