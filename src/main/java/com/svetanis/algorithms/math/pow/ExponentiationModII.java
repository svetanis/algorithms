package com.svetanis.algorithms.math.pow;

// CSES: Exponentiation II

// x^(a^b) mod MOD, where a^b itself is far too big to compute.
// MOD is prime, so by Fermat's little theorem x^(MOD - 1) = 1 (mod MOD)
// for any x that is not a multiple of MOD. Whole groups of MOD - 1 in
// the exponent multiply by 1, so only a^b mod (MOD - 1) matters.
//
// Fermat does not cover x = 0 when a^b is a positive multiple of
// MOD - 1: the exponent reduces to 0 and 0^0 = 1 comes back instead
// of 0. Under the CSES bounds (a <= 1e9) that cannot happen, because
// MOD - 1 = 2 * 500000003 with 500000003 prime, so a would have to be
// a multiple of MOD - 1 itself.

public final class ExponentiationModII {
	// Time complexity: O(log b + log MOD) per query
	// Space complexity: O(log b + log MOD) for the recursion

	private static final int MOD = 1000000007;

	public static long exponent(long x, long a, long b) {
		// a^b modulo MOD - 1: the exponent, cut down by Fermat
		long ab = pow(a, b, MOD - 1);
		// x^(a^b) modulo MOD
		return pow(x, ab, MOD);
	}

	private static long pow(long x, long n, int mod) {
		// reduce x first: x * sqr below multiplies x in unreduced
		return power(x % mod, n, mod);
	}

	private static long power(long x, long n, int mod) {
		if (n == 0) {
			return 1 % mod;
		}
		if (n == 1) {
			return x;
		}
		long temp = power(x, n / 2, mod);
		long sqr = (temp * temp) % mod;
		if (n % 2 == 1) { // n is odd
			return (x * sqr) % mod;
		}
		return sqr;
	}

	public static void main(String[] args) {
		System.out.println(exponent(2L, 3L, 2L)); // 512
		System.out.println(exponent(3L, 7L, 1L)); // 2187
		System.out.println(exponent(15L, 2L, 2L)); // 50625
		System.out.println(exponent(3L, 4L, 5L)); // 763327764
	}
}
