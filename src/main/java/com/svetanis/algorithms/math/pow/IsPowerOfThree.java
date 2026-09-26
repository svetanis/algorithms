package com.svetanis.algorithms.math.pow;

// 326. Power of Three

public final class IsPowerOfThree {

	// Divide out 3 while it goes in evenly; a power of three ends at 1.
	// 27 -> 9 -> 3 -> 1, but 18 -> 6 -> 2
	// Time Complexity: O(log n)
	// Space Complexity: O(1)
	public static boolean isPowerOfThree(int n) {
		if (n <= 0) {
			return false;
		}
		while (n % 3 == 0) {
			n /= 3;
		}
		return n == 1;
	}

	// 3^19 = 1,162,261,467 is the largest power of three an int can hold.
	// Its only divisors are 1, 3, 9, ..., 3^19 -- 3 is prime -- so a
	// positive n divides it exactly when n is a power of three.
	// Time Complexity: O(1)
	// Space Complexity: O(1)
	public static boolean isPowerOfThreeByDivisor(int n) {
		return n > 0 && 1162261467 % n == 0;
	}

	public static void main(String[] args) {
		System.out.println(isPowerOfThree(27)); // true
		System.out.println(isPowerOfThree(0)); // false
		System.out.println(isPowerOfThree(-1)); // false
		System.out.println(isPowerOfThreeByDivisor(27)); // true
		System.out.println(isPowerOfThreeByDivisor(18)); // false
		System.out.println(isPowerOfThreeByDivisor(-1)); // false
	}
}
