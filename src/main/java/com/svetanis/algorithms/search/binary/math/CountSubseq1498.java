package com.svetanis.algorithms.search.binary.math;

import java.util.Arrays;

// 1498. Number of Subsequences That 
// Satisfy the Given Sum Condition

public final class CountSubseq1498 {
	// Time Complexity: O(n log n)
	// Space Complexity: O(1)

	private static final int MOD = (int) 1e9 + 7;

	public static int count(int[] a, int target) {
		Arrays.sort(a);
		int n = a.length;
		int count = 0;
		int[] powerOfTwo = powerOfTwo(n);
		for (int i = 0; i < n; i++) {
			if (a[i] * 2L > target) {
				break;
			}
			int index = binary(a, target - a[i], i + 1) - 1;
			count = (count + powerOfTwo[index - i]) % MOD;
		}
		return count;
	}

	private static int[] powerOfTwo(int n) {
		int[] a = new int[n + 1];
		a[0] = 1;
		for (int i = 1; i <= n; i++) {
			a[i] = (a[i - 1] * 2) % MOD;
		}
		return a;
	}

	private static int binary(int[] a, int target, int left) {
		int right = a.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (a[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] a = { 3, 5, 6, 7 };
		System.out.println(count(a, 9)); // 4

		int[] a1 = { 3, 3, 6, 8 };
		System.out.println(count(a1, 10)); // 6

		int[] a2 = { 2, 3, 3, 4, 6, 7 };
		System.out.println(count(a2, 12)); // 61
	}
}