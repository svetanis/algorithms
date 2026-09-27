package com.svetanis.algorithms.bits.popcount;

// 477. Total Hamming Distance

// two numbers differ in slot i exactly when one has 1 there and the
// other 0, so slot i adds ones * zeros to the total over all pairs

public final class TotalHammingDistance {
	// Time Complexity: O(n) -- 31 passes over the array
	// Space Complexity: O(1)

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
			total += ones * zeros; // pairs that differ in slot i
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
