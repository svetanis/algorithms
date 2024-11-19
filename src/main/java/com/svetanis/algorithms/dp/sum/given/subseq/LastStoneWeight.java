package com.svetanis.algorithms.dp.sum.given.subseq;

import java.util.stream.IntStream;

// 1049. Last Stone Weight II

public final class LastStoneWeight {
	// Time Complexity: O(target * n)
	// Space Complexity: O(n)

	public static int lastStoneWeight(int[] stones) {
		int sum = IntStream.of(stones).sum();
		int target = sum >> 1; // sum/2;
		int[] dp = new int[target + 1];
		for (int weight : stones) {
			for (int capacity = target; capacity >= weight; capacity--) {
				int incl = dp[capacity - weight] + weight;
				dp[capacity] = Math.max(dp[capacity], incl);
			}
		}
		return sum - dp[target] * 2;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 7, 4, 1, 8, 1 };
		System.out.println(lastStoneWeight(a1)); // 1
		int[] a2 = { 31, 26, 33, 21, 40 };
		System.out.println(lastStoneWeight(a2)); // 5
	}
}
