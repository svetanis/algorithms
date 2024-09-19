package com.svetanis.algorithms.dp.knapsack;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// given a list of weights on n items
// find all sums that can be formed
// using their weights

public final class KnapsackWeightOnlyMemoization {
	// Time Complexity: O(n * total sum)
	// Space Complexity: O(n * total sum)

	public static List<Integer> knapsack(List<Integer> weights) {
		Set<Integer> set = new HashSet<>();
		int total = weights.stream().mapToInt(Integer::intValue).sum();
		boolean[][] memo = new boolean[weights.size() + 1][total + 1];
		dfs(0, 0, memo, weights, set);
		return new ArrayList<>(set);
	}

	private static void dfs(int index, int sum, boolean[][] memo, 
			List<Integer> weights, Set<Integer> set) {
		if (memo[index][sum]) {
			return;
		}
		if (index == weights.size()) {
			set.add(sum);
			return;
		}
		// exclude
		dfs(index + 1, sum, memo, weights, set);
		// include
		dfs(index + 1, sum + weights.get(index), memo, weights, set);
		memo[index][sum] = true;
	}

	public static void main(String[] args) {
		// 0, 1, 3, 4, 5, 6, 7, 8, 9, 11, 12
		System.out.println(knapsack(asList(1, 3, 3, 5)));
	}
}
