package com.svetanis.algorithms.math.gcd;

// 2413. Smallest Even Multiple

// The smallest number divisible by both 2 and n is lcm(n, 2)
// = n * 2 / gcd(n, 2). gcd(n, 2) is 2 when n is even and 1 when n is
// odd, so the answer is n itself for even n and 2n for odd n.

public final class SmallestEvenMultiple {
	// Time Complexity: O(1)
	// Space Complexity: O(1)

	public static int sem(int n) {
		return n % 2 == 0 ? n : n * 2;
	}

	public static void main(String[] args) {
		System.out.println(sem(5)); // 10
		System.out.println(sem(6)); // 6
	}
}
