package com.svetanis.algorithms.math;

// 1015. Smallest Integer Divisible by K

public final class SmallestIntDivisibleByK {
	// Time Complexity: O(k)
	// Space Complexity: O(1)

	// Keep only the remainder of 1, 11, 111, ... by k: the next one is
	// (remainder * 10 + 1) % k, and the numbers themselves never need to be
	// built. There are only k possible remainders, so within k lengths one
	// is 0 -- or a remainder has repeated, and from there they cycle without
	// ever reaching 0.
	public static int smallestInt(int k) {
		int n = 1 % k;
		for (int len = 1; len <= k; len++) {
			if (n == 0) {
				return len;
			}
			n = (n * 10 + 1) % k;
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(smallestInt(1)); // 1
		System.out.println(smallestInt(2)); // -1
		System.out.println(smallestInt(3)); // 3
		System.out.println(smallestInt(17)); // 16
	}
}