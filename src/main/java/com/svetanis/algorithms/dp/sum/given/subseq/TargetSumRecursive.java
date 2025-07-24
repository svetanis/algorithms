package com.svetanis.algorithms.dp.sum.given.subseq;

// 494. Target Sum

public final class TargetSumRecursive {
	// Time complexity: O(2^n)

	public static int count(int[] a, int target) {
		return dfs(a, target, 0, 0);
	}

	private static int dfs(int[] a, int target, int index, int sum) {
		if (index == a.length) {
			return sum == target ? 1 : 0;
		}
		int incl = dfs(a, target, index + 1, sum + a[index]);
		int excl = dfs(a, target, index + 1, sum - a[index]);
		return incl + excl;
	}

	public static void main(String[] args) {
		int[] a3 = { 1, 1, 1, 1, 1 };
		System.out.println(count(a3, 3)); // 5

		int[] a4 = { 1 };
		System.out.println(count(a4, 1)); // 1

		int[] a5 = { 0, 0, 0, 0, 0, 0, 0, 0, 1 };
		System.out.println(count(a5, 1)); // 256

		int[] a6 = { 1, 2, 1 };
		System.out.println(count(a6, 0)); // 2
	}
}
