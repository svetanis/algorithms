package com.svetanis.algorithms.dp.knapsack;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// given a list of weights on n items
// find all sums that can be formed
// using their weights

public final class KnapsackWeightOnlyAggregationSet {
	// Time Complexity: O(n * s), s = number of distinct sums

	public static List<Integer> knapsack(List<Integer> weights) {
		return new ArrayList<>(sums(0, weights));
	}

	// every sum reachable using the weights from index onwards.
	// aggregation again, but the returned value is the whole
	// answer instead of one yes/no, so one call answers everything

	private static Set<Integer> sums(int index, List<Integer> weights) {
		if (index == weights.size()) {
			// no weights left: the empty subset, whose sum is 0
			Set<Integer> base = new HashSet<>();
			base.add(0);
			return base;
		}
		// solved once, then reused by both branches below
		Set<Integer> rest = sums(index + 1, weights);
		int weight = weights.get(index);
		Set<Integer> out = new HashSet<>(rest);
		for (int sum : rest) {
			out.add(sum + weight);
		}
		return out;
	}

	public static void main(String[] args) {
		// 0, 1, 3, 4, 5, 6, 7, 8, 9, 11, 12
		System.out.println(knapsack(asList(1, 3, 3, 5)));
	}
}
