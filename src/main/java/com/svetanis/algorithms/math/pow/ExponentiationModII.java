package com.svetanis.algorithms.math.pow;

// CSES: Exponentiation II

public final class ExponentiationModII {
	// Time complexity: O(n * log exponent)

	private static final int MOD = 1000000007;

	public static long exponent(long x, long a, long b) {
		// calculate b^c modulo MOD - 1
		long ab = pow(a, b, MOD - 1);
		// calculate a^(b^c) modulo MOD
		return pow(x, ab, MOD);
	}

	private static long pow(long x, long n, int mod) {
		if (n == 0) {
			return 1 % mod;
		}
		if (n == 1) {
			return x % mod;
		}
		long temp = pow(x, n / 2, mod);
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