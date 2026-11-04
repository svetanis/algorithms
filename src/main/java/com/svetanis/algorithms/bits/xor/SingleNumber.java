package com.svetanis.algorithms.bits.xor;

// 136. Single Number
//
// Input: a non-empty int array in which every number appears twice except one, which appears once.
// Returns that one. On LeetCode: 1 <= nums.length <= 3 * 10^4, -3 * 10^4 <= nums[i] <= 3 * 10^4.
//
// The one idea: ^ of a number with itself is 0, ^ with 0 leaves a number as it is, and the order of
// the ^s does not matter. So ^ every number together: each pair cancels wherever its two copies
// stand, and the single is all that is left. Negatives too -- ^ works slot by slot, sign included.
//
// Time: O(n) -- one pass.
// Space: O(1) -- one int.
//
// Siblings: SingleNumberII (137) has the others three times each, SingleNumberIII (260) has two
// singles.

public final class SingleNumber {
	// Time Complexity: O(n)

	public static int single(int[] a) {
		int xor = 0;
		for (int num : a) { // WALK every number
			xor = xor ^ num; // CANCEL: pairs cancel, the single is left
		}
		return xor;
	}

	public static void main(String[] args) {
		int[] a1 = { 2, 2, 1 };
		System.out.println(single(a1)); // 1

		int[] a2 = { 4, 1, 2, 1, 2 };
		System.out.println(single(a2)); // 4

		int[] a3 = { 1 };
		System.out.println(single(a3)); // 1
	}
}
