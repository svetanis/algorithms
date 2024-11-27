package com.svetanis.algorithms.dp.math;

import java.util.HashMap;
import java.util.Map;

// 397. Integer Replacement

public final class IntegerReplacementTopDown {

	public static int intReplacement(int n) {
		Map<Long, Integer> map = new HashMap<>();
		return dfs(n, map);
	}

	private static int dfs(long n, Map<Long, Integer> map) {
		if (n == 1) {
			return 0;
		}
		if (map.containsKey(n)) {
			return map.get(n);
		}
		int count = 0;
		if (n % 2 == 0) {
			count = 1 + dfs(n / 2, map);
		} else {
			count = 1 + Math.min(dfs(n - 1, map), dfs(n + 1, map));
		}
		map.put(n, count);
		return count;
	}

	public static void main(String[] args) {
		System.out.println(intReplacement(8)); // 3: 8->4->2->1
		System.out.println(intReplacement(7)); // 4: 7->8->4->2->1 or 7->6->3->2->1
		System.out.println(intReplacement(4)); // 2
	}
}
