package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static java.util.Arrays.fill;

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
		// -1 is the "absent" marker. Fibonacci is never negative, so no
		// computed value can collide with it. The old marker was 0, which
		// IS a Fibonacci value -- fib(0) -- and that only survived because
		// n == 0 returns from its base case before the cache is consulted.
		// Copy a 0 marker into a counting problem and it is a silent bug.
		// Same convention as CountWaysToDecodeDigitsMemoization, one file over.
		fill(memo, -1);
		return fib(n, memo);
	}

	private static int fib(int n, int[] memo) {
		// check in memo, if found
		// retrieve and return right away
		if (memo[n] != -1) {
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