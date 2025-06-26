package com.svetanis.algorithms.math.pow;

// CSES: Exponentiation

// n is even x^n = (x^(n/2))^2
// n is odd  x^n = x * (x^((n - 1)/2))^2

public final class ExponentiationMod {
	// Time complexity: O(log n)

	private static final int MOD = 1000000007;

	public static long pow(long x, long n) {
		if (n == 0) {
			return 1 % MOD;
		}
		if (n == 1) {
			return x % MOD;
		}
		long temp = pow(x, n / 2);
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
	}
}