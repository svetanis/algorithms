package com.svetanis.algorithms.twopointers.dups;

import static java.lang.Math.abs;

// given an array of n + 1 integers in the range 1..n
// where one value repeats any number of times
// find the repeated value

// use each value as an index and flip the sign of the slot it points to.
// landing on a slot that is already negative means that value sent us
// there twice, so it is the repeated one. every value is >= 1, so every
// value is a legal index and slot 0 is never a target.

// this answers LC 287's question but not under LC 287's rules: it writes
// into the array and restores it afterwards, and 287 requires solving it
// without modifying nums. the submitted solution is DuplicateInPlaceFloyd.

public final class DuplicateInPlaceMarking {
	// Time Complexity: O(n)
	// Space Complexity: O(1), but the array is modified and then restored

	public static int duplicate(int[] a) {
		int dup = -1;
		for (int i = 0; i < a.length; i++) {
			int cur = abs(a[i]);
			if (a[cur] < 1) {
				dup = cur;
				break;
			}
			a[cur] *= -1;
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = abs(a[i]);
		}
		return dup;
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 4, 2, 2 };
		System.out.println(duplicate(a1)); // 2

		int[] a2 = { 3, 1, 3, 4, 2 };
		System.out.println(duplicate(a2)); // 3

		int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10 };
		System.out.println(duplicate(a4)); // 7

		// the repeat can crowd out the other values entirely.
		// DuplicateInPermutationSum and ...Xor are wrong on all three.
		int[] a3 = { 3, 3, 3, 3, 3 };
		System.out.println(duplicate(a3)); // 3

		int[] a5 = { 2, 2, 2, 2, 2 };
		System.out.println(duplicate(a5)); // 2

		int[] a6 = { 1, 1, 1, 1, 1 };
		System.out.println(duplicate(a6)); // 1
	}
}
