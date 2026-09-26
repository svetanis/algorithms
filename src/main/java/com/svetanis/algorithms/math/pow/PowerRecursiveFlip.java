package com.svetanis.algorithms.math.pow;

// 50. Pow(x, n)

// n is even x^n = (x^(n/2))^2
// n is odd  x^n = x * (x^((n - 1)/2))^2
// n < 0     x^n = 1 / x^(-n)

// A negative n is flipped to positive first, so the recursion only
// ever sees n >= 0. The flip happens on a long: int holds one more
// negative number than positive ones, so for n = Integer.MIN_VALUE
// -n in int arithmetic comes back as MIN_VALUE again.
// PowerRecursive never flips and handles negative n directly.

public final class PowerRecursiveFlip {
	// Time complexity: O(log n)
	// Space complexity: O(log n) for the recursion

	public static double pow(double x, int n) {
		long pow = n; // copy into a long BEFORE negating
		if (pow < 0) {
			pow = -pow;
			return 1 / power(x, pow);
		}
		return power(x, pow);
	}

	private static double power(double x, long n) {
		if (n == 0) {
			return 1;
		}
		// computed once: calling power(x, n / 2) twice makes O(n) calls
		double half = power(x, n / 2);
		if (n % 2 != 0) { // n is odd: 5 / 2 = 2, one x short
			return x * half * half;
		}
		return half * half;
	}

	public static void main(String[] args) {
		System.out.println(pow(2.0, 10)); // 1024
		System.out.println(pow(2.1, 3)); // 9.261
		System.out.println(pow(2.0, -2)); // 0.25
		System.out.println(pow(5.0, -3)); // 0.008
		System.out.println(pow(2.0, Integer.MIN_VALUE)); // 0.0
	}
}
