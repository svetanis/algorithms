package com.svetanis.algorithms.bits.xor;

import java.util.Arrays;

// 260. Single Number III
//
// Input: an int array in which every number appears exactly twice except two numbers, x and y,
// which appear once each. Returns { x, y } in either order. On LeetCode:
// 2 <= nums.length <= 3 * 10^4, any int value.
//
// The one idea: ^ of everything leaves x ^ y, since every pair cancels. x and y are different, so
// x ^ y has a 1 somewhere -- a slot where x and y differ; its lowest 1 is xor & ~(xor - 1). Split
// the array on that slot: x and y land in different halves, and both copies of every other number
// land in the same half. So each half is 136 again -- one single among pairs -- and ^ of each half
// gives one of the two.
//
// Time: O(n) -- two passes.
// Space: O(1) -- three ints, plus the two-number answer.
//
// Siblings: SingleNumber (136) has one single; SetMismatch645 uses the same split on one slot.

public final class SingleNumberIII {
	// Time Complexity: O(n)

	public static int[] single(int[] a) {
		// XOR of all the numbers
		int xor = 0;
		for (int i = 0; i < a.length; i++) {
			xor = xor ^ a[i]; // CANCEL: x ^ y is left
		}
		// the lowest 1 of x ^ y: a slot where x and y differ, already a mask
		int rmb = xor & ~(xor - 1); // SLOT: the lowest 1
		int x = 0, y = 0;
		for (int i = 0; i < a.length; i++) { // SPLIT on that slot
			// the rmb is set
			if ((a[i] & rmb) != 0) {
				x ^= a[i]; // HALF with the slot on
			} else { // the rmb is not set
				y ^= a[i]; // HALF with the slot off
			}
		}
		return new int[] { x, y };
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 2, 1, 3, 2, 5 };
		System.out.println(Arrays.toString(single(a1))); // [3, 5]

		int[] a2 = { -1, 0 };
		System.out.println(Arrays.toString(single(a2))); // [-1, 0]

		int[] a3 = { 0, 1 };
		System.out.println(Arrays.toString(single(a3))); // [1, 0]

		int[] a4 = { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
		System.out.println(Arrays.toString(single(a4))); // [6, 4]

		int[] a5 = { 2, 1, 3, 2 };
		System.out.println(Arrays.toString(single(a5))); // [3, 1]
	}
}
