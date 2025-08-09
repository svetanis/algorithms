package com.svetanis.algorithms.greedy;

// CSES: Increasing Array

public final class IncreasingArray {
	// Time Complexity: O(n)

	public static long minNumOfMoves(long[] a) {
		long count = 0;
		for (int i = 1; i < a.length; i++) {
			if (a[i - 1] > a[i]) {
				count += (a[i - 1] - a[i]);
				a[i] = a[i - 1];
			}
		}
		return count;
	}

	public static void main(String[] args) {
		long[] a1 = { 3, 2, 5, 1, 7 };
		System.out.println(minNumOfMoves(a1)); // 5

		long[] a2 = { 1, 2, 3 };
		System.out.println(minNumOfMoves(a2)); // 0
	}
}
