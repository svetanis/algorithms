package com.svetanis.algorithms.backtracking.pruning;

import java.util.Arrays;

// 473. Matchsticks to Square

public final class MatchsticksToSquare {

	public static boolean makeSquare(int[] matchsticks) {
		int sum = Arrays.stream(matchsticks).sum();
		int max = Arrays.stream(matchsticks).max().getAsInt();
		int side = sum / 4;
		int remainder = sum % 4;
		if (remainder != 0 || side < max) {
			return false;
		}
		Arrays.sort(matchsticks);
		int[] dp = new int[4];
		int n = matchsticks.length;
		return dfs(matchsticks, n - 1, side, dp);
	}

	private static boolean dfs(int[] matchsticks, int index, int target, int[] dp) {
		if (index < 0) {
			return true;
		}
		for (int i = 0; i < 4; i++) {
			if (i > 0 && dp[i - 1] == dp[i]) {
				continue;
			}
			dp[i] += matchsticks[index];
			if (dp[i] <= target && dfs(matchsticks, index - 1, target, dp)) {
				return true;
			}
			dp[i] -= matchsticks[index];
		}
		return false;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 1, 2, 2, 2 };
		System.out.println(makeSquare(a1)); // true
		int[] a2 = { 3, 3, 3, 3, 4 };
		System.out.println(makeSquare(a2)); // false
	}
}
