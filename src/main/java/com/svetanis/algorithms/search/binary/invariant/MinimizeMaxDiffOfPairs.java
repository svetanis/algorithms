package com.svetanis.algorithms.search.binary.invariant;

import java.util.Arrays;

// 2616. Minimize the Maximum Difference of Pairs

public final class MinimizeMaxDiffOfPairs {
	// Time Complexity: (n * (log n + log m))

	public static int minimizeMax(int[] a, int p) {
		int n = a.length;
		Arrays.sort(a);
		int low = 0;
		int high = a[n - 1] - a[0] + 1;
		while (low < high) {
			int mid = (low + high) >>> 1;
			if (greedyCount(a, mid) >= p) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private static int greedyCount(int[] a, int x) {
		int count = 0;
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i + 1] - a[i] <= x) {
				count++;
				i++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] a1 = { 10, 1, 2, 7, 1, 3 };
		System.out.println(minimizeMax(a1, 2)); // 1
		int[] a2 = { 4, 2, 1, 2 };
		System.out.println(minimizeMax(a2, 1)); // 0
	}
}