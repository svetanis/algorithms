package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 561. Array Partition

public final class ArrayPartition {
	// Time Complexity: O(n log n)

	public static int pairSum(int[] a) {
		Arrays.sort(a);
		int sum = 0;
		for (int i = 0; i < a.length; i += 2) {
			sum += a[i];
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 4, 3, 2 };
		System.out.println(pairSum(a1)); // 4

		int[] a2 = { 6, 2, 6, 5, 1, 2 };
		System.out.println(pairSum(a2)); // 9
	}
}
