package com.svetanis.algorithms.twopointers.dups;

import java.util.Arrays;

// 1089. Duplicate Zeros
//
// Input: an array of digits 0..9, 1 <= length <= 10^4.
// Return: nothing -- the array is changed in place: every 0 is written twice, the
// numbers after it shift right, and whatever is pushed past the end is dropped.
//
// The one idea: the output is longer than the input, so write it from the RIGHT. Pass 1
// counts output slots (a zero takes two) to find srcIndex, the last number that still
// fits. Pass 2 walks left from there, writing into the slots from n - 1 leftward; the
// writer is never left of the reader, so no number is overwritten before it is read.
// One edge: when the last number that fits is a zero whose copy would land past the
// end, it is written once.
//
// Sibling: RemoveDuplicatesSorted -- the output is shorter than the input, so it is
//   written from the left instead.
//
// Time: O(n) -- one counting pass, one copying pass.
// Space: O(1).

public final class DuplicateZeros {

	public static void duplicateZeros(int[] a) {
		int n = a.length;
		int srcIndex = -1;
		int count = 0;
		while (count < n) {                     // COUNT output slots until the array is full
			srcIndex += 1;
			int inc = a[srcIndex] == 0 ? 2 : 1; // a zero takes two slots
			count += inc;
		}
		int dstIndex = n - 1;                   // START: write from the last slot
		if (count == n + 1) {                   // the last number is a zero whose copy does not fit
			a[dstIndex] = 0;                    // WRITE that zero once, at the end
			dstIndex -= 1;
			srcIndex -= 1;
		}

		while (dstIndex >= 0) {                 // WRITE right to left: dstIndex >= srcIndex always
			a[dstIndex] = a[srcIndex];
			if (a[srcIndex] == 0) {
				dstIndex -= 1;
				a[dstIndex] = 0;                // WRITE the zero's copy
			}
			srcIndex -= 1;                      // MOVE both one slot left
			dstIndex -= 1;
		}
	}

	public static void main(String[] args) {
		int[] a = { 1, 0, 2, 3, 0, 4, 5, 0 };
		duplicateZeros(a);
		System.out.println(Arrays.toString(a)); // [1, 0, 0, 2, 3, 0, 0, 4]
		int[] a1 = { 1, 2, 3 };
		duplicateZeros(a1);
		System.out.println(Arrays.toString(a1)); // [1, 2, 3]
		int[] a2 = { 8, 4, 5, 0, 0, 0, 0, 7 };
		duplicateZeros(a2);
		System.out.println(Arrays.toString(a2)); // [8, 4, 5, 0, 0, 0, 0, 0]
	}
}
