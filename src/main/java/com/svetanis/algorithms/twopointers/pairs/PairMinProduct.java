package com.svetanis.algorithms.twopointers.pairs;

import java.util.Arrays;

// Pair With the Smallest Product
//
// Input: an array of positive integers.
// Return: a pair {x, y} of values at two different positions with the smallest product.
// With fewer than 2 numbers it prints a message and returns {-1, -1}.
//
// The one idea: with positive numbers a product only grows when either factor grows,
// so the smallest product is the two smallest numbers. One pass keeps them, the
// smaller in first; the first two numbers start the pass already in that order.
//
// Siblings:
//   PairMaxProduct -- the largest product, where the signs matter
//   PairLargestSum -- the same pass for the two largest numbers
//
// Time: O(n) -- one pass.
// Space: O(1).

public final class PairMinProduct {

	public static int[] pair(int[] a) {
		int n = a.length;
		if (n < 2) {
			System.out.println("No such pair exists");
			return new int[] { -1, -1 };     // RETURN: no pair
		}
		if (n == 2) {
			return new int[] { a[0], a[1] }; // RETURN: the only pair
		}
		int first = Math.min(a[0], a[1]);    // START: the smaller of the first two
		int second = Math.max(a[0], a[1]);   // START: the larger of the first two
		for (int i = 2; i < n; i++) {
			if (a[i] < first) {
				second = first;              // MOVE the old smallest to second
				first = a[i];
			} else if (a[i] < second) {
				second = a[i];               // RECORD a new second smallest
			}
		}
		return new int[] { first, second };
	}

	public static void main(String[] args) {
		int[] a = { 11, 8, 5, 7, 5, 100 };
		System.out.println(Arrays.toString(pair(a))); // [5, 5]

		int[] a1 = { 5, 1, 3 };
		System.out.println(Arrays.toString(pair(a1))); // [1, 3]
	}
}
