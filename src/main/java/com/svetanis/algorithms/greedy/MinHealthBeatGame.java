package com.svetanis.algorithms.greedy;

// 2214. Minimum Health to Beat Game
//
// Input: damage[i] -- the health lost on level i; armor -- a shield that may be used on one level
// at most, and blocks up to armor damage there. The levels are played in order, and health must
// stay above 0 the whole time. Returns the least starting health that beats the game. On LeetCode:
// 1 <= damage.length <= 10^5, 0 <= damage[i] <= 10^5, 0 <= armor <= 10^5. The total damage can
// reach 10^10, so it is a long.
//
// The one idea: health only ever goes down, so the lowest point is the end of the game, and the
// starting health must be more than all the damage taken: total damage + 1. The armor lowers that
// total by min(armor, the damage of the level it is used on). That amount is largest on the level
// with the most damage, so the armor goes there, and nothing about the other levels can make
// another level a better choice. The order of the levels does not matter at all.
//
// Time: O(n) -- one pass for the total and the largest damage.
// Space: O(1) -- two running values.

public final class MinHealthBeatGame {

	public static long minHealth(int[] damage, int armor) {
		long total = 0;
		int max = damage[0];
		for (int val : damage) {
			total += val; // SUM all the damage
			max = Math.max(max, val); // RECORD the worst level
		}
		long armorReduction = Math.min(max, armor); // SHIELD the worst level
		return total - armorReduction + 1; // RETURN: one more than the damage that gets through
	}

	public static void main(String[] args) {
		int[] damage = { 3, 5, 2 };
		System.out.println(minHealth(damage, 4)); // 7

		int[] damage1 = { 2, 7, 4, 3 };
		System.out.println(minHealth(damage1, 4)); // 13
	}
}
