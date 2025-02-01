package com.svetanis.algorithms.dp.countways.stairs;

// 70. Climbing Stairs

public final class ClimbingStairs70BottomUpOptimized {
	// Time complexity: O(n)
	// Space complexity: O(1)

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
		int oneStepBefore = 2;
		int twoStepsBefore = 1;
		for (int i = 3; i <= n; i++) {
			int current = oneStepBefore + twoStepsBefore;
			twoStepsBefore = oneStepBefore;
			oneStepBefore = current;
		}
		return oneStepBefore;
	}

	public static void main(String[] args) {
		System.out.println(count(2)); // 2
		System.out.println(count(3)); // 3
	}
}
