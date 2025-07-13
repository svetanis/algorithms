package com.svetanis.algorithms.math.pow;

public final class PowerOfTwo {
	// Time Complexity: O(log n)

	private static final int MOD = (int) 1e9 + 7;

	public static long powerOfTwo(long n) {
		return power(2, n);
	}

	private static long power(long x, long n) {
		long pow = 1;
		while (n > 0) {
			if ((n & 1) == 1) {
				pow = (pow * x) % MOD;
			}
			x = (x * x) % MOD;
			n >>= 1;
		}
		return pow;
	}

	public static void main(String[] args) {
		System.out.println(powerOfTwo(2)); // 4
		System.out.println(powerOfTwo(5)); // 32
		System.out.println(powerOfTwo(10)); // 1024
	}
}