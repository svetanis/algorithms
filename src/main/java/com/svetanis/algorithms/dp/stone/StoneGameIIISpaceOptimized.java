package com.svetanis.algorithms.dp.stone;

// 1406. Stone Game III

public final class StoneGameIIISpaceOptimized {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static String stoneGame(int[] stones) {
		int n = stones.length;
		int one = 0, two = 0, three = 0;
		for (int i = n - 1; i >= 0; i--) {
			int curr = stones[i] - one;
			if (i + 1 < n) {
				int sum = stones[i] + stones[i + 1] - two;
				curr = Math.max(curr, sum);
			}
			if (i + 2 < n) {
				int sum = stones[i] + stones[i + 1] + stones[i + 2] - three;
				curr = Math.max(curr, sum);
			}
			three = two;
			two = one;
			one = curr;
		}
		if (one > 0) {
			return "Alice";
		} else if (one < 0) {
			return "Bob";
		}
		return "Tie";
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 7 };
		System.out.println(stoneGame(a)); // Bob

		int[] a2 = { 1, 2, 3, -9 };
		System.out.println(stoneGame(a2)); // Alice

		int[] a3 = { 1, 2, 3, 6 };
		System.out.println(stoneGame(a3)); // Tie
	}
}
