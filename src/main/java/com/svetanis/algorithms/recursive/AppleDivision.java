package com.svetanis.algorithms.recursive;

// CSES: Apple Division

public final class AppleDivision {
	// Time Complexity: O(2^n)

	public static int appleDivision(int[] a) {
		return dfs(0, a, 0, 0);
	}

	private static int dfs(int index, int[] a, int sum1, int sum2) {
		int n = a.length;
		if (index == n) {
			return Math.abs(sum1 - sum2);
		}
		int incl = dfs(index + 1, a, sum1 + a[index], sum2);
		int excl = dfs(index + 1, a, sum1, sum2 + a[index]);
		return Math.min(incl, excl);
	}

	public static void main(String[] args) {
		int[] a = { 3, 2, 7, 4, 1 };
		System.out.println(appleDivision(a)); // 1

		int[] a2 = { 1, 2, 3, 4 };
		System.out.println(appleDivision(a2)); // 0
	}
}
