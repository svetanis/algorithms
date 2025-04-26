package com.svetanis.algorithms.dp.grid;

// 1937. Maximum Number of Points with Cost

public final class MaxPointsWithCost {
	// Time Complexity: O(n * m)
	// Space Complexity: O(n * m)

	private static final long INF = 1L << 60;

	public static long maxPoints(int[][] points) {
		int n = points[0].length;
		long[] dp = new long[n];
		for (int[] row : points) {
			long leftMax = -INF;
			long[] next = new long[n];
			for (int j = 0; j < n; j++) {
				leftMax = Math.max(leftMax, dp[j] + j);
				next[j] = Math.max(next[j], row[j] + leftMax - j);
			}
			long rightMax = -INF;
			for (int j = n - 1; j >= 0; j--) {
				rightMax = Math.max(rightMax, dp[j] - j);
				next[j] = Math.max(next[j], row[j] + rightMax + j);
			}
			dp = next;
		}
		long max = 0;
		for (long point : dp) {
			max = Math.max(max, point);
		}
		return max;
	}

	public static void main(String[] args) {
		int[][] p1 = { { 1, 2, 3 }, { 1, 5, 1 }, { 3, 1, 1 } };
		System.out.println(maxPoints(p1)); // 9
		int[][] p2 = { { 1, 5 }, { 2, 3 }, { 4, 2 } };
		System.out.println(maxPoints(p2)); // 11
	}
}
