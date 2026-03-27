package com.svetanis.algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.List;

// 254. Factor Combinations

public final class FactorCombinations {
	// Time Complexity: O(n^log n)
	// Space Complexity: O(log n)

	public static List<List<Integer>> factors(int n) {
		List<List<Integer>> factors = new ArrayList<>();
		dfs(n, 2, new ArrayList<>(), factors);
		return factors;
	}

	private static void dfs(int num, int startFactor, 
			List<Integer> list, List<List<Integer>> factors) {
		if (list.size() > 0) {
			List<Integer> combinations = new ArrayList<>(list);
			combinations.add(num);
			factors.add(combinations);
		}
		for (int factor = startFactor; factor * factor <= num; factor++) {
			if (num % factor == 0) {
				list.add(factor);
				dfs(num / factor, factor, list, factors);
				list.remove(list.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(factors(1)); // []
		System.out.println(factors(12)); // [2,6],[3,4],[2,2,3]
		System.out.println(factors(37)); // []
	}
}
