package com.svetanis.algorithms.twopointers.segregate;

import java.util.Arrays;

// 75. Sort Colors
//
// Input: an array that holds only the values 0, 1 and 2.
// Return: nothing -- the array is sorted in place, in one pass, with O(1) extra space.
//
// The one idea: four regions behind three pointers. [0, left) holds 0s, [left, i) holds
// 1s, [i, right] has not been looked at, and (right, n-1] holds 2s. Each pass reads
// a[i] and shrinks the unread region by one. A 0 is swapped to left and both left and
// i move: what comes back from left is a 1, already read (or the same 0, when
// left == i). A 2 is swapped to right and only right moves: what comes back from right
// has not been looked at, so i reads it on the next pass -- which is why the for
// header has no i++.
//
// Siblings -- the same loop:
//   twopointers.segregate.SegregateZerosOnesTwos -- the identical method a second time,
//     written as a while loop with the names low / mid / high
//   twopointers.segregate.RGBs -- the same loop on the chars 'R', 'G', 'B'
//   sorting.quicksort.impl.ThreeWayQuickSortNationalFlag -- the same loop with a pivot in
//     place of 1: less / equal / greater
//
// Time: O(n) -- every pass shrinks [i, right] by one, so at most n passes.
// Space: O(1) -- swaps in place.

public final class DutchNationalFlag {

	public static void dnf(int[] a) {
		int left = 0;                      // START: [0, left) holds the 0s
		int right = a.length - 1;          // START: (right, n-1] holds the 2s
		for (int i = 0; i <= right;) {     // STOP: nothing left to read -- no i++ in the header
			if (a[i] == 0) {
				swap(a, i, left);          // SWAP the 0 to the edge of the 0s
				i++;                       // MOVE both: what came back from left is a 1
				left++;
			} else if (a[i] == 1) {
				i++;                       // MOVE i: already in the 1s
			} else {
				swap(a, i, right);         // SWAP the 2 to the edge of the 2s
				right--;                   // MOVE right only: i deliberately NOT advanced
			}
		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a = { 0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1 };
		dnf(a);
		System.out.println(Arrays.toString(a)); // [0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2]

		int[] a1 = { 2, 0, 2, 1, 1, 0 };
		dnf(a1);
		System.out.println(Arrays.toString(a1)); // [0, 0, 1, 1, 2, 2]

		int[] a2 = { 2, 0, 1 };
		dnf(a2);
		System.out.println(Arrays.toString(a2)); // [0, 1, 2]
	}
}