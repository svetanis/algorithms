package com.svetanis.algorithms.dp.math.pascal;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.min;
import static java.util.Arrays.asList;

import java.util.List;

// given a triangle. find
// the min path sum from
// top to bottom
// each step you may move 
// to adjacent numbers on
// the row below

// brute force: recursive combinatorial search:
// at each step try both left
// and right branch and figure 
// out which one gives the min sum
// 
public final class TriangleBruteForce {
	// Time Complexity: O(2^n)
	// Space Complexity: O(n)

	public static int mps(List<List<Integer>> triangle) {
		return dfs(0, 0, triangle);
	}

	private static int dfs(int row, int col, List<List<Integer>> triangle) {
		if (row == triangle.size()) {
			return 0;
		}
		int left = dfs(row + 1, col, triangle);
		int right = dfs(row + 1, col + 1, triangle);
		return triangle.get(row).get(col) + min(left, right);
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
