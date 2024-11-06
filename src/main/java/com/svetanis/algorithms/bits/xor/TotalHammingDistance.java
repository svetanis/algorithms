package com.svetanis.algorithms.bits.xor;

// 477. Total Hamming Distance

public final class TotalHammingDistance {
	// Time Complexity: O(n)

	public static int totalHammingDist(int[] nums) {
		int total = 0;
		for (int i = 0; i < 31; i++) {
			int ones = 0;
			int zeros = 0;
			for (int num : nums) {
				int bit = (num >> i) & 1;
				if (bit == 1) {
					ones++;
				} else {
					zeros++;
				}
			}
			total += ones * zeros;
		}
		return total;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 14, 2 };
		System.out.println(totalHammingDist(a1)); // 6

		int[] a2 = { 4, 14, 4 };
		System.out.println(totalHammingDist(a2)); // 4
	}
}
