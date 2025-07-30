package com.svetanis.algorithms.dp.math.perfectsquares;

// 279. Perfect Squares

public final class PerfectSquaresRecursive {
	// Time Complexity: O(n ^ sqrt(n))
	// Space Complexity: O(n)

	public static int sum(int n) {
		return dfs(n);
	}

	private static int dfs(int n) {
		if (n == 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 1; i * i <= n; i++) {
			min = Math.min(min, 1 + dfs(n - i * i));
		}
		return min;
	}

	public static void main(String[] args) {
		System.out.println(sum(6)); // 3 --> 4 + 1 + 1
		System.out.println(sum(12)); // 3 --> 4 + 4 + 4
		System.out.println(sum(13)); // 2 --> 4 + 9
	}
}