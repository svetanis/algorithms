package com.svetanis.algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.List;

// 254. Factor Combinations
//
// Given an integer n, return every way to write it as a product of two or more factors, each
// factor from 2 to n - 1, in any order. 12 gives [2, 6], [2, 2, 3], [3, 4]; 1 and a prime give [].
//
// Start-index backtracking, where the "index" is the smallest factor still allowed. Each call
// holds the factors chosen so far and the part of n still to split, rest. Writing rest itself as
// the last factor finishes one answer, so every call below the first records one. Then it tries
// each factor f of rest, from the last factor chosen upward, while f * f <= rest: factors only
// grow along an answer, so [2, 6] is made and [6, 2] never is, and the part left, rest / f, is
// never smaller than f. The first call records nothing, because n alone is not a product.

public final class FactorCombinations {
	// Time Complexity: O(sqrt(n) * A), A answers, one call each, each call's loop at most
	// sqrt(n) turns; copying an answer adds its length, at most log2(n)
	// Space Complexity: O(log n) besides the output, an answer has at most log2(n) factors,
	// so the recursion is never deeper than that

	public static List<List<Integer>> factors(int n) {
		List<List<Integer>> combinations = new ArrayList<>();
		dfs(n, 2, new ArrayList<>(), combinations);
		return combinations;
	}

	private static void dfs(int rest, int minFactor, //
			List<Integer> chosen, List<List<Integer>> combinations) {
		if (chosen.size() > 0) { // n alone is not an answer: at least one factor split off first
			List<Integer> combination = new ArrayList<>(chosen);
			combination.add(rest); // rest as the last, largest factor
			combinations.add(combination);
		}
		// f * f <= rest: the part left, rest / f, is at least f, so factors keep growing
		for (int factor = minFactor; factor * factor <= rest; factor++) {
			if (rest % factor == 0) {
				chosen.add(factor); // choose
				dfs(rest / factor, factor, chosen, combinations); // the next factor is at least this one
				chosen.remove(chosen.size() - 1); // undo
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(factors(1)); // []
		System.out.println(factors(12)); // [[2, 6], [2, 2, 3], [3, 4]]
		System.out.println(factors(37)); // []
	}
}
