package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 905. Sort Array By Parity
//
// Input: an array of non-negative integers.
// Return: the same numbers, every even one before every odd one. Any such order is
// accepted, and the array may be rearranged in place.
//
// The one idea: a WRITE cursor marks the end of the even block. The reader visits every
// number once; an even one is swapped down to the writer, which steps on. It is
// LC 283 Move Zeroes' loop with the keep test changed to "is even".
//
// Two methods, the same problem:
//   sortByParity  -- read/write cursors as above; the evens keep their original order
//   sortByParity2 -- converging cursors from both ends, swapping an odd on the left with
//                    an even on the right; the evens do NOT keep their order
// On {3, 1, 2, 4} the first gives {2, 4, 3, 1} and the second {4, 2, 1, 3}.
//
// Time: O(n) -- either method visits every number once.
// Space: O(1) -- swaps in place.

public final class SortByParity {

	// slow = the WRITE cursor: everything in [0, slow) is even
	// fast = the READ cursor: visits every element exactly once
	public static int[] sortByParity(int[] a) {
		int slow = 0;
		int n = a.length;
		for (int fast = 0; fast < n; fast++) {
			if (a[fast] % 2 == 0) {             // KEEP: 283's loop with a different keep test
				swap(a, slow, fast);            // WRITE the even down to the writer
				slow += 1;                      // MOVE the writer past it
			}
		}
		return a;
	}

	public static int[] sortByParity2(int[] a) {
		int n = a.length;
		int left = 0;                           // START: the two ends
		int right = n - 1;
		while (left < right) {                  // STOP: the cursors meet
			while (left < right && a[left] % 2 == 0) {
				left += 1;                      // SKIP evens already in place
			}
			while (left < right && a[right] % 2 != 0) {
				right -= 1;                     // SKIP odds already in place
			}
			swap(a, left, right);               // SWAP an odd on the left with an even on the right
			left += 1;
			right -= 1;
		}
		return a;
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a1 = { 3, 1, 2, 4 };
		System.out.println(Arrays.toString(sortByParity(a1)));  // [2, 4, 3, 1]

		int[] a2 = { 3, 1, 2, 4 };
		System.out.println(Arrays.toString(sortByParity2(a2))); // [4, 2, 1, 3]

		int[] a3 = { 0 };
		System.out.println(Arrays.toString(sortByParity(a3)));  // [0]
	}
}
