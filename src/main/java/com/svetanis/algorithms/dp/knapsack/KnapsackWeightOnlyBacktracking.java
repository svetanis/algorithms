package com.svetanis.algorithms.dp.knapsack;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// given a list of weights on n items
// find all sums that can be formed
// using their weights

public final class KnapsackWeightOnlyBacktracking {
	// Time Complexity: O(2^n)

	public static List<Integer> knapsack(List<Integer> weights) {
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = new HashSet<>();
		dfs(0, list, weights, set);
		return new ArrayList<>(set);
	}

	private static void dfs(int index, List<Integer> path, 
			List<Integer> weights, Set<Integer> set) {
		if (index == weights.size()) {
			set.add(path.stream().mapToInt(Integer::intValue).sum());
			return;
		}
		// exclude
		dfs(index + 1, path, weights, set);
		// include
		path.add(weights.get(index));
		dfs(index + 1, path, weights, set);
		// backtrack
		path.remove(path.size() - 1);
	}

	public static void main(String[] args) {
		// 0, 1, 3, 4, 5, 6, 7, 8, 9, 11, 12
		System.out.println(knapsack(asList(1, 3, 3, 5)));
	}
}
