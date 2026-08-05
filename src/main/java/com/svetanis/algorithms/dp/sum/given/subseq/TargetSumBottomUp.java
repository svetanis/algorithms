package com.svetanis.algorithms.dp.sum.given.subseq;

import java.util.Arrays;

// given a set of positive numbers and a target sum
// each number should be assigned either '+' or '-'
// find the total ways to assign symbols to make the
// sum of the numbers equal to the target

// two subsets of the given numbers
// whose difference is equal to K
// sum(s1) - sum(s2) = k
// sum(s1) + sum(s2) = sum(n)
// ---------------------------
// 2 * sum(s1) = k + sum(n)
// sum(s1) = (sum(n) + k)/2

// once reduced, this is SubSetSumCountBottomUp verbatim,
// so it carries the same axes: dp[item][capacity]

// this file solves for sum(s1), the POSITIVE subset. TargetSumTopDownReduced
// and TargetSumSpaceOptimized solve for the negative one, (total - k) / 2.
// picking one side determines the other, so the two counts are equal --
// the different halves are not a sign error in either file.

public final class TargetSumBottomUp {
	// Time complexity: O(n * sum)

	public static int count(int[] a, int k) {
		int total = Arrays.stream(a).sum();
		// if (k + total) is odd, can't find subset
		if (total < k || (k + total) % 2 == 1) {
			return 0;
		}
		int target = (k + total) / 2;
		return countSubsets(a, target);
	}

	private static int countSubsets(int[] a, int sum) {
		int n = a.length;
		// the value of dp[i][s] is the number of subsets
		// of set[0 ... i - 1] with sum equal to s
		int[][] dp = new int[n + 1][sum + 1];
		// only the empty set is seeded: it reaches sum 0 one way, and
		// nothing else. the rest of row 0 is already 0.
		// LC 494 allows nums[i] == 0, and a zero can be taken or left
		// without changing the sum, so it doubles the count --
		// seeding dp[i][0] = 1 for every i would throw that away
		dp[0][0] = 1;
		// fill the subset table in bottom up manner.
		// s starts at 0, not 1: column 0 is a real subproblem that a
		// zero-valued element can double, so it has to be recomputed too
		for (int i = 1; i <= n; ++i) {
			for (int s = 0; s <= sum; ++s) {
				dp[i][s] = dp[i - 1][s];
				if (s >= a[i - 1]) {
					int incl = dp[i - 1][s - a[i - 1]];
					int excl = dp[i - 1][s];
					dp[i][s] = incl + excl;
				}
			}
		}
		return dp[n][sum];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 2, 3 };
		// +1-1-2+3, -1+1-2+3, +1+1+2-3
		System.out.println(count(a1, 1)); // 3

		int[] a2 = { 1, 2, 7, 1 };
		// +1+2+7-1, -1+2+7+1
		System.out.println(count(a2, 9)); // 2

		// each zero can take either sign, so eight of them
		// multiply the single assignment of {1} by 2^8
		int[] a3 = { 0, 0, 0, 0, 0, 0, 0, 0, 1 };
		System.out.println(count(a3, 1)); // 256
	}
}
