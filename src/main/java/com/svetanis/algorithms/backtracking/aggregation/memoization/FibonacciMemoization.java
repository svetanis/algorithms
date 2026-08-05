package com.svetanis.algorithms.backtracking.aggregation.memoization;

// 509. Fibonacci Number

// the memoized rung. The naive one it is built from is
// com.svetanis.algorithms.dp.math.fib.FibonacciRecursive -- same base cases,
// no cache, exponential. That folder also carries the tabulated and
// space-optimized rungs; this file is here for the backtrack -> memoize step.

public final class FibonacciMemoization {
	// Time Complexity: O(n)
	// Space Complexity: O(n)

	public static int fib(int n) {
		int[] memo = new int[n + 1];
		return fib(n, memo);
	}

	private static int fib(int n, int[] memo) {
		// check in memo, if found
		// retrieve and return right away.
		// 0 doubles as "absent" here, and fib(0) is genuinely 0 -- but the
		// collision cannot fire: n == 0 returns from its base case below and
		// is never written to memo. Correct, though only by that accident;
		// Integer[] with null would make the sentinel honest.
		if (memo[n] != 0) {
			return memo[n];
		}

		if (n == 0) {
			return 0;
		} else if (n == 1 || n == 2) {
			return 1;
		}

		int fib = fib(n - 1, memo) + fib(n - 2, memo);
		// save result to memo
		// before returning
		memo[n] = fib;
		return fib;

	}

	public static void main(String[] args) {
		System.out.println(fib(8));
	}
}