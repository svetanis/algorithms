package com.svetanis.algorithms.math.pow;

// 231. Power of Two

// A power of two has exactly one 1-bit: 8 is 1000, 32 is 100000.
// Both methods test for that single bit, after turning away n <= 0.
//
// n > 0, not n != 0: Integer.MIN_VALUE (-2^31) is also a single 1-bit --
// the sign bit -- so both bit tests say yes to it, and it is in range.

public final class IsPowerOfTwo {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	// n & -n keeps only the rightmost 1-bit;
	// a power of two has no other bit to lose
	public static boolean isPowerOfTwoIsolate(int n) {
		return n > 0 && (n & (-n)) == n;
	}

	// n & (n - 1) turns off the rightmost 1-bit:
	// 1000 & 0111 = 0000, but 1010 & 1001 = 1000
	public static boolean isPowerOf2(int n) {
		return n > 0 && (n & (n - 1)) == 0;
	}

	public static void main(String[] args) {
		System.out.println(isPowerOf2(32)); // true
		System.out.println(isPowerOf2(7)); // false
		System.out.println(isPowerOf2(Integer.MIN_VALUE)); // false
		System.out.println(isPowerOfTwoIsolate(32)); // true
		System.out.println(isPowerOfTwoIsolate(7)); // false
		System.out.println(isPowerOfTwoIsolate(Integer.MIN_VALUE)); // false
	}
}
