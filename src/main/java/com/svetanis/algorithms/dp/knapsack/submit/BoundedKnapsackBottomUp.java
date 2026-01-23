package com.svetanis.algorithms.dp.knapsack.submit;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

// Given n items where the i-th item has weight weights[i], 
// value values[i], and can be selected at most quantities[i] times, 
// find the maximum total value that fits in a knapsack of capacity capacity.

public final class BoundedKnapsackBottomUp {
	// Time Complexity: O(n * capacity * log(maxQuantity))
	// Space Complexity: O(capacity + n * log(maxQuantity))

	public static int boundedKnapsack(List<Integer> weights, List<Integer> values, List<Integer> quantities,
			int capacity) {
		List<Integer> expWeights = new ArrayList<>();
		List<Integer> expValues = new ArrayList<>();
		int n = values.size();
		for (int i = 0; i < n; i++) {
			int q = 1;
			int w = weights.get(i);
			int v = values.get(i);
			int quantity = quantities.get(i);
			while (q <= quantity) {
				expWeights.add(q * w);
				expValues.add(q * v);
				quantity -= q;
				q *= 2;
			}
			if (quantity > 0) {
				expWeights.add(quantity * w);
				expValues.add(quantity * v);
			}
		}
		return knapsack(expWeights, expValues, capacity);
	}

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
					dp[i][w] = Math.max(dp[i - 1][w], incl);
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
		test5();
	}

	private static void test1() {
		List<Integer> weights = asList(2, 3, 4);
		List<Integer> values = asList(3, 4, 5);
		List<Integer> quantities = asList(3, 2, 1);
		System.out.println(boundedKnapsack(weights, values, quantities, 10)); // 14
	}

	private static void test2() {
		List<Integer> weights = asList(5, 10, 3);
		List<Integer> values = asList(10, 20, 5);
		List<Integer> quantities = asList(1, 1, 1);
		System.out.println(boundedKnapsack(weights, values, quantities, 15)); // 30
	}

	private static void test3() {
		List<Integer> weights = asList(1, 2, 3);
		List<Integer> values = asList(1, 5, 10);
		List<Integer> quantities = asList(10, 5, 3);
		System.out.println(boundedKnapsack(weights, values, quantities, 20)); // 56
	}

	private static void test4() {
		List<Integer> weights = asList(3);
		List<Integer> values = asList(7);
		List<Integer> quantities = asList(5);
		System.out.println(boundedKnapsack(weights, values, quantities, 12)); // 28
	}

	private static void test5() {
		List<Integer> weights = asList(10);
		List<Integer> values = asList(100);
		List<Integer> quantities = asList(1);
		System.out.println(boundedKnapsack(weights, values, quantities, 5)); // 0
	}
}
