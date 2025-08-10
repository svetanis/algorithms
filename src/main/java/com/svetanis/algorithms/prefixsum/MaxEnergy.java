package com.svetanis.algorithms.prefixsum;

// 926. Flip String to Monotone Increasing

public final class MaxEnergy {
	// Time Complexity: O(n)
	// Space Complexity: O(1)

	public static int maxEnergy(int[] energy, int k) {
		int max = Integer.MIN_VALUE;
		int n = energy.length;
		for (int i = n - k; i < n; i++) {
			for (int j = i, sum = 0; j >= 0; j -= k) {
				sum += energy[j];
				max = Math.max(max, sum);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] a1 = { 5, 2, -10, -5, 1 };
		System.out.println(maxEnergy(a1, 3)); // 3
		int[] a2 = { -2, -3, -1 };
		System.out.println(maxEnergy(a2, 2)); // -1
	}
}
