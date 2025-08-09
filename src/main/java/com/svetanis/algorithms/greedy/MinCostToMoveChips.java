package com.svetanis.algorithms.greedy;

// 1217. Minimum Cost to Move Chips to The Same Position

public final class MinCostToMoveChips {
	// Time Complexity: O(n)

	public static int minCost(int[] a) {
		int odd = 0;
		int even = 0;
		for (int num : a) {
			if (num % 2 == 0) {
				even++;
			} else {
				odd++;
			}
		}
		return Math.min(odd, even);
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 3 };
		System.out.println(minCost(a1)); // 1
		int[] a2 = { 2, 2, 2, 3, 3 };
		System.out.println(minCost(a2)); // 2
		int[] a3 = { 1, 1000000000 };
		System.out.println(minCost(a3)); // 1
	}
}
