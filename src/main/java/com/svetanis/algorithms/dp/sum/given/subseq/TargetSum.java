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

public final class TargetSum {
	// Time complexity: O(sum * n)

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
		// the value of subset[i][j] will be true
		// if there is a subset of set[0 ... j - 1]
		// with sum equal to i
		int[][] dp = new int[sum + 1][n + 1];
		// if sum is 0, then answer is true
		for (int i = 0; i <= n; ++i) {
			dp[0][i] = 1;
		}
		// if sum is not 0 and set is empty,
		// then answer is false
		for (int s = 1; s <= sum; s++) {
			dp[s][0] = 0;
		}
		// fill the subset table in bottom up manner
		for (int s = 1; s <= sum; ++s) {
			for (int j = 1; j <= n; ++j) {
				dp[s][j] = dp[s][j - 1];
				if (s >= a[j - 1]) {
					int incl = dp[s - a[j - 1]][j - 1];
					int excl = dp[s][j - 1];
					dp[s][j] = incl + excl;
				}
			}
		}
		return dp[sum][n];
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 2, 3 };
		// +1-1-2+3, -1+1-2+3, +1+1+2-3
		System.out.println(count(a1, 1)); // 3

		int[] a2 = { 1, 2, 7, 1 };
		// +1+2+7-1, -1+2+7+1
		System.out.println(count(a2, 9)); // 2
	}
}
