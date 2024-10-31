package com.svetanis.algorithms.dp.knapsack.submit;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Math.max;
import static java.util.Arrays.asList;

import java.util.List;

// Given two integer arrays to represent weights and profits of ‘N’ items, 
// we need to find a subset of these items which will give us maximum profit 
// such that their cumulative weight is not more than a given number ‘C’. 
// Each item can only be selected once, which means either 
// we put an item in the knapsack or we skip it.

public final class Knapsack01TopDown {

	// Time Complexity: O(2^n)
	// Space Complexity: O(n)

	public static int knapsack(List<Integer> weights, List<Integer> values, int max) {
		int n = weights.size();
		int m = values.size();
		if (max <= 0 || n == 0 || n != m) {
			return 0;
		}
		Integer[][] dp = new Integer[n + 1][max + 1];
		return knapsack(weights, values, max, n - 1, dp);
	}

	private static int knapsack(List<Integer> w, List<Integer> v, int max, int n, Integer[][] dp) {
		// base case: negative capacity
		if (max < 0) {
			return MIN_VALUE;
		}
		// base case: no items left or capacity becomes 0
		if (n < 0 || max == 0) {
			return 0;
		}
		if (dp[n][max] != null) {
			return dp[n][max];
		}
		if (w.get(n) > max) {
			dp[n][max] = knapsack(w, v, max, n - 1, dp);
		} else {
			// 1. include current item n in knapsack and recur for
			// remaining n - 1 with decreased capacity max - w[n]
			int incl = v.get(n) + knapsack(w, v, max - w.get(n), n - 1, dp);
			// 2. exclude current item n from knapsack and
			// recur for remaining items n - 1
			int excl = knapsack(w, v, max, n - 1, dp);
			dp[n][max] = max(incl, excl);
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
