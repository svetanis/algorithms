package com.svetanis.algorithms.dp.grid.alluniquepaths;

// 62. Unique Paths

public final class RobotOnGridRecursive {
	// Time complexity: exponential

	public static int count(int m, int n) {
		if (m == 1 || n == 1) {
			return 1;
		}
		// if diagonal movements are allowed
		// then last addition is required
		return count(m - 1, n) + count(m, n - 1);
		// + count(m - 1, n - 1);
	}

	public static void main(String[] args) {
		System.out.println(count(3, 7));
	}
}
