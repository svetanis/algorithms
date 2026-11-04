package com.svetanis.algorithms.bits.popcount;

// 477. Total Hamming Distance
//
// Input: an int array nums. The Hamming distance of two numbers is the number of slots where their
// binary digits differ. Returns the sum of the distances over every pair of nums. On LeetCode:
// 1 <= nums.length <= 10^4, 0 <= nums[i] <= 10^9, and the answer fits in an int.
//
// The one idea: count slot by slot instead of pair by pair. A pair's distance is the number of
// slots where it differs, so the total over all pairs is, for each slot, the number of pairs that
// differ there, added up. Two numbers differ in slot i exactly when one has 1 there and the other
// has 0; with ones numbers holding a 1 and zeros holding a 0, that is ones * zeros pairs. Slots 0 to
// 30 cover every value up to 10^9 < 2^30.
//
// Time: O(n) -- 31 passes over the array, 31 * n steps.
// Space: O(1) -- three counters.
//
// Siblings: HammingDistance (461) is the distance of one pair.

public final class TotalHammingDistance {
	// Time Complexity: O(n) -- 31 passes over the array
	// Space Complexity: O(1)

	public static int totalHammingDist(int[] nums) {
		int total = 0;
		for (int i = 0; i < 31; i++) { // WALK the slots
			int ones = 0;
			int zeros = 0;
			for (int num : nums) { // SPLIT the array on slot i
				int bit = (num >> i) & 1;
				if (bit == 1) {
					ones++;
				} else {
					zeros++;
				}
			}
			total += ones * zeros; // ADD the pairs that differ in slot i
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
