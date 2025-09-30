package com.svetanis.algorithms.dp.grid.alluniquepaths;

import java.util.HashMap;
import java.util.Map;

// 62. Unique Paths

// a robot starts its journey at
// the top-left corner of a grid
// that measures m x n
// m rows by n columns
// at each step, the robot has only
// two possible directions: it can
// either move to the right or 
// move downward. its destination is 
// the bottom-right corner of the grid
// find the total number of unique paths
// the robot can take to reach its dst

public final class RobotOnGridMemoization {

	public static int countUniquePaths(int m, int n) {
		Map<String, Integer> memo = new HashMap<>();
		return dfs(m, n, memo);
	}

	private static int dfs(int m, int n, Map<String, Integer> memo) {
		if (m == 1 || n == 1) {
			return 1;
		}
		String key = m + "," + n;
		if (memo.containsKey(key)) {
			return memo.get(key);
		}
		int result = dfs(m - 1, n, memo) + dfs(m, n - 1, memo);
		memo.put(key, result);
		return result;
	}

	public static void main(String[] args) {
		// n rows and m columns
		System.out.println(countUniquePaths(3, 2)); // 3
		System.out.println(countUniquePaths(7, 3)); // 28
		System.out.println(countUniquePaths(1, 1)); // 1
		System.out.println(countUniquePaths(10, 5)); // 715
	}
}
