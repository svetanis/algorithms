package com.svetanis.algorithms.math.pow;

// CSES: Exponentiation

// n is even x^n = (x^(n/2))^2
// n is odd  x^n = x * (x^((n - 1)/2))^2

// Every product is reduced mod MOD, which is allowed because
// (a * b) % m = ((a % m) * (b % m)) % m. Both factors stay below MOD,
// about 1e9, so a product stays below about 1e18: too big for int,
// small enough for long.

public final class ExponentiationMod {
	// Time complexity: O(log n)
	// Space complexity: O(log n) for the recursion

	private static final int MOD = 1000000007;
	private static final String NEGATIVE_EXPONENT = "exponent must be >= 0, was %d";

	public static long pow(long x, long n) {
		if (n < 0) {
			throw new IllegalArgumentException(NEGATIVE_EXPONENT.formatted(n));
		}
		// reduce x first: x * sqr below multiplies x in unreduced,
		// and for x above about 9e9 that product overflows long
		return power(x % MOD, n);
	}

	private static long power(long x, long n) {
		if (n == 0) {
			return 1 % MOD;
		}
		if (n == 1) {
			return x;
		}
		long temp = power(x, n / 2);
		long sqr = (temp * temp) % MOD;
		if (n % 2 == 1) { // n is odd
			return (x * sqr) % MOD;
		}
		return sqr;
	}

	public static void main(String[] args) {
		System.out.println(pow(4L, 3L)); // 64
		System.out.println(pow(3L, 4L)); // 81
		System.out.println(pow(5L, 3L)); // 125
		System.out.println(pow(2L, 8L)); // 256
		System.out.println(pow(123L, 123L)); // 921450052
		System.out.println(pow(1000000000000L, 3L)); // 2401
	}
}
