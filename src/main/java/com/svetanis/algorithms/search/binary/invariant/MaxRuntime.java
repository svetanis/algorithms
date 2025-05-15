package com.svetanis.algorithms.search.binary.invariant;

// 2141. Maximum Running Time of N Computers

public final class MaxRuntime {
	// Time Complexity: O(n * log s)
	// Space Complexity: O(1)

	public static long maxRunTime(int n, int[] batteries) {
		long left = 0;
		long right = 0;
		for (int battery : batteries) {
			right += battery;
		}
		while (left < right) {
			long mid = (left + right + 1) >> 1;
			if (canRun(batteries, mid, n)) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		return left;
	}

	private static boolean canRun(int[] batteries, long mid, int n) {
		long sum = 0;
		for (int battery : batteries) {
			sum += Math.min(battery, mid);
		}
		return sum >= n * mid;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 3, 3 };
		System.out.println(maxRunTime(2, a1)); // 4
		int[] a2 = { 1, 1, 1, 1 };
		System.out.println(maxRunTime(2, a2)); // 2
	}
}
