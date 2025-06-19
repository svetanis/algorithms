package com.svetanis.algorithms.dp.editdist;

import static java.lang.Math.min;

// Given strings s1 and s2, we need to transform s1 into s2 
// by deleting, inserting, or replacing characters. 
// Write a function to calculate the count 
// of the min number of edit operations.

public final class EditDistanceRecursive {
	// Time complexity: O(n * m)

	public static int editDist(String x, String y) {
		int n = x.length();
		int m = y.length();
		return dfs(x, y, n, m);
	}

	private static int dfs(String x, String y, int n, int m) {
		// base cases
		if (n == 0 && m == 0) {
			return 0;
		}
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		if (x.charAt(n - 1) == y.charAt(m - 1)) {
			return dfs(x, y, n - 1, m - 1);
		}
		int delete = 1 + dfs(x, y, n - 1, m);
		int insert = 1 + dfs(x, y, n, m - 1);
		int replace = 1 + dfs(x, y, n - 1, m - 1);
		return min(min(delete, insert), replace);
	}

	public static void main(String[] args) {
		System.out.println(editDist("Zeil", "trials")); // 4
		System.out.println(editDist("cat", "act")); // 2
		System.out.println(editDist("COMBO", "COIN")); // 3
		System.out.println(editDist("Anshuman", "Antihuman")); // 2
		System.out.println(editDist("intention", "execution")); // 5
		System.out.println(editDist("brainstorming", "imagination")); // 9
		System.out.println(editDist("dj", "abcdef")); // 5
	}
}
