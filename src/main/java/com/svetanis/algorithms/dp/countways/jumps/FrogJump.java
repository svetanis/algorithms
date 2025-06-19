package com.svetanis.algorithms.dp.countways.jumps;

import java.util.HashMap;
import java.util.Map;

// 403. Frog Jump

public final class FrogJump {
	// Time Complexity: O(n^2)

	private Boolean[][] dp;
	private Map<Integer, Integer> map;

	public boolean canCross(int[] stones) {
		int n = stones.length;
		this.map = indexes(stones);
		this.dp = new Boolean[n][n];
		return dfs(stones, 0, 0);
	}

	private Map<Integer, Integer> indexes(int[] stones) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < stones.length; i++) {
			map.put(stones[i], i);
		}
		return map;
	}

	private boolean dfs(int[] a, int index, int k) {
		int n = a.length;
		if (index == n - 1) {
			return true;
		}
		if (dp[index][k] != null) {
			return dp[index][k];
		}
		for (int jump = k - 1; jump <= k + 1; jump++) {
			if (jump <= 0) {
				continue;
			}
			int next = a[index] + jump;
			if (map.containsKey(next) && dfs(a, map.get(next), jump)) {
				return dp[index][k] = true;
			}
		}
		return dp[index][k] = false;
	}

	public static void main(String[] args) {
		FrogJump fj = new FrogJump();
		int[] a1 = { 0, 1, 3, 5, 6, 8, 12, 17 };
		System.out.println(fj.canCross(a1)); // true
		FrogJump fj2 = new FrogJump();
		int[] a2 = { 0, 1, 2, 3, 4, 8, 9, 11 };
		System.out.println(fj2.canCross(a2)); // false
	}
}
