package com.svetanis.algorithms.dp.math.pascal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 118. Pascal's Triangle

public final class PascalTriangleBottomUp {
	// Time complexity: O(n^2)
	// Auxilary space: O(n^2)

	public static List<List<Integer>> pascal(int n) {
		List<List<Integer>> lists = new ArrayList<>();
		lists.add(Arrays.asList(1));
		for (int row = 1; row < n; ++row) {
			List<Integer> prev = lists.get(row - 1);
			List<Integer> list = new ArrayList<>();
			list.add(1);
			for (int col = 1; col < row; ++col) {
				int up = prev.get(col);
				int left = prev.get(col - 1);
				list.add(up + left);
			}
			list.add(1);
			lists.add(list);
		}
		return lists;
	}

	public static void main(String[] args) {
		int n = 7;
		System.out.println(pascal(n));
	}
}
