package com.svetanis.algorithms.dp.knapsack;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// given a list of weights on n items
// find all sums that can be formed
// using their weights

public final class KnapsackWeightOnlyBottomUp {
	// Time Complexity: O(n * total sum)
	// Space Complexity: O(n * total sum)

	public static List<Integer> knapsack(List<Integer> weights) {
		Set<Integer> set = new HashSet<>();
		int n = weights.size();
		int total = weights.stream().mapToInt(Integer::intValue).sum();
		boolean[][] dp = new boolean[n + 1][total + 1];
		dp[0][0] = true;
		for (int i = 1; i <= n; i++) {
			for (int w = 0; w <= total; w++) {
				// vertical
				dp[i][w] = dp[i][w] || dp[i - 1][w];
				int target = w - weights.get(i - 1);
				if (target >= 0) {
					dp[i][w] = dp[i][w] || dp[i - 1][target];
				}
			}
		}
		for (int w = 0; w <= total; w++) {
			if (dp[n][w]) {
				set.add(w);
			}
		}
		return new ArrayList<>(set);
	}

	public static void main(String[] args) {
		// 0, 1, 3, 4, 5, 6, 7, 8, 9, 11, 12
		System.out.println(knapsack(asList(1, 3, 3, 5)));
	}
}
