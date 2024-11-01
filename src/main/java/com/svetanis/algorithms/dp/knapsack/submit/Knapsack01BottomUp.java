package com.svetanis.algorithms.dp.knapsack.submit;

import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.List;

// Given two integer arrays to represent weights and profits of ‘N’ items, 
// we need to find a subset of these items which will give us maximum profit 
// such that their cumulative weight is not more than a given number ‘C’. 
// Each item can only be selected once, which means either 
// we put an item in the knapsack or we skip it.

public final class Knapsack01BottomUp {
	// Time Complexity: O(n * w)
	// Space Complexity: O(n * w)

	public static int knapsack(List<Integer> weights, List<Integer> values, int max) {
		int n = weights.size();
		int m = values.size();
		if (max <= 0 || n == 0 || n != m) {
			return 0;
		}
		int[][] dp = new int[n + 1][max + 1];
		for (int i = 0; i <= n; i++) {
			for (int w = 0; w <= max; w++) {
				if (i == 0 || w == 0) {
					dp[i][w] = 0;
				} else if (w < weights.get(i - 1)) {
					dp[i][w] = dp[i - 1][w];
				} else {
					int diff = w - weights.get(i - 1);
					int incl = values.get(i - 1) + dp[i - 1][diff];
					dp[i][w] = max(dp[i - 1][w], incl);
				}
			}
		}
		return dp[n][max];
	}

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
	}

	private static void test1() {
		List<Integer> weights = asList(1, 2, 3, 8, 7, 4);
		List<Integer> values = asList(20, 5, 10, 40, 15, 25);
		System.out.println(knapsack(weights, values, 10)); // 60
	}

	private static void test2() {
		List<Integer> weights = asList(3, 4, 7);
		List<Integer> values = asList(4, 5, 8);
		System.out.println(knapsack(weights, values, 7)); // 9
	}

	private static void test3() {
		List<Integer> weights = asList(3, 4, 7);
		List<Integer> values = asList(4, 5, 8);
		System.out.println(knapsack(weights, values, 8)); // 9
	}

	private static void test4() {
		List<Integer> weights = asList(1, 2, 3);
		List<Integer> values = asList(6, 10, 12);
		System.out.println(knapsack(weights, values, 5)); // 22
	}
}
