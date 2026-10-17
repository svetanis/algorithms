package com.svetanis.algorithms.twopointers;

import java.util.Arrays;

// 977. Squares of a Sorted Array
//
// Input: an array sorted in non-decreasing order, negatives allowed.
// Return: the squares of its numbers, sorted in non-decreasing order.
//
// The one idea: a square depends only on the distance from 0, and the two numbers
// farthest from 0 are the two ends, so the largest square left is always at one of
// them. Compare the ends, WRITE the larger square into the last empty slot of the
// output, and drop that end. The output fills from the back, largest first.
// The loop runs while left <= right: there are no pairs here, so the last number
// standing must be written too. With < its slot keeps the default 0, which is only
// correct when the array happens to contain a 0.
//
// Two methods, the same problem:
//   sortSquares          -- the converging two pointers above, O(n)
//   sortSquaresBySorting -- square everything, then sort: the O(n log n) baseline
// Sibling: sorting.mergesort.SortSquares -- finds where the negatives end and merges
//   OUTWARD from that split, smallest square first, instead of inward from the ends.
//
// Time: O(n) -- every pass writes one number, so exactly n passes.
// Space: O(n) for the output; O(1) besides it.
// Squares fit in int because the values are at most 10^4 in size (10^8 < 2^31).

public final class SquaringSortedArray {

	public static int[] sortSquares(int[] a) {
		int n = a.length;
		int left = 0;                                  // START: the two ends
		int right = n - 1;
		int index = n - 1;                             // START: the back of the output
		int[] squares = new int[n];
		while (left <= right) {                        // STOP: the last number is written too
			int leftSquare = a[left] * a[left];        // COMPARE
			int rightSquare = a[right] * a[right];
			if (leftSquare > rightSquare) {
				squares[index] = leftSquare;           // WRITE the larger square
				left++;                                // DROP LEFT: written, done
			} else {
				squares[index] = rightSquare;          // WRITE the larger square
				right--;                               // DROP RIGHT: written, done
			}
			index--;                                   // NEXT SLOT
		}
		return squares;
	}

	public static int[] sortSquaresBySorting(int[] a) {
		int[] squares = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			squares[i] = a[i] * a[i];                  // SQUARE every number
		}
		Arrays.sort(squares);                          // SORT: O(n log n)
		return squares;
	}

	public static void main(String[] args) {
		int[] a = { -4, -1, 0, 3, 10 };
		System.out.println(Arrays.toString(sortSquares(a)));           // [0, 1, 9, 16, 100]
		System.out.println(Arrays.toString(sortSquaresBySorting(a)));  // [0, 1, 9, 16, 100]

		int[] a1 = { -7, -3, 2, 3, 11 };                               // no 0: catches < instead of <=
		System.out.println(Arrays.toString(sortSquares(a1)));          // [4, 9, 9, 49, 121]
		System.out.println(Arrays.toString(sortSquaresBySorting(a1))); // [4, 9, 9, 49, 121]
	}
}
