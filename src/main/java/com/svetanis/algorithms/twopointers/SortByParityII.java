package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 922. Sort Array By Parity II
//
// Input: an array of even length, half of its numbers even and half odd.
// Return: the same numbers arranged so every even index holds an even number and every
// odd index holds an odd number. Any such arrangement is accepted.
//
// The one idea: two cursors that each step by 2 -- even over the even indexes, odd over
// the odd indexes. When even lands on an odd number, that number is misplaced, and
// because the counts are equal, some odd index holds a misplaced even number: walk odd
// forward to it and swap, which fixes both. The odd cursor never moves back, because
// every odd index it has passed already holds an odd number. The inner walk has no
// bound check for the same reason: the equal counts guarantee it stops in range.
//
// Time: O(n) -- each cursor crosses its half of the array once.
// Space: O(1) -- swaps in place.

public final class SortByParityII {

	public static int[] sort(int[] a) {
		int even = 0;                   // START: the first even index
		int odd = 1;                    // START: the first odd index
		int n = a.length;
		while (even < n) {              // STOP: every even index is settled
			if (a[even] % 2 != 0) {     // SEE an odd number at an even index
				while (a[odd] % 2 != 0) {
					odd += 2;           // SKIP odd indexes already holding an odd number
				}
				swap(a, even, odd);     // SWAP: fixes both indexes at once
			}
			even += 2;                  // SETTLE: this even index holds an even number
		}
		return a;
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void main(String[] args) {
		int[] a1 = { 4, 2, 5, 7 };
		System.out.println(Arrays.toString(sort(a1))); // [4, 5, 2, 7]

		int[] a2 = { 2, 3 };
		System.out.println(Arrays.toString(sort(a2))); // [2, 3]
	}
}
