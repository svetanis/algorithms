package com.svetanis.algorithms.matrix;

// 1975. Maximum Matrix Sum

public final class MaxMatrixSum {
	// Time Complexity: O(n * m)
	// Space Complexity: O(1)

	public static long maxMatrixSum(int[][] g) {
		int count = 0;
		int min = Integer.MAX_VALUE;
		long sum = 0;
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				int curr = g[i][j];
				if (curr < 0) {
					count += 1;
					curr = -curr;
				}
				sum += curr;
				if (curr < min) {
					min = curr;
				}
			}
		}
		return count % 2 == 0 ? sum : sum - 2 * min;
	}

	public static void main(String[] agrs) {
		int[][] m1 = { { 1, -1 }, { -1, 1 } };
		System.out.println(maxMatrixSum(m1)); // 4

		int[][] m2 = { { 1, 2, 3 }, { -1, -2, -3 }, { 1, 2, 3 } };
		System.out.println(maxMatrixSum(m2)); // 16
	}
}
