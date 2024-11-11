package com.svetanis.algorithms.dp.countways;

import static com.svetanis.java.base.collect.Lists.newList;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 935. Knight Dialer

public final class KnightDialerTopDown {

	private static final int MOD = (int) 1e9 + 7;

	public static int topDown(int n) {
		int total = 0;
		Map<Integer, List<Integer>> map = neighbours();
		Integer[][] dp = new Integer[n + 1][map.size()];
		fill(dp[0], 1);
		for (int src : map.keySet()) {
			total = (total + dfs(src, n, dp, map)) % MOD;
		}
		return total;
	}

	private static int dfs(int src, int len, Integer[][] dp, Map<Integer, List<Integer>> map) {
		if (len == 1) {
			return 1;
		}
		if (dp[len][src] != null) {
			return dp[len][src];
		}
		int count = 0;
		for (int val : map.get(src)) {
			count = (count + dfs(val, len - 1, dp, map)) % MOD;
		}
		dp[len][src] = count;
		return count;
	}

	private static Map<Integer, List<Integer>> neighbours() {
		Map<Integer, List<Integer>> map = new HashMap<>();
		map.put(0, Arrays.asList(4, 6));
		map.put(1, Arrays.asList(6, 8));
		map.put(2, Arrays.asList(7, 9));
		map.put(3, Arrays.asList(4, 8));
		map.put(4, Arrays.asList(3, 9, 0));
		map.put(5, Arrays.asList());
		map.put(6, newList(asList(0, 7, 1)));
		map.put(7, newList(asList(2, 6)));
		map.put(8, newList(asList(1, 3)));
		map.put(9, newList(asList(2, 4)));
		return map;
	}

	public static void main(String[] args) {
		System.out.println(topDown(1)); // 10
		System.out.println(topDown(2)); // 20
		System.out.println(topDown(3131)); // 136006598
	}
}
