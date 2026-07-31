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
	// Time Complexity: O(total sum * 2^n)

	public static List<Integer> knapsack(List<Integer> weights) {
		Set<Integer> set = new HashSet<>();
		int total = weights.stream().mapToInt(Integer::intValue).sum();
		for (int sum = 0; sum <= total; sum++) {
			if (canMake(weights.size(), sum, weights)) {
				set.add(sum);
			}
		}
		return new ArrayList<>(set);
	}

	// can the first n weights add up to exactly sum?
	// the answer is returned, not collected - which is what
	// makes it memoizable: canMake(n, sum) is memo[n][sum]

	private static boolean canMake(int n, int sum, List<Integer> weights) {
		// sum == 0 must be tested before n == 0:
		// the empty set makes 0, so canMake(0, 0) is true
		if (sum == 0) {
			return true;
		}
		if (n == 0) {
			return false;
		}
		int weight = weights.get(n - 1);
		// exclude
		if (canMake(n - 1, sum, weights)) {
			return true;
		}
		// include
		return weight <= sum && canMake(n - 1, sum - weight, weights);
	}

	public static void main(String[] args) {
		// 0, 1, 3, 4, 5, 6, 7, 8, 9, 11, 12
		System.out.println(knapsack(asList(1, 3, 3, 5)));
	}
}
