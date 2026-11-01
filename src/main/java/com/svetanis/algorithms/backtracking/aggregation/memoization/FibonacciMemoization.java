package com.svetanis.algorithms.backtracking.aggregation.memoization;

import static java.util.Arrays.fill;

// 509. Fibonacci Number
//
// Input: n, 0 <= n <= 30 on LeetCode. Returns F(n), where F(0) = 0, F(1) = 1 and
// F(n) = F(n - 1) + F(n - 2). The result is an int, so it is exact only up to F(46).
//
// The one idea: the plain recursion computes the same F(k) over and over -- F(n - 2) is needed
// by F(n) directly and again inside F(n - 1). One cell of memo holds F(k) for one k, or -1 while
// it has not been computed. Each call looks in its cell first, and writes the answer there before
// returning, so every F(k) is computed once.
//
// -1 is the "not computed" marker because Fibonacci numbers are never negative, so no computed
// value can equal it. A 0 marker would collide with F(0) = 0; it would still work here only
// because n == 0 is answered before the memo is read -- in a counting problem where 0 is a real
// answer it is a silent bug.
//
// Time: O(n) -- n cells, each filled once with O(1) work.
// Space: O(n) -- the memo, and a recursion n deep.
//
// Siblings: com.svetanis.algorithms.dp.math.fib.FibonacciRecursive has the same base cases and no
// memo, so it is exponential; that package also holds the tabulated and space-optimized versions.
// This file is the backtrack -> memoize step.

public final class FibonacciMemoization {

	public static int fib(int n) {
		int[] memo = new int[n + 1];
		fill(memo, -1); // MARKER: -1 means not computed yet
		return fib(n, memo);
	}

	private static int fib(int n, int[] memo) {
		// LOOKUP: if the cell is filled, return it right away
		if (memo[n] != -1) {
			return memo[n];
		}

		if (n == 0) { // BASE
			return 0;
		} else if (n == 1 || n == 2) {
			return 1;
		}

		int fib = fib(n - 1, memo) + fib(n - 2, memo); // SPLIT: the two smaller questions
		// RECORD: save the result in memo before returning
		memo[n] = fib;
		return fib;

	}

	public static void main(String[] args) {
		System.out.println(fib(8)); // 21
	}
}