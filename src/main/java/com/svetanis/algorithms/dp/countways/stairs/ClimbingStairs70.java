package com.svetanis.algorithms.dp.countways.stairs;

// 70. Climbing Stairs

public final class ClimbingStairs70 {
	// Time complexity: O(2^n)

	public static int count(int n) {
		if (n < 0) {
			return 0;
		}
		if (n == 0 || n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		return count(n - 1) + count(n - 2);
	}

	public static void main(String[] args) {
		System.out.println(count(2)); // 2
		System.out.println(count(3)); // 3
	}
}
