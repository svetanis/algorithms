package com.svetanis.algorithms.math.pow;

// 2^n mod 1e9+7 for n up to about 1e18.

// The loop reads n one binary digit at a time, lowest first, while x
// is squared each step: 2, 2^2, 2^4, 2^8, ... Every 1 digit of n
// multiplies its power of 2 into the result.
//   13 = 1101 in binary, so 2^13 = 2^8 * 2^4 * 2^1

public final class PowerOfTwoMod {
	// Time Complexity: O(log n)
	// Space Complexity: O(1)

	private static final int MOD = (int) 1e9 + 7;
	private static final String NEGATIVE_EXPONENT = "exponent must be >= 0, was %d";

	public static long powerOfTwo(long n) {
		if (n < 0) {
			throw new IllegalArgumentException(NEGATIVE_EXPONENT.formatted(n));
		}
		return power(2, n);
	}

	private static long power(long x, long n) {
		long pow = 1;
		while (n > 0) {
			if ((n & 1) == 1) { // lowest binary digit of n is 1
				pow = (pow * x) % MOD;
			}
			x = (x * x) % MOD; // 2^1 -> 2^2 -> 2^4 -> ...
			n >>= 1; // drop that digit
		}
		return pow;
	}

	public static void main(String[] args) {
		System.out.println(powerOfTwo(2)); // 4
		System.out.println(powerOfTwo(5)); // 32
		System.out.println(powerOfTwo(10)); // 1024
		System.out.println(powerOfTwo(1000000006)); // 1
	}
}
