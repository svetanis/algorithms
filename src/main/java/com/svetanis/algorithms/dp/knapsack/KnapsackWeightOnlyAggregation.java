package com.svetanis.algorithms.dp.knapsack;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// given a list of weights on n items
// find all sums that can be formed
// using their weights

public final class KnapsackWeightOnlyAggregation {
	// Time Complexity: O(2^n)

	public static List<Integer> knapsack(List<Integer> weights) {
		Set<Integer> set = new HashSet<>();
		dfs(0, 0, weights, set);
		return new ArrayList<>(set);
	}

	private static int dfs(int index, int sum, List<Integer> weights, 
			Set<Integer> set) {
		if (index == weights.size()) {
			set.add(sum);
			return 0;
		}

		// include
		sum += weights.get(index);
		dfs(index + 1, sum, weights, set);
		// backtrack
		sum -= weights.get(index);
		// exclude
		return dfs(index + 1, sum, weights, set);
	}

	public static void main(String[] args) {
		// 0, 1, 3, 4, 5, 6, 7, 8, 9, 11, 12
		System.out.println(knapsack(asList(1, 3, 3, 5)));
	}
}
