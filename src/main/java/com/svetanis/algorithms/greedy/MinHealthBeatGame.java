package com.svetanis.algorithms.greedy;

// 2214. Minimum Health to Beat Game

public final class MinHealthBeatGame {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static long minHealth(int[] damage, int armor) {
		long total = 0;
		int max = damage[0];
		for (int val : damage) {
			total += val;
			max = Math.max(max, val);
		}
		long armorReduction = Math.min(max, armor);
		return total - armorReduction + 1;
	}

	public static void main(String[] args) {
		int[] damage = { 3, 5, 2 };
		System.out.println(minHealth(damage, 4)); // 7

		int[] damage1 = { 2, 7, 4, 3 };
		System.out.println(minHealth(damage1, 4)); // 13
	}
}
