package com.svetanis.algorithms.backtracking.aggregation.memoization;

// see more examples in com.svetanis.algorithms.dp.math.fib
public final class FibonacciBacktracking {

	public static int fib(int n) {
		int[] memo = new int[n + 1];
		return fib(n, memo);
	}

	private static int fib(int n, int[] memo) {
		// check in memo, if found
		// retrieve and return right away
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