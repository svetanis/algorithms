package com.svetanis.algorithms.dp.math.pascal;

import java.util.ArrayList;
import java.util.List;

// 118. Pascal's Triangle

public final class PascalTriangleRowBottomUp {
	// Time complexity: O(n^2)
	// Auxilary space: O(n)

	public static List<Integer> pascal(int n) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			list.add(1);
		}
		for (int row = 2; row <= n; ++row) {
			for (int col = row - 1; col > 0; col--) {
				list.set(col, list.get(col) + list.get(col - 1));
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(pascal(3)); // 1, 3, 3, 1
		System.out.println(pascal(0)); // 1,
		System.out.println(pascal(1)); // 1, 1
	}
}
