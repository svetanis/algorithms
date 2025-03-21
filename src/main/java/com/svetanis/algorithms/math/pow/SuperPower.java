package com.svetanis.algorithms.math.pow;

// 372. Super Pow

public final class SuperPower {
	// Time complexity: O(n)
	// Space complexity: O(1)

	private static final int MOD = 1337;

	public static int superPow(int a, int[] b) {
		long result = 1;
		long x = a;
		for (int i = b.length - 1; i >= 0; i--) {
			result = result * power(x, b[i]) % MOD;
			x = power(x, 10);
		}
		return (int) result;
	}

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