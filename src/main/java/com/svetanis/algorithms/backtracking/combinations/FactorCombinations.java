package com.svetanis.algorithms.backtracking.combinations;

import java.util.ArrayList;
import java.util.List;

// 254. Factor Combinations
//
// Input: n. Returns every way to write n as a product of two or more factors, each from 2 to n - 1,
// in any order: 12 gives [2, 6], [2, 2, 3], [3, 4]; 1 and a prime give []. On LeetCode: 1 <= n <=
// 10^7.
//
// The one idea: one call holds the factors chosen so far (chosen) and the part of n still to split
// (rest). Writing rest itself as the last factor finishes one answer, so every call below the first
// records one. Then the call tries each factor f of rest, from the last factor chosen upward, while
// f * f <= rest: factors only grow along an answer, so [2, 6] is made and [6, 2] never is, and the
// part left, rest / f, is never smaller than f. The first call records nothing, because n alone is
// not a product.
//
// Time: O(sqrt(n) * A) -- A answers, one call each; each call's loop runs at most sqrt(n) turns;
// copying an answer adds its length, at most log2(n). Counted: n = 9,999,991 (a prime) takes 3,161
// turns; n = 8,648,640 takes 88,579 calls for 88,578 answers and 630,008 turns.
// Space: O(log n) besides the output -- an answer has at most log2(n) factors, so the recursion is
// never deeper than that.
//
// Siblings: none for 254.

public final class FactorCombinations {
	public static List<List<Integer>> factors(int n) {
		List<List<Integer>> combinations = new ArrayList<>();
		dfs(n, 2, new ArrayList<>(), combinations);
		return combinations;
	}

	private static void dfs(int rest, int minFactor, //
			List<Integer> chosen, List<List<Integer>> combinations) {
		if (chosen.size() > 0) { // RECORD: n alone is no answer; one factor split off first
			List<Integer> combination = new ArrayList<>(chosen);
			combination.add(rest); // RECORD: rest closes the answer as the last, largest factor
			combinations.add(combination);
		}
		// WALK f * f <= rest: the part left, rest / f, is at least f, so factors keep growing
		for (int factor = minFactor; factor * factor <= rest; factor++) {
			if (rest % factor == 0) {
				chosen.add(factor); // CHOOSE
				dfs(rest / factor, factor, chosen, combinations); // RECURSE: next factor >= this one
				chosen.remove(chosen.size() - 1); // UNDO
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(factors(1)); // []
		System.out.println(factors(12)); // [[2, 6], [2, 2, 3], [3, 4]]
		System.out.println(factors(37)); // []
	}
}
