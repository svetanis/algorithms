package com.svetanis.algorithms.math.pow;

// 372. Super Pow

// a^b mod 1337, where b is given as an array of decimal digits.
// Read b from its last digit: with b = [4, 3], a^43 = a^3 * (a^10)^4.
// So each step multiplies in x^digit, then moves x one decimal place
// up: x -> x^10. Every digit is 0..9, so each power() call loops at
// most four times.

public final class SuperPower {
	// Time complexity: O(n), n = number of digits in b
	// Space complexity: O(1)

	private static final int MOD = 1337;

	public static int superPow(int a, int[] b) {
		long result = 1;
		long x = a % MOD; // reduce first, so x * x stays small
		for (int i = b.length - 1; i >= 0; i--) {
			result = result * power(x, b[i]) % MOD;
			x = power(x, 10); // a^1 -> a^10 -> a^100 -> ...
		}
		return (int) result;
	}

	// fast exponentiation mod 1337, reading n one binary digit at a time
	private static long power(long x, int n) {
		long result = 1;
		while (n > 0) {
			if (n % 2 != 0) { // n is odd
				result = result * x % MOD;
			}
			n >>= 1;
			x = x * x % MOD;
		}
		return result;
	}

	public static void main(String[] args) {
		int[] a1 = { 3 };
		int[] a2 = { 1, 0 };
		int[] a3 = { 4, 3, 3, 8, 5, 2 };
		System.out.println(superPow(2, a1)); // 8
		System.out.println(superPow(2, a2)); // 1024
		System.out.println(superPow(1, a3)); // 1
	}
}
