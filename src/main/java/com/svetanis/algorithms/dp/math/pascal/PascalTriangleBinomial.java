package com.svetanis.algorithms.dp.math.pascal;

import static com.svetanis.algorithms.dp.math.binomialcoefficient.BinomialEfficient.binom;

public final class PascalTriangleBinomial {
	// Time complexity: O(n^3)

	public static void pascal(int n) {
		// iterate through every line
		// and print entries in it
		for (int line = 0; line < n; ++line) {
			// every line has number of
			// integers equal to line number
			for (int i = 0; i <= line; ++i) {
				System.out.print(binom(line, i) + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int n = 7;
		pascal(n);
	}
}
