package com.svetanis.algorithms.dp.sum.given.subseq;

import java.util.stream.IntStream;

// 1049. Last Stone Weight II

public final class LastStoneWeightRecursive {
	// Time Complexity: O(2^n)
	// Space Complexity: O(n)

	public static int lastStoneWeight(int[] stones) {
		int sum = IntStream.of(stones).sum();
		return dfs(stones, stones.length - 1, sum, 0);
	}

	private static int dfs(int[] stones, int index, int target, int sum) {
		if (index < 0) {
			return Math.abs(target - 2 * sum);
		}
		int incl = dfs(stones, index - 1, target, sum + stones[index]);
		int excl = dfs(stones, index - 1, target, sum);
		return Math.min(incl, excl);
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 7, 4, 1, 8, 1 };
		System.out.println(lastStoneWeight(a1)); // 1
		int[] a2 = { 31, 26, 33, 21, 40 };
		System.out.println(lastStoneWeight(a2)); // 5
	}
}
