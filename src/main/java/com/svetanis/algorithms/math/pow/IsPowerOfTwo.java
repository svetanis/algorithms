package com.svetanis.algorithms.math.pow;

// 231. Power of Two

public final class IsPowerOfTwo {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	// get/isolate the rightmost 1-bit
	public static boolean isPowerOfTwoIsolate(int n) {
		return n != 0 && (n & (-n)) == n;
	}

	// turn off the rightmost 1-bit
	public static boolean isPowerOf2(int n) {
		return n != 0 && (n & (n - 1)) == 0;
	}

	public static void main(String[] args) {
		System.out.println(isPowerOf2(32)); // true
		System.out.println(isPowerOf2(7)); // false
		System.out.println(isPowerOfTwoIsolate(32)); // true
		System.out.println(isPowerOfTwoIsolate(7)); // false
	}
}