package com.svetanis.algorithms.twopointers.dups;

import static java.lang.Math.abs;

// Find the repeated value, by marking the slot each value points to
//
// Input: n + 1 integers, each in [1, n]. All of them appear once except one value,
// which appears any number of times.
// Return: that repeated value. The array is written to during the call and restored
// before it returns.
//
// The one idea: use each value as an index and flip the sign of the slot it points to.
// Landing on a slot that is already negative means that value sent us there twice, so
// it is the repeated one. Every value is >= 1, so every value is a legal index and
// slot 0 is never a target.
//
// This answers LC 287's question but not under LC 287's rules: it writes into the array
// and restores it afterwards, and 287 requires solving it without modifying nums. The
// submitted solution is DuplicateInPlaceFloyd.
//
// Siblings -- the same problem:
//   DuplicateInPlaceFloyd  -- O(n), read-only: reads i -> a[i] as a linked list
//   DuplicateInPlaceBinary -- O(n log n), read-only: counts the numbers <= mid
//   DuplicateInPermutationSum, DuplicateInPermutationXor -- need every value 1..n present
//
// Time: O(n) -- one marking pass, one restoring pass.
// Space: O(1), but the array is modified and then restored.

public final class DuplicateInPlaceMarking {

	public static int duplicate(int[] a) {
		int dup = -1;
		for (int i = 0; i < a.length; i++) {
			int cur = abs(a[i]);      // READ the value; its own slot may already be flipped
			if (a[cur] < 1) {         // COMPARE: slot cur is marked, so cur was seen before
				dup = cur;            // FOUND the repeat
				break;
			}
			a[cur] *= -1;             // RECORD: mark slot cur as seen
		}
		for (int i = 0; i < a.length; i++) {
			a[i] = abs(a[i]);         // RESTORE every sign
		}
		return dup;                   // RETURN; -1 only when nothing repeats, which 287 rules out
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 3, 4, 2, 2 };
		System.out.println(duplicate(a1)); // 2

		int[] a2 = { 3, 1, 3, 4, 2 };
		System.out.println(duplicate(a2)); // 3

		int[] a4 = { 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 10 };
		System.out.println(duplicate(a4)); // 7

		// the repeat can crowd out the other values entirely.
		// DuplicateInPermutationSum is wrong on all three, ...Xor on the last two.
		int[] a3 = { 3, 3, 3, 3, 3 };
		System.out.println(duplicate(a3)); // 3

		int[] a5 = { 2, 2, 2, 2, 2 };
		System.out.println(duplicate(a5)); // 2

		int[] a6 = { 1, 1, 1, 1, 1 };
		System.out.println(duplicate(a6)); // 1
	}
}
