package com.svetanis.algorithms.dp.knapsack;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// given a list of weights on n items
// find all sums that can be formed
// using their weights

public final class KnapsackWeightOnlyBottomUpSpaceOptimized {
	// Time Complexity: O(n * total sum)
	// Space Complexity: O(total sum)

	public static List<Integer> knapsack(List<Integer> weights) {
		Set<Integer> set = new HashSet<>();
		int n = weights.size();
		int total = weights.stream().mapToInt(Integer::intValue).sum();
		boolean[][] dp = new boolean[2][total + 1];
		dp[0][0] = true;
		for (int i = 1; i <= n; i++) {
			for (int w = 0; w <= total; w++) {
				// vertical
				dp[1][w] = dp[1][w] || dp[0][w];
				int target = w - weights.get(i - 1);
				if (target >= 0) {
					dp[1][w] = dp[1][w] || dp[0][target];
				}
			}
			// update prev row to current
			for(int w = 0; w <= total; w++) {
				dp[0][w] = dp[1][w];
			}
		}
		for (int w = 0; w <= total; w++) {
			if (dp[0][w]) {
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
