package com.svetanis.algorithms.slidingwindow.array;

import java.util.Arrays;

// 1658. Minimum Operations to Reduce X to Zero 

public final class MinOperations {

	public static int minOperations(int[] a, int x) {
		int n = a.length;
		int sum = 0;
		int left = 0;
		int min = Integer.MAX_VALUE;
		int target = Arrays.stream(a).sum() - x;
		for (int right = 0; right < n; right++) {
			sum += a[right];
			while (left <= right && sum > target) {
				sum -= a[left++];
			}
			if (sum == target) {
				int len = right - left + 1;
				min = Math.min(min, n - len);
			}
		}
		return min == Integer.MAX_VALUE ? -1 : min;
	}

	public static void main(String[] args) {
		int[] a = { 1, 1, 4, 2, 3 };
		System.out.println(minOperations(a, 5)); // 2
		int[] a1 = { 5, 6, 7, 8, 9 };
		System.out.println(minOperations(a1, 4)); // -1
		int[] a2 = { 3, 2, 20, 1, 1, 3 };
		System.out.println(minOperations(a2, 10)); // 5
	}
}
