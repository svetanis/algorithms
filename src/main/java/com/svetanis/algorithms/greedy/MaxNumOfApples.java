package com.svetanis.algorithms.greedy;

import java.util.Arrays;

// 1196. How Many Apples Can You Put into the Basket

public final class MaxNumOfApples {
	// Time Complexity: O(n log n)
	// Space Complexity: O(1)

	public static int countApples(int weight, int[] a) {
		Arrays.sort(a);
		int sum = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			sum += a[i];
			if (sum > weight) {
				return i;
			}
		}
		return n;
	}

	public static void main(String[] args) {
		int[] a = { 600, 1000, 950, 200, 400 };
		System.out.println(countApples(5000, a)); // 5
	}
}
