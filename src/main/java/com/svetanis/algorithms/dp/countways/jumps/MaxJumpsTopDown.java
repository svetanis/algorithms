package com.svetanis.algorithms.dp.countways.jumps;

// 2770. Maximum Number of Jumps to Reach the Last Index

public final class MaxJumpsTopDown {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n)

	private final static int INF = -Integer.MAX_VALUE;

	public static int count(int[] a, int target) {
		int n = a.length;
		Integer[] dp = new Integer[n];
		int max = count(a, target, 0, dp);
		return max < 0 ? -1 : max;
	}

	private static int count(int[] a, int target, int index, Integer[] dp) {
		if (index == a.length - 1) {
			return 0;
		}
		if (dp[index] != null) {
			return dp[index];
		}
		int max = INF;
		for (int i = index + 1; i < a.length; i++) {
			int jumps = Math.abs(a[index] - a[i]);
			if (jumps <= target) {
				max = Math.max(max, 1 + count(a, target, i, dp));
			}
		}
		return dp[index] = max;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 6, 4, 1, 2 };
		System.out.println(count(a1, 2)); // 3

		int[] a2 = { 1, 3, 6, 4, 1, 2 };
		System.out.println(count(a2, 3)); // 5

		int[] a3 = { 1, 3, 6, 4, 1, 2 };
		System.out.println(count(a3, 0)); // -1

		int[] a4 = { 0, 2, 1, 3 };
		System.out.println(count(a4, 1)); // -1
	}
}
