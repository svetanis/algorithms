package com.svetanis.algorithms.dp.countways.dice;

// 1155. Number of Dice Rolls With Target Sum

// returns number of ways to get sum
// 'target' with 'n' dice and 'k' throws

public final class DiceThrowRecursive {

	public static int diceThrow(int n, int k, int target) {
		if (target < 1) {
			return 0;
		}
		if (n == 1) {
			return target <= k ? 1 : 0;
		}
		int count = 0;
		for (int face = 1; face <= k; face++) {
			count += diceThrow(n - 1, k, target - face);
		}
		return count;
	}

	public static void main(String[] args) {
		System.out.println(diceThrow(2, 4, 1)); // 0
		System.out.println(diceThrow(2, 2, 3)); // 2
		System.out.println(diceThrow(3, 6, 8)); // 21
		System.out.println(diceThrow(2, 4, 5)); // 4
		System.out.println(diceThrow(3, 4, 5)); // 6
		System.out.println(diceThrow(1, 6, 3)); // 1
		System.out.println(diceThrow(2, 6, 7)); // 6
	}
}
