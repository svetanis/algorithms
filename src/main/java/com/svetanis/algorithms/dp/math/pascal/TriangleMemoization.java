package com.svetanis.algorithms.dp.math.pascal;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.util.Arrays.asList;
import static java.util.Arrays.fill;

import java.util.List;

// given a triangle. find
// the min path sum from
// top to bottom
// each step you may move 
// to adjacent numbers on
// the row below

public final class TriangleMemoization {
	// Time Complexity: O(n^2)
	// Space Complexity: O(n^2)

	public static int mps(List<List<Integer>> triangle) {
		int[][] memo = init(triangle.size());
		return dfs(0, 0, memo, triangle);
	}

	private static int[][] init(int n) {
		int[][] memo = new int[n][n];
		for (int r = 0; r < n; r++) {
			fill(memo[r], MAX_VALUE);
		}
		return memo;
	}

	private static int dfs(int r, int c, int[][] memo, 
			List<List<Integer>> triangle) {
		if (r == triangle.size()) {
			return 0;
		}
		if (memo[r][c] != MAX_VALUE) {
			return memo[r][c];
		}
		int left = dfs(r + 1, c, memo, triangle);
		int right = dfs(r + 1, c + 1, memo, triangle);
		int min = triangle.get(r).get(c) + min(left, right);
		memo[r][c] = min;
		return min;
	}

	public static void main(String[] args) {
		List<List<Integer>> grid = newArrayList();
		grid.add(asList(2));
		grid.add(asList(3, 4));
		grid.add(asList(6, 5, 7));
		grid.add(asList(4, 1, 8, 3));
		System.out.println(mps(grid)); // 11: 2->3->5->1
	}
}
