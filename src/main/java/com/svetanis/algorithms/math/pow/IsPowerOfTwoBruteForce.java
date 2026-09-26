package com.svetanis.algorithms.math.pow;

// 231. Power of Two

// The slow ways, without the one-line bit test in IsPowerOfTwo:
// halve n while it is even, or count its 1-bits.
//
// Both must turn away n <= 0 first, or they never return on a negative n:
// Java's % keeps the sign, so -1 % 2 is -1, never 1, and the halving
// reaches 0 and stays there; >> copies the sign bit in from the left,
// so -1 >> 1 is still -1.

public final class IsPowerOfTwoBruteForce {
	// Time Complexity: O(log n)
	// Space Complexity: O(1)

	public static boolean isPowerOf2(int n) {
		if (n <= 0) {
			return false;
		}
		while (n != 1) {
			if (n % 2 == 1) {
				return false;
			}
			n = n / 2;
		}
		return true;
	}

	public static boolean isPowerOfTwoCountBits(int n) {
		if (n <= 0) {
			return false;
		}
		return count(n) == 1;
	}

	// number of 1-bits; n must not be negative
	private static int count(int n) {
		int count = 0;
		while (n != 0) {
			count += (n & 1);
			n = n >> 1;
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(isPowerOf2(32)); // true
		System.out.println(isPowerOfTwoCountBits(32)); // true
		System.out.println(isPowerOf2(7)); // false
		System.out.println(isPowerOfTwoCountBits(7)); // false
		System.out.println(isPowerOf2(-4)); // false
		System.out.println(isPowerOfTwoCountBits(-4)); // false
	}
}
