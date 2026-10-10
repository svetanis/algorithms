package com.svetanis.algorithms.twopointers.quadruple;

import java.util.Arrays;

// Maximum product of four numbers, by sorting
//
// Input: an array of integers.
// Return: the largest product of four numbers at four different positions; -1 when there
// are fewer than four numbers.
//
// The one idea: signs decide, so only the ends of the sorted order matter. Sort, and the
// best four are the four LARGEST, the four SMALLEST (four negatives make a positive), or
// the two smallest with the two largest.
//
// Sibling: twopointers.quadruple.MaxProductQuadruple -- the same three products from one
//   pass that keeps the top four and the bottom four: O(n), no sort.
//
// Time: O(n log n) for the sort.
// Space: O(1) besides the sort, which works in place.

public final class MaxProductQuadrupleSorting {

	public static int quadruple(int[] a) {
		int n = a.length;
		if (n < 4) {
			return -1;                                   // RETURN -1: no four numbers to multiply
		}
		Arrays.sort(a);                                  // SORT: the candidates sit at the ends
		int x = a[0] * a[1] * a[2] * a[3];               // COMPARE the four smallest,
		int y = a[n - 1] * a[n - 2] * a[n - 3] * a[n - 4]; // the four largest,
		int z = a[0] * a[1] * a[n - 1] * a[n - 2];       // and the two smallest with the two largest
		return Math.max(x, Math.max(y, z));
	}

	public static void main(String[] args) {
		int[] a = { 10, 3, 5, 6, 20 };
		System.out.println(quadruple(a)); // 6000

		int[] a1 = { -10, -3, -5, -6, -20 };
		System.out.println(quadruple(a1)); // 6000

		int[] a2 = { 1, -4, 3, -6, 7, 0 };
		System.out.println(quadruple(a2)); // 504
	}
}
