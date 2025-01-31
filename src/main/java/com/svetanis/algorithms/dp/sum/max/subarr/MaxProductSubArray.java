package com.svetanis.algorithms.dp.sum.max.subarr;

// 152. Maximum Product Subarray

public final class MaxProductSubArray {
	// Time Complexity: O(n)
	
	public static int maxProductSubArray(int[] a) {
		int min = a[0];
		int max = a[0];
		int maxProduct = a[0];
		for (int i = 1; i < a.length; i++) {
			int currentMax = max;
			int currentMin = min;
			max = Math.max(a[i], Math.max(currentMax * a[i], currentMin * a[i]));
			min = Math.min(a[i], Math.min(currentMax * a[i], currentMin * a[i]));
			maxProduct = Math.max(maxProduct, max);
		}
		return maxProduct;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 3, -2, 4 };
		System.out.println(maxProductSubArray(a1)); // 6

		int[] a2 = { -2, 0, -1 };
		System.out.println(maxProductSubArray(a2)); // 0
	}
}
