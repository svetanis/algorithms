package com.svetanis.algorithms.dp.coins;

// 279. Perfect Squares

public final class PerfectSquaresRecursive {
	// Time Complexity: exponential in n -- measured at about 1.42^n, which is
	// x32.8 for every +10: 52 calls at n = 10, 1.8 million at n = 40, and
	// 2 billion at n = 60. NOT O(n * sqrt(n)) -- that is PerfectSquaresBottomUp's
	// cost, and it is what a cache buys. The gap between the two files IS the lesson.
	// Space Complexity: O(n) -- the deepest path is 1 + 1 + ... + 1, so n frames

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
